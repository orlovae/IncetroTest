package ru.alexandrorlov.incetrotest.data.network.model

import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("currentPage")
    val currentPage: Int? = null,
    @SerializedName("pageCount")
    val pageCount: Int? = null,
    @SerializedName("perPage")
    val perPage: Int? = null,
    @SerializedName("totalCount")
    val totalCount: Int? = null
)