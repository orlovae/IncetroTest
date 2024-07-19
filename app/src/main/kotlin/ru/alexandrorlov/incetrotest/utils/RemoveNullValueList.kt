package ru.alexandrorlov.incetrotest.utils

fun <T> removeNullValueList(list: List<T?>): List<T> {
    val outList = mutableListOf<T>()
    list.forEach {
        if (it != null) {
            outList.add(it)
        }
    }
    return mutableListOf()
}