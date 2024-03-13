package com.example.myjourney.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PlaceData {

    companion object {

        val places = FirebaseDatabase.getInstance().reference.child("places")

        fun GetPlaces(callback: (List<Places>) -> Unit) {

            places.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val places = mutableListOf<Places>()
                    snapshot.children.forEach {
                        val place = it.getValue(Places::class.java)
                        if (place != null) {
                            place.name?.let { it1 -> Log.d("AAA", it1) }
                        }
                        if (place != null) {
                            places.add(place)
                        }
                    }
                    callback(places)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(emptyList())
                }

            })


        }

        fun GetPlace(name: String, callback: (Places) -> Unit) {

            places.child(name).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val place = snapshot.getValue(Places::class.java)
                    if (place != null) {
                        callback(place)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(Places("", "", "", "", arrayOf("")))
                }
            })

        }

    }

}