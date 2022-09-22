package com.codingurkan.tsoft.ui.fragments.photolist

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.codingurkan.tsoft.R
import com.codingurkan.tsoft.adapter.PhotoListAdapter
import com.codingurkan.tsoft.base.BaseFragment
import com.codingurkan.tsoft.databinding.FragmentPhotoListBinding
import com.codingurkan.tsoft.model.Hit
import com.codingurkan.tsoft.util.EndlessScrollListener
import com.codingurkan.tsoft.util.FIRST_PAGE
import com.codingurkan.tsoft.util.PHOTO_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment : BaseFragment<FragmentPhotoListBinding, PhotoListViewModel>(
    FragmentPhotoListBinding::inflate
) {
    override val viewModel by viewModels<PhotoListViewModel>()
    private lateinit var photoListAdapter: PhotoListAdapter


    override fun onCreateFinished() {
        initAdapters()
        viewModel.downloadPhotos(FIRST_PAGE)
    }

    override fun initializeListeners() {
        //TODO initialize edecek bir datamız olmadığı için burası boş
    }

    override fun observeEvents() {
        viewModel.photoList.observe(viewLifecycleOwner) { _data ->
            _data?.let {
                photoListAdapter.setPhotoList(it)
            }
        }
    }

    override fun initAdapters() {
        photoListAdapter = PhotoListAdapter { b, _data ->
            b?.let {
                //Todo : Bu b isChecked olarak dönecek. burada database kaydetme ve çıkarma işlemleri yapılacak.
            } ?: run {
                val bundle = Bundle()
                bundle.putSerializable(PHOTO_DATA, _data)
                findNavController().navigate(R.id.action_photoListFragment_to_photoDetailFragment,
                    bundle)
            }
        }.also { _adapter ->
            photoListAdapter = _adapter
            binding.recyclerView.apply {
                adapter = photoListAdapter
                addOnScrollListener(object :
                    EndlessScrollListener(layoutManager as GridLayoutManager) {
                    override fun onLoadMore(page: Int) {
                        viewModel.downloadPhotos(page)
                    }
                })
            }
        }
    }
}