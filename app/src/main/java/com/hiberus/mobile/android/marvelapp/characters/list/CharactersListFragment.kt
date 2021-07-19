package com.hiberus.mobile.android.marvelapp.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiberus.mobile.android.marvelapp.BaseFragment
import com.hiberus.mobile.android.marvelapp.characters.mapper.toVo
import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.marvelapp.databinding.FragmentCharactersBinding
import com.hiberus.mobile.android.marvelapp.util.OnCharacterClickListener
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment : BaseFragment() {

    companion object {
        private const val endlessOffset = 5
    }

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersListViewModel by viewModel()
    private val charactersListAdapter: CharactersListAdapter by inject()
    private lateinit var layoutManager: LinearLayoutManager
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

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

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
                    asyncResult = result,
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
        layoutManager = LinearLayoutManager(context)
        binding.rvCharactersList.layoutManager = layoutManager
        charactersListAdapter.setCharactersAdapterListener(charactersAdapterListener)
        binding.rvCharactersList.adapter = charactersListAdapter
        binding.rvCharactersList.addOnScrollListener(charactersOnScrollListener)
    }

    @Suppress("UNCHECKED_CAST")
    override fun showResult(result: Any?) {
        (result as? List<CharacterBo>)?.apply {
            charactersListAdapter.characters = this.toVo()
            charactersListAdapter.notifyDataSetChanged()
        }
    }
}