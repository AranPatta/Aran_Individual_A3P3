package com.example.tempconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var seekBarCel: SeekBar
    private lateinit var seekBarFah: SeekBar
    private lateinit var celTemp: TextView
    private lateinit var fahTemp: TextView
    private lateinit var msg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarCel = findViewById(R.id.seekBarCel)
        seekBarFah = findViewById(R.id.seekBarFah)
        celTemp = findViewById(R.id.celTemp)
        fahTemp = findViewById(R.id.fahTemp)
        msg = findViewById(R.id.message)

        seekBarCel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateFah(progress)
                }
            }

            //not used, but get error when removed...
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarFah.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (progress < 32) {
                        seekBar?.progress = 32
                        updateMsg(0)
                    } else {
                        updateCel(progress)
                    }
                }
            }

            //not used, but get error when removed...
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        updateFah(0)
    }

    private fun updateFah(celsius: Int) {
        val fahrenheit = celsius * 9 / 5 + 32
        seekBarFah.progress = fahrenheit
        celTemp.text = "$celsius.00"
        fahTemp.text = "$fahrenheit.00"
        updateMsg(celsius)
    }

    private fun updateCel(fahrenheit: Int) {
        val celsius = (fahrenheit - 32) * 5 / 9
        seekBarCel.progress = celsius
        celTemp.text = "$celsius.00"
        fahTemp.text = "$fahrenheit.00"
        updateMsg(celsius)
    }

    private fun updateMsg(celsius: Int) {
        if (celsius <= 20) {
            msg.text = getString(R.string.warm)
        } else {
            msg.text = getString(R.string.cold)
        }
    }
}