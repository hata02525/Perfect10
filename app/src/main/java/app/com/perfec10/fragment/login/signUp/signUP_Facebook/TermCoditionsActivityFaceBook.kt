package app.com.perfec10.fragment.login.signUp.signUP

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import app.com.perfec10.R
import app.com.perfec10.network.NetworkConstants.acceptTermsAndConditions
import app.com.perfec10.util.PreferenceManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_term_coditions.*
import org.json.JSONObject




class TermCoditionsActivityFaceBook : AppCompatActivity() {
   var doubleBackToExitPressedOnce = false
   private lateinit var preferenceManager: PreferenceManager
   val url="http://18.217.249.143/perfec10/termCondition/term.pdf"
   lateinit var mainActivitySignUP: TermCoditionsActivityFaceBook
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_term_coditions)
      mainActivitySignUP =this
      preferenceManager = PreferenceManager(this)
      pdfView.isZooming
      pdfView.fromAsset("term.pdf")
       .load()
      preferenceManager.key_Sesstion = "1"
      
      Log.d("TAHS"," check_accept.isChecked "+check_accept.isClickable)
   
      check_accept.setOnClickListener {v->
         if ((v as CheckBox).isChecked) {
         btn_next.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent))
         btn_next.isEnabled=true
            btn_next.setOnClickListener {
               initJson();
            }
            
      }else{
            preferenceManager.key_Sesstion = "1"
            btn_next.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary))
         btn_next.isEnabled=false
      }
      
   }
   }
   
   
   private fun initJson(){
    
    val dilog= ProgressDialog(this)
      dilog.setMessage("Please wait...")
      dilog.setCancelable(true)
      dilog.show()
      val auth = PreferenceManager(this).userAuthkey
      AndroidNetworking.post(acceptTermsAndConditions).addHeaders("accessToken",auth)
       .build()
       .getAsJSONObject(object: JSONObjectRequestListener {
          override fun onResponse(response: JSONObject?) {
             dilog.dismiss()
             Log.d("TAGS",response!!.toString())
             val s= response.getString("message")
             if(s == "Successful.") {
                preferenceManager.key_Sesstion = "2"
               /* val addFriendAllowDialoge = AddFriendAllowDialogesSignUp(mainActivitySignUP, "email")
                addFriendAllowDialoge.show(mainActivitySignUP.supportFragmentManager, "fsdf")
   */
                 startActivity(Intent(this@TermCoditionsActivityFaceBook, AlertDialogFacebook::class.java)
                 .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                 .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                  finish()
                
             }
          }
   
          override fun onError(anError: ANError?) {
             dilog.dismiss()
          }
       })
   }
   
   
   override fun onBackPressed() {
      if (doubleBackToExitPressedOnce) {
         super.onBackPressed()
         return
      }
      
      this.doubleBackToExitPressedOnce = true
      Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
      Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
   }
}
