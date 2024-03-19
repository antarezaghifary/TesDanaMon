package com.antareza.tesdanamon.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.antareza.tesdanamon.R
import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.data.reqres.model.UserEntity
import com.antareza.tesdanamon.databinding.FragmentLoginBinding
import com.antareza.tesdanamon.presentation.base.BaseFragment
import com.antareza.tesdanamon.util.SharedPref
import com.antareza.tesdanamon.util.UserRoleManage
import com.antareza.tesdanamon.util.setError
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val nav: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val viewModel: LoginViewModel by viewModel()


    override fun setContent(savedInstanceState: Bundle?) {
        setAction()
        onBackPressed()
    }


    private fun setAction() {
        with(binding) {
            btnLogin.setOnClickListener {
                if (tieEmail.text.isNullOrEmpty()) {
                    tieEmail.error = getString(R.string.email)
                } else if (tiePassword.text.isNullOrEmpty()) {
                    tiePassword.error = getString(R.string.password)
                } else {
                    initObserver()
                }
            }

            btnRegister.setOnClickListener {
                nav?.navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }


    override fun setViewModel() {
        viewModel.isSuccessLogin.observe(viewLifecycleOwner) {
            if (it != null) {
                val userSharedPref = SharedPref(requireContext())
                userSharedPref.saveIsLogIn(true)
                userSharedPref.saveDataUser(it)
                checkUserRole(it)
            } else {
                setError(getString(R.string.error_login), requireContext())
            }
        }
    }

    private fun initObserver() = with(binding) {
        viewModel.login(email = tieEmail.text.toString(), password = tiePassword.text.toString())
    }

    private fun checkUserRole(result: User) {
        if (result.role == UserRoleManage.USER.value) {
            nav?.navigate(R.id.action_loginFragment_to_userFragment)
        } else if (result.role == UserRoleManage.ADMIN.value) {
            nav?.navigate(R.id.action_loginFragment_to_adminFragment)
        } else {
            setError(getString(R.string.error_login), requireContext())
        }
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finishAffinity(requireActivity())
                }
            })
    }
}