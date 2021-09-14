package com.hiberus.mobile.android.marvelapp.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiberus.mobile.android.domain.characters.CharacterDetailUseCase
import com.hiberus.mobile.android.marvelapp.common.model.ResourceState
import com.hiberus.mobile.android.marvelapp.common.model.ResourceState.Companion.toResourceState
import com.hiberus.mobile.android.model.characters.CharacterBo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(
    private val characterDetailUseCase: CharacterDetailUseCase
) : ViewModel() {

    private var _characterDetail = MutableLiveData<ResourceState<CharacterBo>>()
    val characterDetail: LiveData<ResourceState<CharacterBo>>
        get() = _characterDetail

    fun getCharacterDetail(id: Int) {
        _characterDetail.value = ResourceState.Loading()
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                characterDetailUseCase(id).collect { result ->
                    _characterDetail.value = result.toResourceState()
                }
            }
        }
    }
}