package com.antareza.tesdanamon.presentation.admin

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antareza.tesdanamon.data.reqres.model.User
import com.antareza.tesdanamon.databinding.ItemAdapterPhotoBinding
import com.antareza.tesdanamon.databinding.ItemAdapterUserBinding
import com.antareza.tesdanamon.domain.reqres.model.Photo
import com.antareza.tesdanamon.util.UserRoleManage
import com.antareza.tesdanamon.util.loadImage
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding

class AdminAdapter(
    private val editData: (User) -> Unit,
    private val deleteData: (User) -> Unit
) : RecyclerView.Adapter<ViewHolder<ItemAdapterUserBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemAdapterUserBinding> = viewBinding(parent)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder<ItemAdapterUserBinding>, position: Int) {
        with(holder.binding) {
            val item = data[position]
            textId.text = "ID : ${item.id}"
            textUsername.text = "Title : ${item.username}"
            textEmail.text = "Email : ${item.email}"
            textRole.text = "Role : ${item.role}"
            textRole.text = if (item.role == UserRoleManage.USER.value) {
                "Role : User"
            } else {
                "Role : Admin"
            }
            ivEdit.setOnClickListener {
                editData(item)
            }
            ivDelete.setOnClickListener {
                deleteData(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitData(data: List<User>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<User> by lazy {
        ArrayList()
    }
}
