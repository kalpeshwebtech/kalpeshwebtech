package com.webecom.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.webecom.MainActivity
import com.webecom.R
import com.webecom.utils.Utils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*


class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var cardSignUp: CardView

    private val map = HashMap<String, String>()
    private lateinit var edtFName: EditText
    private lateinit var edtLName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initialize()
        initObservables()
    }

    private fun initialize() {
        edtFName=findViewById(R.id.edtFName)
        edtLName=findViewById(R.id.edtLName)
        edtEmail=findViewById(R.id.edtEmail)
        edtPass=findViewById(R.id.edtPass)

        cardSignUp = findViewById(R.id.cardSignUp)
        cardSignUp.setOnClickListener(this)
        lySignIn.setOnClickListener(this)
    }

    private fun initObservables() {
//        viewModel?.isLoader?.observe(this, Observer {
//            if (it) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
//        })
//        viewModel?.userLogin?.observe(this, Observer {
//            if (it.IsSuccess) {
//                prefManager.savePreference(IS_LOGIN,true)
//                prefManager.saveUserData(it.Data)
//                if(it.Data.phone==null||it.Data.phone.toString().equals("")){
//                    startActivity(Intent(this@LoginActivity, CompletePro::class.java))
//                }else {
//                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                }
//                finish()
//            }
//        })
//        viewModel?.error?.observe(this, Observer {
//            showMessage(this, it)
//        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.cardSignUp -> {
                checkValidation()
            }
            R.id.lySignIn -> {
                finish()
            }
            else -> {
            }
        }
    }

    private fun checkValidation() {
        val fname=edtFName.text
        val lname=edtLName.text
        val email=edtEmail.text
        val pass=edtPass.text
        if (fname.isEmpty()){
            edtFName.error="Please Enter First Name"
            edtFName.requestFocus()
        }else if (lname.isEmpty()){
            edtLName.error="Please Enter Last Name"
            edtLName.requestFocus()
        }else  if (email.isEmpty()) {
            edtEmail.error = "Please Enter Your Email ID"
            edtEmail.requestFocus()
        } else if (!Utils.isValidEmail(email.toString())) {
            edtEmail.error = "Please Enter Your valid Email ID"
            edtEmail.requestFocus()
        }else if (pass.isEmpty()){
            edtPass.error="Please Enter Password"
            edtPass.requestFocus()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}