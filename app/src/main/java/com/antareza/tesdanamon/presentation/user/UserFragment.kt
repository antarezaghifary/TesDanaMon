package com.antareza.tesdanamon.presentation.user

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.antareza.tesdanamon.databinding.FragmentUserBinding
import com.antareza.tesdanamon.domain.reqres.model.Photo
import com.antareza.tesdanamon.presentation.base.BaseFragment
import com.antareza.tesdanamon.util.addObservers
import com.oratakashi.viewbinding.core.tools.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate),
    UserDataContract {

    private val viewModel: UserViewModel by viewModel()
    private val adapterUser: UserAdapterL by lazy {
        UserAdapterL()
    }


    override fun setContent(savedInstanceState: Bundle?) {
        setAction()
    }

    private fun setAction() {
        with(binding) {
            fabLogout.setOnClickListener {

            }
        }
    }

    override fun setViewModel() {
        addObservers(UserObserver(this, viewModel))
        viewModel.getPhotos(1, 30)
    }

    override fun onGetPhotoLoading() {
        Toast.makeText(requireContext(), "Loading . . .", Toast.LENGTH_LONG).show()
    }

    override fun onGetPhotoSuccess(data: List<Photo>) {
        adapterUser.submitData(data)
        binding.rvDashboard.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapterUser
        }

    }

    override fun onGetPhotoEmpty() {
        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_LONG).show()
    }

    override fun onGetPhotoFailure(error: Throwable) {
        Toast.makeText(requireContext(), "Error $error", Toast.LENGTH_LONG).show()
    }

}