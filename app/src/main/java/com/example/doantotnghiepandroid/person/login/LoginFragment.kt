package com.example.doantotnghiepandroid.person.login

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.doantotnghiepandroid.R
import com.example.doantotnghiepandroid.activity.MainActivity
import com.example.doantotnghiepandroid.home.model.AccountManager
import com.example.doantotnghiepandroid.home.model.User
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(), GoogleApiClient.OnConnectionFailedListener{


    private var googleApiClient: GoogleApiClient? = null
    private val RC_SIGN_IN = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        btnLoginGmail.setOnClickListener {
            loginGmail()
        }
    }
    private fun loginGmail() {
        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestEmail()
            .build()
        googleApiClient = context?.let {
            GoogleApiClient.Builder(it)
                .enableAutoManage(context as FragmentActivity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
        }

        btnLoginGmail.setOnClickListener {
            val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }
    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }
    private fun gotoHome() {
        startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)
            val user = account?.let { User(it.email?:"", it.displayName?:"", it.photoUrl.toString()) }
            AccountManager.user = user
            findNavController().popBackStack()
        }
        catch (e: ApiException) {

        }
    }
}