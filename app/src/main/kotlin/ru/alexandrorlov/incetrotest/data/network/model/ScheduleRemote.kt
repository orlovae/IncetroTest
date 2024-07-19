package ru.alexandrorlov.incetrotest.data.network.model


import com.google.gson.annotations.SerializedName

data class ScheduleRemote(
    @SerializedName("day")
    val day: Int? = null,
    @SerializedName("end")
    val end: Long? = null,
    @SerializedName("start")
    val start: Long? = null
)