package com.example.emergencyinfomation

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
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

    }

}