package com.ola.consumewithinjection.ui.event

sealed class Event {
    data class FetchCatFacts(val amount: Int) : Event()
}