package com.elf.square.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data model representing a GitHub Repository.
 */
data class Repository(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?
)
