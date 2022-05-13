package com.webecom.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.webecom.R
import com.webecom.utils.Utils
import kotlinx.android.synthetic.main.activity_forgot.*
import java.util.*


class ForgotPassword : AppCompatActivity(), View.OnClickListener {

private lateinit var edtEmail:EditText
    private lateinit var cardForgot: CardView
    private val map = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        initialize()
        initObservables()
    }

    private fun initialize() {
        edtEmail=findViewById(R.id.edtEmail)
        cardForgot=findViewById(R.id.cardForgot)
        cardForgot.setOnClickListener(this)
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
            R.id.cardForgot -> {
                checkValidation()
            }
            else -> {
            }
        }
    }

    private fun checkValidation() {
        val email = edtEmail.text
        if (email.isEmpty()) {
            edtEmail.error = "Please Enter Your Email ID"
            edtEmail.requestFocus()
        } else if (!Utils.isValidEmail(email.toString())) {
            edtEmail.error = "Please Enter Your valid Email ID"
            edtEmail.requestFocus()
        }else {
            finish()
        }
    }
}