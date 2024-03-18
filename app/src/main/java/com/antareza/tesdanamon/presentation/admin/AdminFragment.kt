package com.antareza.tesdanamon.presentation.admin

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.antareza.tesdanamon.R
import com.antareza.tesdanamon.databinding.FragmentAdminBinding
import com.antareza.tesdanamon.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class AdminFragment : BaseFragment<FragmentAdminBinding>(FragmentAdminBinding::inflate) {


    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val adapterAdmin: AdminAdapter by lazy {
        AdminAdapter({ editData ->

        }, { deleteData ->
            viewModel.deleteDataUser(deleteData.id)
        })
    }
    private val viewModel: AdminViewModel by viewModel()

    override fun setContent(savedInstanceState: Bundle?) {
        with(binding) {
            setAction()
            initObserver()
        }
    }

    override fun setViewModel() = with(binding) {
        viewModel.isSuccessGetUser.observe(viewLifecycleOwner) { result ->
            // do something
            if (result) {
                rvUser.apply {
                    viewModel.userList.observe(viewLifecycleOwner) {
                        adapterAdmin.submitData(it)
                    }
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = adapterAdmin
                }
            }
        }

        viewModel.isDeleteUser.observe(viewLifecycleOwner) { result ->
            if (result) {
                refreshData()
            }
        }
    }

    private fun initObserver() {
        viewModel.getDataUser()
    }

    private fun setAction() {
        with(binding) {
            fabLogout.setOnClickListener {
                // do something
            }
        }
    }

    private fun refreshData() {
        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        ft.detach(this).attach(this).commit()
        viewModel.getDataUser()
    }
}