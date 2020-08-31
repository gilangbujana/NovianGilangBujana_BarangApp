package com.gilangbujana.noviangilangbujana_androiddeveloper_31082020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gilangbujana.noviangilangbujana_androiddeveloper_31082020.data.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var user = User()
        user.username = "gilangbujana"
        user.password = "30111997"

        btn_login.setOnClickListener {

            if(!et_username.text.equals(user.username) || !et_password.text.equals(user.password)) {
                et_username.error = "username atau password yang anda masukkan salah"
            }else{
                var intent = Intent(this, ListBarangActivity::class.java)
                startActivity(intent)
            }
        }
    }
}