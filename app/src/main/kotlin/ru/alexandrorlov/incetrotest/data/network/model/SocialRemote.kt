package ru.alexandrorlov.incetrotest.data.network.model


import com.google.gson.annotations.SerializedName

data class SocialRemote(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("organization")
    val idOrganization: Long? = null,
    @SerializedName("type")
    val type: Int? = null,
    @SerializedName("url")
    val url: String = ""
)