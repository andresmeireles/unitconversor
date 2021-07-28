package com.andresmeireles.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andresmeireles.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculateButton = binding.calculateButton

        calculateButton.setOnClickListener { calculate() }
    }

    private fun calculate() {
        val unitText = binding.unitInputEditText.text
        val unitValue = unitText.toString().toDoubleOrNull()
        println(unitValue)
        if (unitValue == null) {
            display(0.0)
            return
        }
        val value = (unitValue * toUnitConverter()) / fromUnitConverter()
        display(value)
    }

    private fun display(toDisplay: Double) {
        val number = if (toDisplay < 1) toDisplay.toBigDecimal() else toDisplay.toLong()
        binding.convertedNumber.text = getString(R.string.convertedAmount, number.toString())
    }

    private fun toUnitConverter(): Double {
        return when (binding.toRadioGroup.checkedRadioButtonId) {
            R.id.toUnit1 -> 100000
            R.id.toUnit2 -> 1000
            else -> 1
        }.toDouble()
    }

    private fun fromUnitConverter(): Double {
        return when (binding.fromUnitRadioGroup.checkedRadioButtonId) {
            R.id.unit1 -> 100000
            R.id.unit2 -> 1000
            else -> 1
        }.toDouble()
    }
}