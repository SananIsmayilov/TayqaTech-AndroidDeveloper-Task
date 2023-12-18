package sananismayilov.au.myapplication.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sananismayilov.au.myapplication.data.PeopleEntity

@Database(entities = [PeopleEntity::class], version = 1)
abstract class PeopleDB : RoomDatabase() {
    abstract fun getDao(): PeopleDao

}