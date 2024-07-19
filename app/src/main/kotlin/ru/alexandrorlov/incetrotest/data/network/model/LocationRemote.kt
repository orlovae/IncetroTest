package ru.alexandrorlov.incetrotest.data.network.model


import com.google.gson.annotations.SerializedName

data class LocationRemote(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("district")
    val district: Int? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("latitude")
    val latitude: Double? = null,
    @SerializedName("longitude")
    val longitude: Double? = null,
    @SerializedName("organization")
    val idOrganization: Long? = null
)