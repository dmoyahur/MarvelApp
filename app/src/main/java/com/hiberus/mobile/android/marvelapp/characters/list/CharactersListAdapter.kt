package com.hiberus.mobile.android.marvelapp.characters.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.marvelapp.databinding.RowCharacterBinding
import com.hiberus.mobile.android.marvelapp.util.ImageVariant
import com.hiberus.mobile.android.marvelapp.util.OnCharacterClickListener
import com.hiberus.mobile.android.marvelapp.util.getImageUrl
import com.hiberus.mobile.android.marvelapp.util.loadImage

class CharactersListAdapter : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {

    var characters: List<CharacterVo> = mutableListOf()
    private var charactersAdapterListener: OnCharacterClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = RowCharacterBinding.inflate(LayoutInflater
            .from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun setCharactersAdapterListener(onCharacterClickListener: OnCharacterClickListener) {
        this.charactersAdapterListener = onCharacterClickListener
    }

    inner class ViewHolder(private val itemBinding: RowCharacterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            val character = characters[position]
            val imageUrl = getImageUrl(
                character.thumbnailPath,
                character.thumbnailExtension,
                ImageVariant.PORTRAIT_SMALL.variant
            )

            itemBinding.ivRowCharacterImage.loadImage(
                itemBinding.root.context,
                imageUrl,
                RequestOptions.circleCropTransform()
            )
            itemBinding.tvRowCharacterName.text = character.name

            itemBinding.root.setOnClickListener {
                charactersAdapterListener?.onCharacterClicked(character)
            }
        }
    }
}