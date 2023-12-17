package sananismayilov.au.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("People")
data class PeopleEntity(
    @PrimaryKey
    val humanId: Int,
    val name: String,
    val surname: String,
    val cityId: Int,
    val countryId: Int
)