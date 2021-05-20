package com.antoinelzch.chosesafaire.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChosesAFaire")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val isChecked: Boolean
)
