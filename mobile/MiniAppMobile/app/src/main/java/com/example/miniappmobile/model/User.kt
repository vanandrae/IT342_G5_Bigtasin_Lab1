package com.example.miniappmobile.model

data class User(
    val id: Long? = null,
    val username: String,    // This MUST be here to fix the red error
    val password: String,
    val email: String,
    val fullName: String? = null,
    val role: String? = "USER"
)