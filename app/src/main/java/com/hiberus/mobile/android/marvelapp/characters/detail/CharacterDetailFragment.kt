package com.hiberus.mobile.android.marvelapp.characters.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.marvelapp.databinding.FragmentCharacterDetailBinding
import com.hiberus.mobile.android.marvelapp.util.ImageVariant
import com.hiberus.mobile.android.marvelapp.util.getImageUrl
import com.hiberus.mobile.android.marvelapp.util.loadImage

class CharacterDetailFragment : Fragment() {

    companion object {
        private const val CHARACTER_EXTRA = "CHARACTER_EXTRA"
    }

    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getParcelable(CHARACTER_EXTRA) as? CharacterVo)?.apply {
            setupCharacter(this)
        }
    }

    private fun setupCharacter(character: CharacterVo) {
        context?.let { ctx ->
            binding.ivCharacterDetailImage.loadImage(ctx, character.thumbnailUrl)
        }

        binding.tvCharacterDetailName.text = character.name

        if (character.description.isNotEmpty()) {
            binding.tvCharacterDetailDescription.text = character.description
        } else {
            binding.tvCharacterDetailDescriptionLabel.visibility = View.GONE
            binding.tvCharacterDetailDescription.visibility = View.GONE
        }
    }
}