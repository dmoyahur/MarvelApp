package com.hiberus.mobile.android.marvelapp.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiberus.mobile.android.domain.characters.CharacterDetailUseCase
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(
    private val characterDetailUseCase: CharacterDetailUseCase
) : ViewModel() {

    private var _characterDetail = MutableLiveData<AsyncResult<CharacterBo>>()
    val characterDetail: LiveData<AsyncResult<CharacterBo>>
        get() = _characterDetail

    fun getCharacterDetail(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                characterDetailUseCase.invoke(id).collect { result ->
                    _characterDetail.value = result
                }
            }
        }
    }

}