package com.usu.rougelikev2

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this).apply{
            orientation = LinearLayout.VERTICAL
        }

        val textView = AppCompatTextView(this).apply{
            text = "ROUGE LIKE"
            gravity = Gravity.CENTER
            textSize = 42f
        }

        val play = MaterialButton(this).apply{
            text = "Play"
        }
        play.setOnClickListener { view: View? ->
            startActivity(
                Intent(
                    this,
                    GameActivity::class.java
                )
            )
        }

        linearLayout.addView(textView)
        linearLayout.addView(play)

        setContentView(linearLayout)
    }
}