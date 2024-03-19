package com.antareza.tesdanamon.presentation.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antareza.tesdanamon.R
import com.antareza.tesdanamon.databinding.FragmentUserBinding
import com.antareza.tesdanamon.domain.reqres.model.Photo
import com.antareza.tesdanamon.presentation.base.BaseFragment
import com.antareza.tesdanamon.util.SharedPref
import com.antareza.tesdanamon.util.addObservers
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate),
    UserDataContract {

    private val viewModel: UserViewModel by viewModel()
    private val adapterUser: UserAdapterL by lazy {
        UserAdapterL()
    }
    private val userSharedPref by lazy { SharedPref(requireContext()) }
    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private var scrollPosition:Int = 0

    override fun setContent(savedInstanceState: Bundle?) {
        setAction()
        onBackPressed()
    }

    private fun setAction() {
        with(binding) {
            fabLogout.setOnClickListener {
                userSharedPref.logout()
                navigation?.navigate(R.id.action_userFragment_to_loginFragment)
            }
        }
    }

    override fun setViewModel() {
        addObservers(UserObserver(this, viewModel))
        viewModel.getPhotos(1)
    }

    override fun onGetPhotoLoading() {
        Toast.makeText(requireContext(), getString(R.string.loading), Toast.LENGTH_SHORT).show()
    }

    override fun onGetPhotoSuccess(data: List<Photo>) {
        adapterUser.submitData(data)
        binding.rvDashboard.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapterUser
            setupRecyclerView()
        }
    }

    override fun onGetPhotoEmpty() {
        Toast.makeText(requireContext(), getString(R.string.data_not_found), Toast.LENGTH_LONG)
            .show()
    }

    override fun onGetPhotoFailure(error: Throwable) {
        Toast.makeText(requireContext(), "Error $error", Toast.LENGTH_LONG).show()
    }

    private fun setupRecyclerView() = with(binding) {
        rvDashboard.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val notAtBeginning = firstVisibleItemPosition >= 0
                scrollPosition = recyclerView.computeVerticalScrollOffset()
                if (!viewModel.isLoading.value!! &&
                    visibleItemCount.plus(firstVisibleItemPosition) >= totalItemCount &&
                    notAtBeginning
                ) {
                    //findLastVisibleItemPosition
                    viewModel.getPhotos(viewModel.currenPage.value?.inc() ?: 1)
                }
            }
        })
        rvDashboard.scrollToPosition(scrollPosition)
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    ActivityCompat.finishAffinity(requireActivity())
                }
            })
    }

}