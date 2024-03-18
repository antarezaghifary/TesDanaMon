package com.antareza.tesdanamon.presentation.user

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antareza.tesdanamon.databinding.ItemAdapterPhotoBinding
import com.antareza.tesdanamon.domain.reqres.model.Photo
import com.antareza.tesdanamon.util.loadImage
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding

class UserAdapterL : RecyclerView.Adapter<ViewHolder<ItemAdapterPhotoBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemAdapterPhotoBinding> = viewBinding(parent)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder<ItemAdapterPhotoBinding>, position: Int) {
        with(holder.binding) {
            ivPhoto.loadImage(data[position].thumbnailUrl)
            tvTitle.text = "${data[position].id} - ${data[position].title}"
            tvUrl.text = data[position].url
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitData(data: List<Photo>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<Photo> by lazy {
        ArrayList()
    }
}
