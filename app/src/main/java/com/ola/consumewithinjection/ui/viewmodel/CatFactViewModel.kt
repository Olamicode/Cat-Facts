package com.ola.consumewithinjection.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ola.consumewithinjection.data.repository.CatFactRepository
import com.ola.consumewithinjection.ui.event.Event
import com.ola.consumewithinjection.ui.util.UiText
import com.ola.consumewithinjection.ui.viewstate.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatFactViewModel @Inject constructor(
    private val repository: CatFactRepository
): ViewModel() {

    val event = Channel<Event>(Channel.UNLIMITED)

    var state: MutableStateFlow<State> = MutableStateFlow(State.Idle)
        private set

    init {
        onEvent()
    }

   private fun onEvent () {
        viewModelScope.launch {
            event.consumeAsFlow().collectLatest{ event ->
                when (event) {
                    is Event.FetchCatFacts -> {
                        getCatFacts(event.amount)
                    }
                }
            }
        }

    }

    private fun getCatFacts(amount: Int) {
        viewModelScope.launch {
            state.value = State.Loading
          val response =  repository.getCatFact(amount)
            response.onSuccess {
                state.value = State.GetCatFacts(it)
            }
            response.onFailure {
                state.value = State.Error(UiText.DynamicString(it.localizedMessage))
            }
        }
    }

}