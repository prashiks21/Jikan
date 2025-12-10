package com.jikanAPI.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jikanAPI.data.local.entity.AnimeEntity

@Database(
    entities = [AnimeEntity::class],
    version = 1
)
abstract class AnimeDatabase : RoomDatabase() {
    abstract val dao: AnimeDao
}
