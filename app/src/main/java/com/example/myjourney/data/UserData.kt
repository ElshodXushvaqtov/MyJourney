package com.example.myjourney.data

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserPanel {
    companion object {
        private val auth = FirebaseAuth.getInstance()
        private val users = FirebaseDatabase.getInstance().reference.child("users")
        fun getUserSaved(context: Context): String {
            val preferences = context.getSharedPreferences("myShared", Context.MODE_PRIVATE)
            return preferences.getString("user", "") ?: ""
        }

        fun FavouriteGet(callback: (List<String>) -> Unit) {
            users.child(auth.currentUser!!.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val retrievedUser = dataSnapshot.getValue(UserData::class.java)
                        if (retrievedUser != null) {
                            callback(retrievedUser.favourites)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
        }

        fun FavouritesCreate(name: String) {
            users.child(auth.currentUser!!.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val retrievedUser = dataSnapshot.getValue(UserData::class.java)

                        if (retrievedUser != null && !(retrievedUser.favourites.contains(name))) {
                            retrievedUser.favourites = retrievedUser.favourites.plus(name)
                        }
                        users.child(auth.currentUser!!.uid).setValue(retrievedUser)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
        }


        fun FavouritesDelete(name: String) {
            users.child(auth.currentUser!!.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val retrievedUser = dataSnapshot.getValue(UserData::class.java)

                        if (retrievedUser != null) {
                            retrievedUser.favourites = retrievedUser.favourites.minus(name)
                        }
                        users.child(auth.currentUser!!.uid).setValue(retrievedUser)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
        }

        fun isFavourite(place_name: String, callback: (Boolean) -> Unit) {
            users.child(auth.currentUser!!.uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val retrievedUser = dataSnapshot.getValue(UserData::class.java)
                    val favourites = retrievedUser!!.favourites
                    var flag = false
                    if (dataSnapshot.exists()) {
                            if (favourites.contains(place_name)) {
                                flag = true
                                callback(true)
                            }
                        if (!flag) callback(false)
                    } else {
                        callback(false)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(false)
                }
            }
            )
        }

    }
}