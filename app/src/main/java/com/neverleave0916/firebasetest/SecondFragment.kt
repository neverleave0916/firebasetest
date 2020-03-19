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
import kotlinx.android.synthetic.main.fragment_first.et1
import kotlinx.android.synthetic.main.fragment_first.et2
import kotlinx.android.synthetic.main.fragment_second.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        button2.setOnClickListener { createAccount() }
        auth = FirebaseAuth.getInstance()
    }



    private fun createAccount() {
        Toast.makeText(activity,"註冊中..", Toast.LENGTH_SHORT).show()
        // [START create_user_with_email]
        var email=et1.text.toString()
        var password =et2.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener((context as Activity?)!!) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(activity,"註冊成功", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(activity,"註冊失敗", Toast.LENGTH_SHORT).show()
                    Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
        // [END create_user_with_email]
    }
}
