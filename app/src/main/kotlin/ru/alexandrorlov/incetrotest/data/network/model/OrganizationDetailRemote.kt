package ru.alexandrorlov.incetrotest.data.network.model


import com.google.gson.annotations.SerializedName

data class OrganizationDetailRemote(
    @SerializedName("averageCheck")
    val averageCheck: List<Any> = emptyList(),
    @SerializedName("categoryName")
    val categoryName: String = "",
    @SerializedName("cuisines")
    val cuisines: List<Any> = emptyList(),
    @SerializedName("detailedInfo")
    val detailedInfo: String = "",
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("distance")
    val distance: Any? = null,
    @SerializedName("email")
    val email: String = "",
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("isFavorite")
    val isFavorite: Boolean? = null,
    @SerializedName("location")
    val location: LocationRemote? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("phones")
    val phones: List<String> = emptyList(),
    @SerializedName("photos")
    val photos: List<String> = emptyList(),
    @SerializedName("rate")
    val rate: String = "",
    @SerializedName("rateCount")
    val rateCount: Int? = null,
    @SerializedName("review")
    val review: Any? = null,
    @SerializedName("reviewCount")
    val reviewCount: Int? = null,
    @SerializedName("schedule")
    val schedule: List<ScheduleRemote> = emptyList(),
    @SerializedName("serviceLanguages")
    val serviceLanguages: List<String> = emptyList(),
    @SerializedName("services")
    val services: List<Any> = emptyList(),
    @SerializedName("socials")
    val socials: List<SocialRemote> = emptyList(),
    @SerializedName("urls")
    val urls: List<String> = emptyList(),
)