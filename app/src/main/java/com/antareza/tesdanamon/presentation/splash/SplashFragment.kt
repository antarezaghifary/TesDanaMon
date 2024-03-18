package com.antareza.tesdanamon.presentation.splash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.antareza.tesdanamon.R
import com.antareza.tesdanamon.databinding.FragmentSplashBinding
import com.antareza.tesdanamon.presentation.base.BaseFragment
import com.oratakashi.viewbinding.core.tools.visible

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val nav: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setContent(savedInstanceState: Bundle?) {

        goEdgeToEdge()

        with(binding) {
            splashView.visible()
            splashView.animateLogo()
            welcomeTitle.animate()
                .scaleX(TARGET_SCAlE)
                .scaleY(TARGET_SCAlE)
                .setStartDelay(SCALE_ANIMATION_DELAY)
                .setDuration(SCALE_ANIMATION_DURATION)
                .start()
        }

        Handler().postDelayed({
            nav?.navigate(R.id.action_splashFragment_to_loginFragment)
        }, 4000)
    }

    override fun setViewModel() {
    }

    private fun goEdgeToEdge() {
        requireActivity().window?.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }


    companion object {
        private const val TARGET_SCAlE = 1f
        private const val SCALE_ANIMATION_DELAY = 800L
        private const val SCALE_ANIMATION_DURATION = 800L
    }
}