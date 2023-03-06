package my.edu.tarc.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.insurance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener{
            var basic: Int = 0
            var extra: Int = 0
            var total = 0

            //Get age group
            val age = binding.spinnerAge.selectedItemPosition
            if(age == 0 ){ // Less than 17
                basic = 60
            }else if( age == 1 ){
                basic = 70
            }else if( age == 2 ){
                basic = 90
            }else if( age == 3) {
                basic = 120
            }else if( age == 4 ){
                basic = 150
            }else if( age == 5 ){
                basic = 150
            }else {
                basic = -1
            }
            //Get the gander
            val gender = binding.radioGroupGender.checkedRadioButtonId
            if(gender == binding.radioButtonMale.id){
                //Calculate the extra premium for male
                when(basic){
                    60 -> extra += 0
                    70 -> extra += 50
                    90 -> extra += 100
                    120 -> extra += 150
                    150 -> extra += 200
                    else -> {
                        extra = 0
                    }
                }
            }
            //Get the smoker status
            val smoker = binding.checkBoxSmoker.isChecked
            if(smoker) {
                //Calculate the extra premium for a smoker
                when(age){
                    0 -> extra += 0
                    1 -> extra += 100
                    2 -> extra += 150
                    3 -> extra += 200
                    4 -> extra += 250
                    5 -> extra += 300
                    else -> {
                        extra = 0
                    }
                }
            }
            total = basic + extra
            binding.myPremium = Premium(basic, extra, total)
        }

        binding.buttonReset.setOnClickListener{
            binding.myPremium = Premium()
            binding.spinnerAge.setSelection(0)
            binding.radioGroupGender.check(binding.radioButtonMale.id)
            binding.checkBoxSmoker.isChecked = false
        }
    }
}