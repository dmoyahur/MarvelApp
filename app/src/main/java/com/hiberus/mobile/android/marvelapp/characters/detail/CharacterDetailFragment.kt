package com.hiberus.mobile.android.marvelapp.characters.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.hiberus.mobile.android.marvelapp.characters.mapper.toVo
import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.marvelapp.characters.vo.ComicVo
import com.hiberus.mobile.android.marvelapp.common.BaseFragment
import com.hiberus.mobile.android.marvelapp.common.model.ResourceState
import com.hiberus.mobile.android.marvelapp.databinding.FragmentCharacterDetailBinding
import com.hiberus.mobile.android.marvelapp.util.ImageVariant
import com.hiberus.mobile.android.marvelapp.util.getImageUrl
import com.hiberus.mobile.android.marvelapp.util.isNetworkAvailable
import com.hiberus.mobile.android.marvelapp.util.loadImage
import com.hiberus.mobile.android.marvelapp.util.setVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModel()
    private lateinit var character: CharacterVo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = CharacterDetailFragmentArgs.fromBundle(it)
            character = safeArgs.character

            configureToolbar()
            initObservers()
            getCharacterDetail()
        }
    }

    private fun configureToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.title = character.name
    }

    private fun initObservers() {
        viewModel.characterDetail.observe(viewLifecycleOwner, { result ->
            val resourceState = if (result is ResourceState.Success) {
                ResourceState.Success(result.data?.toVo())
            } else {
                result
            }

            handleDataState(
                resourceState = resourceState,
                successView = binding.svCharacterDetail,
                loadingView = binding.clProgress.root,
                errorView = binding.clError.root,
                errorMessageView = binding.clError.tvError,
                errorButtonRetryView = binding.clError.btnRetry,
                retryFunction = { viewModel.getCharacterDetail(character.id) }
            )
        })
    }

    private fun getCharacterDetail() {
        if (isNetworkAvailable(context)) {
            viewModel.getCharacterDetail(character.id)
        } else {
            showResult(character)
        }
    }

    override fun showResult(result: Any?) {
        (result as? CharacterVo)?.let { characterDetail ->
            context?.let { ctx ->
                val imageUrl = getImageUrl(characterDetail.thumbnailPath,
                    characterDetail.thumbnailExtension, ImageVariant.LANDSCAPE_MEDIUM.variant)
                binding.ivCharacterDetailImage.loadImage(ctx, imageUrl)
            }

            if (characterDetail.description.isNotEmpty()) {
                binding.tvCharacterDetailDescriptionLabel.setVisible(true)
                binding.tvCharacterDetailDescription.setVisible(true)
                binding.tvCharacterDetailDescription.text = characterDetail.description
            }

            if (characterDetail.comics.isNotEmpty()) {
                binding.tvCharacterDetailComicsLabel.setVisible(true)
                binding.tvCharacterDetailComics.setVisible(true)
                binding.tvCharacterDetailComics.text = getComicsTextList(characterDetail.comics)
            }
        }
    }

    private fun getComicsTextList(comics: List<ComicVo>): String {
        var comicsTextList = ""
        comics.forEachIndexed { index, comic ->
            comicsTextList += comic.name
            if (index < comics.size - 1) {
                comicsTextList += "\n"
            }
        }

        return comicsTextList
    }
}
