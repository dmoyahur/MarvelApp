package com.hiberus.mobile.android.marvelapp.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiberus.mobile.android.marvelapp.R
import com.hiberus.mobile.android.marvelapp.characters.mapper.toVo
import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.marvelapp.databinding.FragmentCharactersBinding
import com.hiberus.mobile.android.marvelapp.util.OnCharacterClickListener
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.model.characters.error.AsyncError
import com.hiberus.mobile.android.repository.util.AsyncResult
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment : Fragment() {

    companion object {
        private const val CHARACTER_EXTRA = "CHARACTER_EXTRA"
    }

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersListViewModel by viewModel()
    private val charactersListAdapter: CharactersListAdapter by inject()
    private val charactersAdapterListener = object: OnCharacterClickListener {
        override fun onCharacterClicked(character: CharacterVo) {
            val bundle = Bundle().apply {
                putParcelable(CHARACTER_EXTRA, character)
            }
            findNavController().navigate(R.id.action_charactersFragment_to_characterDetailFragment, bundle)
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

        setupCharactersRecycler()

        viewModel.characters.observe(viewLifecycleOwner, {
            handleDataState(it)
        })
    }

    private fun setupCharactersRecycler() {
        charactersListAdapter.setCharactersAdapterListener(charactersAdapterListener)
        binding.charactersList.layoutManager = LinearLayoutManager(context)
        binding.charactersList.adapter = charactersListAdapter
    }

    private fun handleDataState(asyncResult: AsyncResult<List<CharacterBo>>) {
        when (asyncResult) {
            is AsyncResult.Loading -> showLoading(true)
            is AsyncResult.Success -> {
                val charactersList = asyncResult.data
                if (charactersList != null && charactersList.isNotEmpty()) {
                    val result = charactersList.toVo()
                    showResult(result)
                }
            }
            is AsyncResult.Error -> showError(asyncResult.error)
        }
    }

    private fun showLoading(showLoading: Boolean) {
        if (showLoading) {
            binding.clProgress.root.visibility = View.VISIBLE
        } else {
            binding.clProgress.root.visibility = View.GONE
        }
    }

    private fun showResult(result: List<CharacterVo>) {
        showLoading(false)
        charactersListAdapter.characters = result
        charactersListAdapter.notifyDataSetChanged()
    }

    private fun showError(asyncError: AsyncError) {
        showLoading(false)
        binding.clError.root.visibility = View.VISIBLE
        binding.clError.tvError.text = asyncError.debugMessage
    }
}