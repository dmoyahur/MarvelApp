package com.hiberus.mobile.android.marvelapp.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiberus.mobile.android.marvelapp.common.BaseFragment
import com.hiberus.mobile.android.marvelapp.characters.mapper.toVo
import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.marvelapp.databinding.FragmentCharactersBinding
import com.hiberus.mobile.android.marvelapp.util.OnCharacterClickListener
import com.hiberus.mobile.android.model.characters.CharacterBo
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment : BaseFragment() {

    companion object {
        private const val endlessOffset = 5
    }

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersListViewModel by viewModel()
    private val charactersListAdapter: CharactersListAdapter by inject()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var isLoadingMore = false
    private var previousTotal = 0

    private val charactersAdapterListener = object: OnCharacterClickListener {
        override fun onCharacterClicked(character: CharacterVo) {
            val action = CharactersListFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character)
            findNavController().navigate(action)
        }
    }

    private val charactersOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = linearLayoutManager.childCount
            val totalItemCount = linearLayoutManager.itemCount
            val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

            if (isLoadingMore) {
                if (totalItemCount > previousTotal) {
                    isLoadingMore = false
                    previousTotal = totalItemCount
                }
            }
            if (!isLoadingMore && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + endlessOffset)
            ) {
                viewModel.getCharacters()
                isLoadingMore = true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        setupCharactersRecycler()
        getCharactersList()
    }

    private fun initObservers() {
        viewModel.characters.observe(viewLifecycleOwner, { result ->
            if (result != null) {
                handleDataState(
                    resourceState = result,
                    successView = binding.rvCharactersList,
                    loadingView = binding.clProgress.root,
                    errorView = binding.clError.root,
                    errorMessageView = binding.clError.tvError,
                    errorButtonRetryView = binding.clError.btnRetry,
                    retryFunction = { viewModel.getCharacters() }
                )
            }
        })
    }

    private fun getCharactersList() {
        if (viewModel.characters.value == null) {
            viewModel.getCharacters()
        }
    }

    private fun setupCharactersRecycler() {
        linearLayoutManager = LinearLayoutManager(context)
        charactersListAdapter.setCharactersAdapterListener(charactersAdapterListener)
        with(binding.rvCharactersList) {
            layoutManager = linearLayoutManager
            adapter = charactersListAdapter
            addOnScrollListener(charactersOnScrollListener)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun showResult(result: Any?) {
        (result as? List<CharacterBo>)?.let {
            with(charactersListAdapter) {
                characters = it.toVo()
                submitList(characters)
            }
        }
    }
}
