package sananismayilov.au.myapplication.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sananismayilov.au.myapplication.data.PeopleEntity

@Database(entities = [PeopleEntity::class], version = 1)
abstract class PeopleDB : RoomDatabase() {
    abstract fun getDao(): PeopleDao

    companion object {
        @Volatile
        private var instance: PeopleDB? = null
        private var lock = Any()

        operator fun invoke (context: Context) = instance ?: synchronized(lock){
            instance?: makeDatabase(context).also {
                instance = it
            }
        }


        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,PeopleDB::class.java,"Notedb"
        ).build()
    }
}