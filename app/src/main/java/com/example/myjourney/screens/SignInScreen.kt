package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjourney.R
import com.example.myjourney.data.UserData
import com.example.myjourney.screens.ui.theme.MyJourneyTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context
private lateinit var auth: FirebaseAuth
private var mAuth = FirebaseAuth.getInstance()

class SignInScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    auth = FirebaseAuth.getInstance()
                    context = LocalContext.current
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("822578261694-vsbbdi0ppijrit24o60bie8vepn16j84.apps.googleusercontent.com")
                        .requestEmail()
                        .build()
                    val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color.Transparent,
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.user_sign_in),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(180.dp)
                                .fillMaxWidth(),

                            )

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                            ),
                            modifier = Modifier
                                .width(220.dp)
                                .border(1.dp, Color.Black, RoundedCornerShape(30.dp))
                                .padding(5.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = {
                                    val signInIntent = mGoogleSignInClient.signInIntent
                                    startActivityForResult(signInIntent, 1)
                                }) {
                                    Image(
                                        painterResource(id = R.drawable.google),
                                        contentDescription = null
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "Sign in with Google")
                            }
                        }
                    }
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken)
                Log.d("TAG", "onActivityResult: ")
            } catch (e: ApiException) {
                Log.d("TAG", "error: $e")

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    val userData = UserData(
                        user?.displayName,
                        user?.uid,
                        user?.email,
                        user?.photoUrl.toString()
                    )
                    Toast.makeText(context, "Successfully signed in!", Toast.LENGTH_SHORT)
                        .show()
                    val reference = Firebase.database.reference.child("users")
                    var b = true
                    reference.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val children = snapshot.children
                            children.forEach {
                                val user = it.getValue(UserData::class.java)
                                if (user != null && user.uid == userData.uid) {
                                    b = false
                                }
                            }
                            if (b) {
                                setUser(userData)
                            }

                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.d("TAG", "onCancelled: ${error.message}")
                        }

                    })

                    val i = Intent(this, HomeScreen::class.java)
                    i.putExtra("uid", userData.uid)
                    i.putExtra("userEmail", userData.email)
                    i.putExtra("userName", userData.name)
                    i.putExtra("userPhoto", userData.photo)
                    startActivity(i)


                } else {
                    Log.d("TAG", "error: Authentication Failed.")
                }
            }
    }

    private fun setUser(userData: UserData) {
        val userIdReference = Firebase.database.reference
            .child("users").child(userData.uid ?: "")
        userIdReference.setValue(userData).addOnSuccessListener {

            val i = Intent(this, HomeScreen::class.java)
            i.putExtra("uid", userData.uid)
            startActivity(i)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginView() {
    MyJourneyTheme {

    }
}