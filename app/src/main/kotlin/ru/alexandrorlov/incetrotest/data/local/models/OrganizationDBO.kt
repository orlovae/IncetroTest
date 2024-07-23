package ru.alexandrorlov.incetrotest.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organizations")
data class OrganizationDBO(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("averageCheck")
    val averageCheck: List<Double>,
    @ColumnInfo("cuisines")
    val cuisines: List<String>,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("photo")
    val photo: String,
    @ColumnInfo("rate")
    val rate: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("detailPhotoList")
    val detailPhotoList: List<String>,
)
