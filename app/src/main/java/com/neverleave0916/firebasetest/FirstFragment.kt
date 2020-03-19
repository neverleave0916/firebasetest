package com.neverleave0916.firebasetest

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        auth = FirebaseAuth.getInstance()
        button.setOnClickListener { getUserProfile() }
        button3.setOnClickListener { onClick() }
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }

    private fun onClick() {
        Toast.makeText(activity,"登入中..", Toast.LENGTH_SHORT).show()
        var email=et1.text.toString()
        var password =et2.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener((context as Activity?)!!) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(activity,"登入成功", Toast.LENGTH_SHORT).show()

                    val user = auth.currentUser
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(activity,"登入失敗", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun getUserProfile() {
        // [START get_user_profile]
        Toast.makeText(activity,"使用者資料", Toast.LENGTH_SHORT).show()
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name1 = user.displayName
            textView.text=name1.toString()
            val email1 = user.email
            textView2.text=email1.toString()
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
        // [END get_user_profile]
    }
}
