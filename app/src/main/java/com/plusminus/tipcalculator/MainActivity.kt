package com.plusminus.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.plusminus.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateTip.setOnClickListener {
            calculateTip()
        }

    }

    fun calculateTip() {
        var amount: Double = 0.0
        if (binding.costOfService.text == null) {
            Toast.makeText(this, "Enter the cost", Toast.LENGTH_SHORT).show()
        } else {
            amount = binding.costOfService.text.toString().toDouble()
            val selectedId = binding.tipOptions.checkedRadioButtonId
            when (selectedId) {
                R.id.option_twenty_percent -> amount *= 0.2
                R.id.option_eighteen_percent -> amount *= 0.18
                R.id.option_fifteen_percent -> amount *= 0.15
            }
            if (binding.tipRound.isChecked) {
                var temp: Int = amount.roundToInt()
                val formattedTip = NumberFormat.getCurrencyInstance().format(temp)
                binding.calculatedValue.text = getString(R.string.tip_amount, formattedTip)
            } else {
                val formattedTip = NumberFormat.getCurrencyInstance().format(amount)
                binding.calculatedValue.text = getString(R.string.tip_amount, formattedTip)
            }
        }

    }
}