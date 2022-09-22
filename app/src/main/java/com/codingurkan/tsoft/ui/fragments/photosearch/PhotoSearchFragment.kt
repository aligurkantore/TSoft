package com.codingurkan.tsoft.ui.fragments.photosearch

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.codingurkan.tsoft.R
import com.codingurkan.tsoft.adapter.PhotoListAdapter
import com.codingurkan.tsoft.base.BaseFragment
import com.codingurkan.tsoft.databinding.FragmentPhotoSearchBinding
import com.codingurkan.tsoft.model.Hit
import com.codingurkan.tsoft.util.EndlessScrollListener
import com.codingurkan.tsoft.util.FIRST_PAGE
import com.codingurkan.tsoft.util.PHOTO_DATA
import com.codingurkan.tsoft.util.WELCOME_SEARCH
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class PhotoSearchFragment : BaseFragment<FragmentPhotoSearchBinding, PhotoSearchViewModel>(
    FragmentPhotoSearchBinding::inflate
) {
    override val viewModel by viewModels<PhotoSearchViewModel>()
    private lateinit var photoListAdapter: PhotoListAdapter

    override fun onCreateFinished() {
        Toast.makeText(requireContext(), WELCOME_SEARCH, Toast.LENGTH_SHORT).show()
        initAdapters()
    }

    override fun initializeListeners() {
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null) {
                        lifecycleScope.launchWhenCreated {
                            delay(500)
                            viewModel.query.postValue(p0)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null) {
                        lifecycleScope.launchWhenCreated {
                            delay(500)
                            viewModel.query.postValue(p0)
                        }
                    }
                    return true
                }
            })
        }
    }

    override fun observeEvents() {
        viewModel.apply {
            searchPhotoList.observe(viewLifecycleOwner){
                it?.let {
                    photoListAdapter.setPhotoList(it)
                }
            }

            query.observe(viewLifecycleOwner){ _query ->
                _query?.let {
                    viewModel.downloadSearchPhoto(_query, FIRST_PAGE)
                }
            }
        }
    }

    override fun initAdapters() {
        photoListAdapter = PhotoListAdapter { b, _data ->
            val bundle = Bundle()
            bundle.putSerializable(PHOTO_DATA, _data)
            findNavController().navigate(R.id.action_photoSearchFragment_to_photoDetailFragment,
                bundle)
        }.also { _adapter ->
            photoListAdapter = _adapter
            binding.recyclerView.apply {
                adapter = photoListAdapter
                addOnScrollListener(object :
                    EndlessScrollListener(layoutManager as GridLayoutManager) {
                    override fun onLoadMore(page: Int) {
                        viewModel.query.value?.let { _query ->
                            viewModel.downloadSearchPhoto(_query, page)
                        }
                    }
                })
            }
        }
    }
}
