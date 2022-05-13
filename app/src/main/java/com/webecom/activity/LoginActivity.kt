package com.webecom.activity

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.facebook.*
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
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
import com.webecom.utils.Utils.Companion.isInternet
import com.webecom.utils.Utils.Companion.showMessage
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import java.net.URLEncoder.encode
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var callbackManager: CallbackManager? = null
    private lateinit var loginManager: LoginManager
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth

    private val map = HashMap<String, String>()
    private lateinit var cardLogin: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FacebookSdk.sdkInitialize(applicationContext)
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id))
        initialize()
        initObservables()
        checkHashKey()
    }
    fun checkHashKey() {
        val info: PackageInfo
        try {
            info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something: String = String(Base64.encode(md.digest(), 0))
                Log.e("hash_key", something)
            }
        } catch (ignored: PackageManager.NameNotFoundException) {
            Log.e("Name NotFound Exception", ignored.toString())
        } catch (e: NoSuchAlgorithmException) {
            Log.e("no such an algorithm", e.toString())
        } catch (e: java.lang.Exception) {
            Log.e("exception", e.toString())
        }
    }
    private fun initialize() {

//        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
//        prefManager = PrefManager.getInstance(this)
        getSupportActionBar()?.hide()
//        customeProgressDialog = CustomeProgressDialog(this)
        configureGoogleSignIn()
        firebaseAuth = FirebaseAuth.getInstance()
        cardLogin = findViewById(R.id.cardLogin)

        cardLogin.setOnClickListener(this)
        cardGLogin.setOnClickListener(this)
        cardFBLogin.setOnClickListener(this)
        lySignUp.setOnClickListener(this)
        tvForgot.setOnClickListener(this)
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

//    fun onClick(view: View) {
//        if (isInternet(this)) {
//            signIn()
//        } else {
//            startActivity(Intent(this@LoginActivity, NoInternet::class.java))
//        }
//    }

    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                //showMessage(this,"Google sign in failed:")
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.e("account", Gson().toJson(acct))
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user: FirebaseUser = firebaseAuth.getCurrentUser()!!
//                map.put(Prefkeys.NAME, user.displayName.toString())
//                map.put(Prefkeys.EMAIL, user.email.toString())
//                if (user.photoUrl != null)
//                    map.put(Prefkeys.IMAGE, user.photoUrl.toString())
//
//                map.put(Prefkeys.SOCIAL_ID, user.uid)
//                getDeviceInfo(this)?.let { it1 ->
//                    map.put(Prefkeys.DEVICE_INFO, it1)
//                }
//                viewModel.login(
//                    map,
//                    prefManager.getPrefrenceData(Prefkeys.DEVICE_TOKEN)!!
//                )
                showMessage(this,user.displayName.toString()+"\n"+user.email.toString())
                signOut()
                revokeAccess()
                dashBoardRedirect()
            } else {
                showMessage(this,"Google sign in failed:")
            }
        }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener {
                OnCompleteListener<Void?> { }
            }
    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess().addOnCanceledListener {
            OnCompleteListener<Void?> {
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.cardLogin -> {
                checkValidation()
            }
            R.id.cardGLogin -> {
                if (isInternet(this)) {
                    signIn()
                }
            }
            R.id.cardFBLogin -> {
                facebookLogin()
            }
            R.id.tvForgot -> {
                val intent = Intent(this, ForgotPassword::class.java)
                startActivity(intent)
            }
            R.id.lySignUp -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            else -> {
            }
        }
    }

    private fun checkValidation() {
        val email = edtEmail.text
        val password = edtPass.text
        if (email.isEmpty()) {
            edtEmail.error = "Please Enter Your Email ID"
            edtEmail.requestFocus()
        } else if (!Utils.isValidEmail(email.toString())) {
            edtEmail.error = "Please Enter Your valid Email ID"
            edtEmail.requestFocus()
        } else if (password.isEmpty()) {
            edtPass.error = "Please Enter Your Password"
            edtPass.requestFocus()
        } else {
            dashBoardRedirect()
        }
    }
    private fun dashBoardRedirect() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun facebookLogin() {
        loginManager = LoginManager.getInstance()
        callbackManager = CallbackManager.Factory.create()
        loginManager.logInWithReadPermissions(this, Arrays.asList("email", "public_profile", "user_birthday"))

        loginManager.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    val request = GraphRequest.newMeRequest(
                        loginResult.accessToken
                    ) { jsonResponce, response ->
                        if (jsonResponce != null) {
                            try {
                                Log.e("fbfbfb", Gson().toJson(jsonResponce))
                                val fullname = jsonResponce.getString("name")
                                val parts = fullname.split("\\s+").toTypedArray()
                                var fname = ""
                                var lname = ""
                                fname = parts[0]
                                if (parts.size > 1) {
                                    lname = parts[1]
                                }
                                val email = jsonResponce.getString("email")
                                val socialId = jsonResponce.getString("id")
                                var profile_pic = ""
                                if (jsonResponce.has("picture")) {
                                    profile_pic =
                                        jsonResponce.getJSONObject("picture").getJSONObject("data")
                                            .getString("url")
                                }
                                Log.e(
                                    "fblogin",
                                    fname + "--" + lname + "--" + email + "--" + socialId + "--" +
                                            profile_pic
                                )
                                if (AccessToken.getCurrentAccessToken() != null) {
                                    AccessToken.setCurrentAccessToken(null)
                                }
                                disconnectFromFacebook()
                                LoginManager.getInstance().setLoginBehavior(LoginBehavior.WEB_ONLY)
                                    .logOut()
                                showMessage(this@LoginActivity,fullname+"\n"+email+"\n"+socialId)
                                val provider = "facebook"
                                /*socialLoginProcess(
                                    SocialModel(
                                        socialId,
                                        fname,
                                        lname,
                                        email,
                                        provider,
                                        profile_pic
                                    )
                                )*/
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            } catch (e: NullPointerException) {
                                e.printStackTrace()
                            }
                        } else {

                        }
                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "id, name, email, gender, picture.type(large)")
                    request.parameters = parameters
                    request.executeAsync()
                }

                override fun onCancel() {
                    Log.e("LoginScreen", "---onCancel")
                }
                override fun onError(error: FacebookException) {
                    Log.e("LoginScreen", "----onError: " + error.message)
                }
            })
    }
    fun disconnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return
        }
        GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/me/permissions/", null,
            HttpMethod.DELETE
        ) { LoginManager.getInstance().logOut() }.executeAsync()
    }
}