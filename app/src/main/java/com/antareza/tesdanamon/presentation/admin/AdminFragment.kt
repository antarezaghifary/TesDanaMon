package com.antareza.tesdanamon.presentation.admin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.antareza.tesdanamon.R
import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.databinding.FragmentAdminBinding
import com.antareza.tesdanamon.presentation.base.BaseFragment
import com.antareza.tesdanamon.util.SharedPref
import com.antareza.tesdanamon.util.setError
import org.koin.androidx.viewmodel.ext.android.viewModel


class AdminFragment : BaseFragment<FragmentAdminBinding>(FragmentAdminBinding::inflate) {


    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val userSharedPref by lazy { SharedPref(requireContext()) }

    private val adapterAdmin: AdminAdapter by lazy {
        AdminAdapter({ editData ->
            val bundle = Bundle()
            bundle.putParcelable(DATA, editData)
            navigation?.navigate(R.id.action_adminFragment_to_adminEditUserFragment, bundle)
        }, { deleteData ->
            showDeleteConfirmationDialog(deleteData)
        })
    }
    private val viewModel: AdminViewModel by viewModel()

    override fun setContent(savedInstanceState: Bundle?) {
        with(binding) {
            setAction()
            initObserver()
            onBackPressed()
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
                userSharedPref.logout()
                navigation?.navigate(R.id.action_adminFragment_to_loginFragment)
            }
        }
    }

    private fun refreshData() {
        viewModel.getDataUser()
        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        ft.detach(this).attach(this).commit()
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

    @SuppressLint("MissingInflatedId")
    private fun showDeleteConfirmationDialog(user: User) {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_confirm, null)
        val passwordEditText = dialogView.findViewById<EditText>(R.id.tie_password)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_confirmation_title))
            .setMessage(getString(R.string.delete_confirmation_message))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                val password = passwordEditText.text.toString()
                if (password == userSharedPref.getDataUser().password) {
                    viewModel.deleteDataUser(user.id)
                } else {
                    setError(getString(R.string.password_not_match), requireContext())
                }
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()

        dialog.show()
    }

    companion object {
        var DATA = "data"
    }
}