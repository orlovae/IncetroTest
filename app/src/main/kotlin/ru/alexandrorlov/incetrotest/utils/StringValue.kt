package ru.alexandrorlov.incetrotest.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class StringValue {

    data class DynamicString(val value: String) : StringValue()

    class StringResource(
        @StringRes val resId: Int,
        val args: String = "",
    ) : StringValue()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> {
                if (args.isBlank()) {
                    context.getString(resId)
                } else {
                    "${context.getString(resId)} $args"
                }
            }
        }
    }
}