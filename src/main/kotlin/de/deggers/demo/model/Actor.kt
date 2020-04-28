package de.deggers.demo.model

data class Actor(val firstName: String, val lastName: String) {
    val displayName = "$firstName $lastName"
}
