package ru.alexandrorlov.incetrotest.data.local.converters

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromListToString(list: List<String>) : String = list.joinToString(",")

    @TypeConverter
    fun fromStringToList(string: String) : List<String> = string.split(",")

    @TypeConverter
    fun fromListDoubleToString(list: List<Double>) : String = list.joinToString(",")

    @TypeConverter
    fun fromStringToListDouble(string: String) : List<Double> =
        if (string == "") {
            emptyList<Double>()
        } else {
            string
                .split(",")
                .map { it.toDouble() }
        }
}