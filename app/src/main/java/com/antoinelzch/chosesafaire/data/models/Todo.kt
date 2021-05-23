package com.antoinelzch.chosesafaire.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChosesAFaire")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var isChecked: Boolean
)
