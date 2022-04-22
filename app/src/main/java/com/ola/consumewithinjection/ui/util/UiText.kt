package com.ola.consumewithinjection.ui.util

import android.content.Context

sealed class UiText {
    data class StringResource(val resId: Int): UiText()
    data class DynamicString(val message: String): UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> message
            is StringResource -> context.getString(resId)
        }
    }
}
