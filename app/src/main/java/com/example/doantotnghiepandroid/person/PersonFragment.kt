package com.example.doantotnghiepandroid.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.doantotnghiepandroid.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home_detail.*
import kotlinx.android.synthetic.main.fragment_person.*


class PersonFragment : Fragment(){
    val mGoogleSignInClient : GoogleSignInClient ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person,container,false)
    } // man` nao di toi' comment nhi

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signIn()
        onClick()
    }
    private fun signIn(){
        val acct = GoogleSignIn.getLastSignedInAccount(activity)
        val personName = acct?.displayName
        val personGivenName = acct?.givenName
        val personFamilyName = acct?.familyName
        val personEmail = acct?.email
        val personId = acct?.id
        val personPhoto = acct?.photoUrl
        tvUser.text = personName
        Glide.with(this).load(personPhoto).into(imgPerson)
    }
            private fun signOut() {
                mGoogleSignInClient?.signOut()?.addOnCompleteListener(requireActivity()
                    ) {

                    }
            }
    private fun onClick() {
        tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_nav_person_to_loginFragment)
        }
        tvLogout.setOnClickListener {
            signOut()
        }
    }
}