package ru.alexandrorlov.incetrotest.data.network.model

import com.google.gson.annotations.SerializedName

data class OrganizationRemote(
    @SerializedName("averageCheck")
    private val _averageCheck: List<Double?>?,
    @SerializedName("cuisines")
    private val _cuisines: List<String?>?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("isFavorite")
    private val _isFavorite: Boolean?,
    @SerializedName("name")
    private val _name: String?,
    @SerializedName("photo")
    private val _photo: String?,
    @SerializedName("rate")
    private val _rate: String?,
) {
    val averageCheck: List<Double>
        get() = _averageCheck?.filterNotNull() ?: emptyList()

    val cuisines: List<String>
        get() = _cuisines?.filterNotNull() ?: emptyList()

    val isFavorite: Boolean
        get() = _isFavorite ?: false

    val name: String
        get() = _name ?: ""

    val photo: String
        get() = _photo ?: ""

    val rate: String
        get() = _rate ?: ""
}
