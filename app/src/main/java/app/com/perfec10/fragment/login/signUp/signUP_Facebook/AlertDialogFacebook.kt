package app.com.perfec10.fragment.login.signUp.signUP

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import app.com.perfec10.R
import app.com.perfec10.activity.MainActivity
import kotlinx.android.synthetic.main.term_condetion_fb.*

class AlertDialogFacebook : AppCompatActivity() {
   
   
   var doubleBackToExitPressedOnce = false
   
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.term_condetion_fb)
   
   
   
      tv_later_popup_1.setOnClickListener {
         startActivity(Intent(this, MainActivity::class.java)
          .putExtra("key_termCondition", 2)
          .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
          .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
         this.finish()
      }
   
      tv_yes_popup_1.setOnClickListener {
         
         startActivity(Intent(this, MainActivity::class.java)
          .putExtra("key_termCondition", 1)
          .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
          .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
         this.finish()
      }
      
      
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
