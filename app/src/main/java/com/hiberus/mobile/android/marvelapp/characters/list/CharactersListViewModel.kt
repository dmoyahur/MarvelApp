package com.hiberus.mobile.android.marvelapp.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiberus.mobile.android.domain.characters.CharactersUseCase
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersListViewModel(
    private val charactersUseCase: CharactersUseCase
) : ViewModel() {

    companion object {
        private const val pageSize = 100
    }

    private var offset = 0

    private var _characters = MutableLiveData<AsyncResult<List<CharacterBo>>>()
    val characters: LiveData<AsyncResult<List<CharacterBo>>>
        get() = _characters

    internal fun getCharacters() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                charactersUseCase.invoke(offset, pageSize).collect { result ->
                    if (result is AsyncResult.Success) {
                        val dataSize = result.data?.size ?: 0
                        if (dataSize > offset) {
                            offset = dataSize
                        }
                    }
                    _characters.value = result
                }
            }
        }
    }
}