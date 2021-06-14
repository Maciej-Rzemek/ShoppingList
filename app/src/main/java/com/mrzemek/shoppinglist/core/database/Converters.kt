package com.mrzemek.shoppinglist.core.database

import androidx.room.TypeConverter
import java.util.*

class Converters {

    companion object {
        @JvmStatic
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return value?.let { Date(it) }
        }

        @JvmStatic
        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }
    }

}