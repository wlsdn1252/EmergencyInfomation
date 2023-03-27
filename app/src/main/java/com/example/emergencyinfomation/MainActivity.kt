package com.example.emergencyinfomation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goInputActivityButton.setOnClickListener{
            val intent = Intent(this, EditActivity :: class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        getDataAndUiUpdate()
    }
    // 저장한 데이터 받아오기
    private fun getDataAndUiUpdate(){
        // USER_INFORMATION는 Const.kt파일에 선언해놨음
        // 값을 가져오는게 항상 똑같으니까 미리 상수처리를 한거???
        with(getSharedPreferences(USER_INFORMATION,Context.MODE_PRIVATE)){
            nameValueTextView.text = getString(NAME,"미정")
            birthdateValueTextView.text = getString(BIRTHDATE,"미정")
            bloodTypeValueTextView.text = getString(BLOOD_TYPE,"미정")
            emergencyContactTextView.text = getString(EMERGENCY_CONTACT,"미정")
            val warnign = getString(WARNING, "")

            warningTextView.isVisible = warnign.isNullOrEmpty().not()
            warningValueTextView.isVisible = warnign.isNullOrEmpty().not()

            if(!warnign.isNullOrEmpty()){
                warningValueTextView.text = warnign
            }


        }
    }
}