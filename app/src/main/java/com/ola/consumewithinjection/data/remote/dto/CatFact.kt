package com.ola.consumewithinjection.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CatFact(
    @SerializedName("_id")
    val id: String,
    @SerializedName("__v")
    val v: String,
    val text: String,
    val updatedAt: String,
    val deleted: Boolean,
    val source: String,
    val sentCount: Int
)
