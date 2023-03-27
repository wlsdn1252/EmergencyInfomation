package com.example.emergencyinfomation

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        // 스피너ui 구현 부분
        bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )
        
        //캘린더 ui 구현
        birthdateLayer.setOnClickListener{
            val listener = OnDateSetListener{ _, year, month, dayOfMonth ->
                birthdateTextView.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000,
                1,
                1
            ).show()
        }

        //체크박스 구현
        warningCheckBox.setOnCheckedChangeListener { _, isChecked ->
            warningEditText.isVisible = isChecked
        }
        // 체크박스 기본값 세팅
        warningEditText.isVisible = warningCheckBox.isChecked

        // 저장버튼 클릭시 데이터 저장
        saveButton.setOnClickListener{
            saveDate()
            finish()
        }

    }
    // 저장버튼 클릭시 데이터 저장 함수
    private fun saveDate(){
        // 코드의 반복을 줄이기 위해 whit()을 쓴다.
        // USER_INFORMATION는 Const.kt파일에 선언해놨음
        // 값을 가져오는게 항상 똑같으니까 미리 상수처리를 한거???
        with( getSharedPreferences("USER_INFORMATION",Context.MODE_PRIVATE).edit()){
            putString(NAME,nameEditText.text.toString())
            putString(BLOOD_TYPE,getBloodType())
            putString(EMERGENCY_CONTACT,emergencyContactEditText.text.toString())
            putString(BIRTHDATE, birthdateTextView.text.toString())
            putString(WARNING,getWarning())
            apply()
        }
        Toast.makeText(this, "저장완료", Toast.LENGTH_SHORT).show()
    }

    // 혈액형 데이터를 받기 위한 함수
    private fun getBloodType() :String {
        val bloodAlphabet = bloodTypeSpinner.selectedItem.toString()
        val bloodSign = if(bloodTypePlus.isChecked) "+" else "-"
        return "$bloodSign$bloodAlphabet"
    }

    //
    private fun getWarning() : String{
        return if(warningCheckBox.isChecked) warningEditText.text.toString() else " "
    }

}