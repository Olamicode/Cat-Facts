package com.ola.consumewithinjection.ui.viewstate

import com.ola.consumewithinjection.data.remote.dto.CatFact
import com.ola.consumewithinjection.ui.util.UiText

sealed class State {
    object Idle: State()
    object Loading: State()
    data class GetCatFacts(val catFacts: List<CatFact>): State()
    data class Error(val message : UiText): State()
}