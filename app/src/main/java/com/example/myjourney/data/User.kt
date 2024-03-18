package com.example.myjourney.data

data class UserData(
    var name: String? = null,
    var uid: String? = null,
    var email: String? = null,
    var photo: String? = null,
    var favourites: List<String>
) {

    constructor() : this(null, null, null, null, emptyList())
}
