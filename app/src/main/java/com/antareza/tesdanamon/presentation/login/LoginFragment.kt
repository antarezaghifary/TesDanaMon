package com.antareza.tesdanamon.presentation.login

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.antareza.tesdanamon.BuildConfig
import com.antareza.tesdanamon.R
import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.data.reqres.web.WebSocketListener
import com.antareza.tesdanamon.databinding.FragmentLoginBinding
import com.antareza.tesdanamon.presentation.base.BaseFragment
import com.antareza.tesdanamon.util.SharedPref
import com.antareza.tesdanamon.util.UserRoleManage
import com.antareza.tesdanamon.util.setError
import okhttp3.Request
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val nav: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val viewModel: LoginViewModel by viewModel()
    private var apiKeyWebSocketClient: String? = null
    private val channelId = 1


    override fun setContent(savedInstanceState: Bundle?) {
        setAction()
        onBackPressed()
        setupWebSocket()
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

    private fun setupWebSocket() {
        apiKeyWebSocketClient = BuildConfig.WEB_SOCKET_APIKEY
        val req = Request.Builder()
            .url("wss://free.blr2.piesocket.com/v3/$channelId?api_key=$apiKeyWebSocketClient&notify_self=1")
            .build()

        val client: okhttp3.OkHttpClient = okhttp3.OkHttpClient()
        val listener: okhttp3.WebSocketListener = WebSocketListener()
        client.newWebSocket(req, listener)

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