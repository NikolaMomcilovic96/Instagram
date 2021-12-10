package com.raywenderlich.instagramclient.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.instagramclient.databinding.MainFragmentBinding
import com.raywenderlich.instagramclient.viewmodel.PostViewModel

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var postViewModel: PostViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        val adapter = ListItemRecyclerViewAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        postViewModel.readAllData.observe(viewLifecycleOwner, { post ->
            adapter.setData(post)
        })

        return binding.root
    }
}