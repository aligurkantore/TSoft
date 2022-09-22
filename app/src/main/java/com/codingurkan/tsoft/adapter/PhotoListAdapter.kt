package com.codingurkan.tsoft.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingurkan.tsoft.R
import com.codingurkan.tsoft.databinding.PhotoListItemIkiBinding
import com.codingurkan.tsoft.model.Hit
import com.codingurkan.tsoft.util.loadImage

class PhotoListAdapter(
    private val onAdapterClick: (Boolean?, Hit) -> Unit,
) : RecyclerView.Adapter<PhotoListAdapter.PhotoListVH>() {

    private val list: ArrayList<Hit> = arrayListOf()

    class PhotoListVH(val binding: PhotoListItemIkiBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListVH {
        val view =
            PhotoListItemIkiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoListVH(view)
    }

    override fun onBindViewHolder(holder: PhotoListVH, position: Int) {
        with(holder.binding) {
            imageViewList.loadImage(list[position].previewURL)
            likeTv.text = list[position].likes.toString()
            commentTv.text =
                holder.itemView.context.getString(R.string.comment_new, list[position].comments)
            viewsTv.text =
                holder.itemView.context.getString(R.string.views_new, list[position].views / 1000)

            imageViewList.setOnClickListener {
                onAdapterClick(null, list[position])
            }
            heart.setOnCheckedChangeListener { _, b ->
                onAdapterClick(b, list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setPhotoList(newPhotoList: ArrayList<Hit>) {
        val beforeSize = list.size
        list.addAll(newPhotoList)
        notifyItemRangeInserted(beforeSize, list.size)
    }
}