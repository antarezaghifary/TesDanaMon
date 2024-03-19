package com.antareza.tesdanamon.presentation.useredit

import android.os.Bundle
import com.antareza.tesdanamon.R
import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.data.reqres.model.UserEntity
import com.antareza.tesdanamon.databinding.FragmentAdminEditUserBinding
import com.antareza.tesdanamon.presentation.admin.AdminFragment.Companion.DATA
import com.antareza.tesdanamon.presentation.base.BaseFragment
import com.antareza.tesdanamon.util.UserRoleManage
import com.antareza.tesdanamon.util.setError
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminEditUserFragment :
    BaseFragment<FragmentAdminEditUserBinding>(FragmentAdminEditUserBinding::inflate) {

    private val dataUser: User? by lazy {
        arguments?.getParcelable(DATA)
    }

    private var uRole: Int = UserRoleManage.USER.value

    private val viewModel: AdminEditUserViewModel by viewModel()

    override fun setContent(savedInstanceState: Bundle?) = with(binding) {
        setData()
        setListener()
    }

    override fun setViewModel() = with(binding) {

        if (dataUser?.role == UserRoleManage.USER.value) {
            rbUser.isChecked = true
            rbAdmin.isChecked = false
        } else {
            rbUser.isChecked = false
            rbAdmin.isChecked = true
        }

        viewModel.isEditDataSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                activity?.onBackPressed()
            } else {
                setError(getString(R.string.error_register), requireContext())
            }
        }
    }

    private fun setListener() = with(binding) {
        ivBack.setOnClickListener {
            activity?.onBackPressed()
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
        btnSave.setOnClickListener {
            if (tieFullName.text.isNullOrEmpty()) {
                tieFullName.error = getString(R.string.username)
            } else if (tieEmail.text.isNullOrEmpty()) {
                tieEmail.error = getString(R.string.email)
            } else if (tiePassword.text.isNullOrEmpty()) {
                tiePassword.error = getString(R.string.password)
            } else {
                submitData()
            }
        }
    }

    private fun setData() = with(binding) {
        tieFullName.setText(dataUser?.username)
        tieEmail.setText(dataUser?.email)
        tiePassword.setText(dataUser?.password)
    }

    private fun submitData() = with(binding) {
        viewModel.editData(
            UserEntity(
                id = dataUser?.id ?: 0,
                username = tieFullName.text.toString(),
                email = tieEmail.text.toString(),
                password = tiePassword.text.toString(),
                role = uRole
            )
        )
    }
}