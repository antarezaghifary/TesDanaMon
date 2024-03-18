package com.antareza.tesdanamon.presentation.register

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.antareza.tesdanamon.R
import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.databinding.FragmentRegisterBinding
import com.antareza.tesdanamon.presentation.base.BaseFragment
import com.antareza.tesdanamon.util.UserRoleManage
import com.antareza.tesdanamon.util.setError
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    private val nav: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private var uRole: Int = UserRoleManage.USER.value

    override fun setContent(savedInstanceState: Bundle?) {
        setAction()
    }

    private val viewModel: RegisterViewModel by viewModel()


    private fun setAction() {
        with(binding) {
            btnRegister.setOnClickListener {
                if (tieFullName.text.isNullOrEmpty()) {
                    tieFullName.error = getString(R.string.username)
                } else if (tieEmail.text.isNullOrEmpty()) {
                    tieEmail.error = getString(R.string.email)
                } else if (tiePassword.text.isNullOrEmpty()) {
                    tiePassword.error = getString(R.string.password)
                } else {
                    initObserver()
                }
            }

            ivBack.setOnClickListener {
                nav?.navigateUp()
            }

            rbRole.setOnCheckedChangeListener { radioGroup, id ->
                when (id) {
                    R.id.rb_user -> {
                        // do something
                        uRole = UserRoleManage.USER.value
                    }

                    R.id.rb_admin -> {
                        // do something
                        uRole = UserRoleManage.ADMIN.value
                    }
                }
            }
        }
    }

    override fun setViewModel() = with(binding) {

        viewModel.isSuccessRegister.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                nav?.navigateUp()
            } else {
                setError(getString(R.string.error_register), requireContext())
            }
        }
    }

    private fun initObserver() = with(binding) {
        viewModel.register(
            User(
                username = tieFullName.text.toString(),
                email = tieEmail.text.toString(),
                password = tiePassword.text.toString(),
                role = uRole
            )
        )
    }
}