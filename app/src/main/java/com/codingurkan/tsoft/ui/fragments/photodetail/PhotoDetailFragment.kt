package com.codingurkan.tsoft.ui.fragments.photodetail

import androidx.fragment.app.viewModels
import com.codingurkan.tsoft.base.BaseFragment
import com.codingurkan.tsoft.databinding.FragmentPhotoDetailBinding
import com.codingurkan.tsoft.model.Hit
import com.codingurkan.tsoft.util.COMMENT
import com.codingurkan.tsoft.util.PHOTO_DATA
import com.codingurkan.tsoft.util.loadImage

class PhotoDetailFragment : BaseFragment<FragmentPhotoDetailBinding, PhotoDetailViewModel>(
    FragmentPhotoDetailBinding::inflate
) {
    override val viewModel by viewModels<PhotoDetailViewModel>()

    private var data: Hit? = null

    override fun onCreateFinished() {
        data = arguments?.getSerializable(PHOTO_DATA) as Hit
    }

    override fun initializeListeners() {
        data?.webformatURL?.let { binding.imageDetails.loadImage(it) }
        binding.likeDetailsTv.text = data?.likes.toString()
        binding.commentDetailsTv.text = "(${data?.comments} $COMMENT)"
        data?.userImageURL?.let { binding.userImage.loadImage(it) }
        binding.userName.text = data?.user
    }


    override fun observeEvents() {
        //TODO : Observe olan bir datamız olmadığı için burası boş
    }

    override fun initAdapters() {
        //TODO: Adapter'a ihtiyacımız olmadığından burası boş.
    }
}