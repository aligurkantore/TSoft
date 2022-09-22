package com.codingurkan.tsoft.ui.fragments.photofavourite

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.codingurkan.tsoft.base.BaseFragment
import com.codingurkan.tsoft.databinding.FragmentFavouriteBinding


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding, FavouriteViewModel>(
    FragmentFavouriteBinding::inflate
) {
    override val viewModel by viewModels<FavouriteViewModel>()

    override fun onCreateFinished() {
        Toast.makeText(requireContext(), "Welcome", Toast.LENGTH_SHORT).show()
    }

    override fun initializeListeners() {
        //Todo initializeListeners
    }

    override fun observeEvents() {
        //Todo observeEvents
    }

    override fun initAdapters() {
        //Todo initAdapters
    }
}
