package sananismayilov.au.myapplication.roomdb

import People
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import sananismayilov.au.myapplication.data.PeopleEntity

@Dao
interface PeopleDao {
    @Insert
    suspend fun insertPeople(peopleEntity: PeopleEntity)

    @Query("SELECT * FROM People ")
    suspend fun getAllPeople(): List<PeopleEntity>

    @Query("DELETE FROM People")
    suspend fun deleteAllPeople()

    @Query("SELECT * FROM People WHERE countryId = :countryId")
    suspend fun getPeopleByCountry(countryId: Int): List<PeopleEntity>

}

