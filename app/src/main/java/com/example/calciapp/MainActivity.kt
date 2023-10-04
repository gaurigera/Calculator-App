package com.example.calciapp

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.calciapp.ui.theme.CalciAppTheme
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.calciapp.Calculator


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textPad = findViewById<EditText>(R.id.textPad)
        val stack = mutableListOf<Int>()
        val calculator = Calculator()

        val numberClickListener = View.OnClickListener { view ->
            if (view is Button) {
                calculator.digitPressed(view.text.toString().toInt())
                val initial = textPad.text.toString()
                val newstr = initial + view.text.toString()
                textPad.setText(newstr)
            }
        }

        val numberButtons = arrayOf(
            R.id.numberZero, R.id.numberOne, R.id.numberTwo, R.id.numberThree,
            R.id.numberFour, R.id.numberFive, R.id.numberSix, R.id.numberSeven,
            R.id.numberEight, R.id.numberNine
        )

        val operatorClickListener = View.OnClickListener {view ->
            if (view is Button) {
                calculator.operatorPressed(view.text.toString())
                val initial = textPad.text.toString()
                val newstr = initial + view.text.toString()
                textPad.setText(newstr)
            }
        }
        val operatorButtons = arrayOf(R.id.plus, R.id.minus,
            R.id.division, R.id.multiply)

        for (buttonId in numberButtons) {
            val numberButton = findViewById<Button>(buttonId)
            numberButton.setOnClickListener(numberClickListener)
        }

        for(operatorId in operatorButtons) {
            val button = findViewById<Button>(operatorId)
            button.setOnClickListener(operatorClickListener)
        }

        val delButton = findViewById<Button>(R.id.delete)
        delButton.setOnClickListener {
            val txt = textPad.text.toString()
            if (txt.isNotEmpty()) {
                val newtxt = txt.substring(0, txt.length - 1)
                textPad.setText(newtxt)
                if(stack.size > 0)
                    stack.removeAt(stack.size - 1)
            }
        }

        val clrButton = findViewById<Button>(R.id.clear)
        clrButton.setOnClickListener {
            textPad.setText("")
            stack.clear()
        }

        val eqlButton = findViewById<Button>(R.id.equalTo)
        eqlButton.setOnClickListener {
            val ans = calculator.calculate()
            textPad.setText(ans.toString())
        }
    }
}

