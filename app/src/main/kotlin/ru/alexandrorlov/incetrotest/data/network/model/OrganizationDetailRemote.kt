package ru.alexandrorlov.incetrotest.data.network.model


import com.google.gson.annotations.SerializedName

data class OrganizationDetailRemote(
    @SerializedName("averageCheck")
    private val _averageCheck: List<Double?>?,
    @SerializedName("categoryName")
    val categoryName: String?,
    @SerializedName("cuisines")
    private val _cuisines: List<String?>?,
    @SerializedName("detailedInfo")
    private val _detailedInfo: String?,
    @SerializedName("discount")
    val discount: Int?,
    @SerializedName("distance")
    val distance: Any?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("isFavorite")
    private val _isFavorite: Boolean?,
    @SerializedName("location")
    val location: LocationRemote?,
    @SerializedName("name")
    private val _name: String?,
    @SerializedName("phones")
    val phones: List<String?>?,
    @SerializedName("photos")
    private val _photos: List<String?>?,
    @SerializedName("rate")
    private val _rate: String?,
    @SerializedName("rateCount")
    val rateCount: Int?,
    @SerializedName("review")
    val review: Any?,
    @SerializedName("reviewCount")
    val reviewCount: Int?,
    @SerializedName("schedule")
    val schedule: List<ScheduleRemote?>?,
    @SerializedName("serviceLanguages")
    val serviceLanguages: List<String?>?,
    @SerializedName("services")
    val services: List<Any?>?,
    @SerializedName("socials")
    val socials: List<SocialRemote?>?,
    @SerializedName("urls")
    val urls: List<String?>?,
) {
    val averageCheck: List<Double>
        get() = _averageCheck?.filterNotNull() ?: emptyList()

    val cuisines: List<String>
        get() = _cuisines?.filterNotNull() ?: emptyList()

    val detailedInfo: String
        get() = _detailedInfo ?: ""

    val isFavorite: Boolean
        get() = _isFavorite ?: false

    val name: String
        get() = _name ?: ""

    val photos: List<String>
        get() = _photos?.filterNotNull() ?: emptyList()

    val rate: String
        get() = _rate ?: ""
}