package ru.alexandrorlov.incetrotest.data.network.model

import com.google.gson.annotations.SerializedName

data class Organizations(
    @SerializedName("data")
    val organizationsList: List<OrganizationRemote> = emptyList(),
    @SerializedName("meta")
    val metaData: MetaData? = null
)