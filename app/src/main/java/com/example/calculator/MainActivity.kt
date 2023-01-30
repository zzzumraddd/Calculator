package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var delete: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button
    private lateinit var backspace: Button
    private lateinit var foiz: Button

    private lateinit var point: Button
    private lateinit var clear: Button
    private lateinit var equal: Button
    private lateinit var div: Button
    private lateinit var multiply: Button
    private lateinit var plus: Button
    private lateinit var minus: Button


    private lateinit var operand: TextView
    private lateinit var result: TextView


    private var isPoint = true
    private var isSimvol = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)
        clear.setOnClickListener {
            operand.text = "0"
            result.text = "0"
            isPoint = true
            isSimvol = true
        }

        equal.setOnClickListener {
            operand.text=""
        }
        div.setOnClickListener {
            addSimvol("/")
        }
        multiply.setOnClickListener {
            addSimvol("x")
        }
        plus.setOnClickListener {
            addSimvol("+")
        }
        minus.setOnClickListener {
            addSimvol("-")
        }

        backspace.setOnClickListener{
                if (operand.text.length == 1) {
                    operand.text = "0"
                    result.text = "0"
                    isPoint = true
                    isSimvol = true
                } else {
                    if (operand.text[operand.text.length - 1] == '.') {
                        isPoint = true
                    }
                    operand.text = operand.text.substring(0, operand.text.length - 1)
                }

                var str: String = operand.text.toString()

                if (!operand.text[operand.text.length - 1].isDigit()) {
                    operand.text = operand.text.substring(0, operand.text.length - 1)
                    result.text = calculate().toString()
                    operand.text = str
                } else {
                    result.text = calculate().toString()
                }
        }



    }

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {

        var btn = findViewById<Button>(p0!!.id)
        if (operand.text == "0") {
            operand.text = ""
        }
        operand.text = operand.text.toString() + btn.text
        isSimvol = true
        result.text = calculate()
    }

    @SuppressLint("SetTextI18n")
    private fun calculate(): String {
        var list = createArray(operand.text.toString())
        list = hisobla1(list)
        hisobla2(list)
        return list[0].toString()
    }

    private fun createArray(s: String): MutableList<Any> {
        var list = mutableListOf<Any>()
        var temp = ""
        for (i in s) {
            if (i.isDigit() || i == '.') {
                temp += i
            } else {
                list.add(temp.toFloat())
                temp = ""
                list.add(i)
            }
        }
        if (temp.isNotEmpty()) {
            list.add(temp.toFloat())
        }
        return list
    }

    private fun Deleted(s: String): MutableList<Any> {
        var list = mutableListOf<Any>()
        var temp = ""
        for (i in s) {
            if (i.isDigit() || i == '.') {
                temp += i
            } else {
                list.add(temp.toFloat())
                temp = ""
                list.add(i)
            }
        }
        if (temp.isNotEmpty()) {
            list.add(temp.toFloat())
        }
        list = list.drop(list.size-1) as MutableList<Any>
        return list
    }

    fun hisobla1(l: MutableList<Any>): MutableList<Any> {
        var list = l
        var i = 0
        while (list.contains('/') || list.contains('x')) {

            if (list[i] == 'x' || list[i] == '/') {
                var old = list[i - 1] as Float
                var next = list[i + 1] as Float
                var amal = list[i]
                var res = 0f
                when (amal) {
                    '/' -> {
                        res = old / next
                    }
                    'x' -> {
                        res = old * next
                    }
                }
                list[i - 1] = res
                list.removeAt(i)
                list.removeAt(i)
                i -= 2
            }
            i++
        }
        Log.d("AAA", list.toString())

        return l
    }
    private fun replace(i: Int, myList: MutableList<Any>) {
        myList.removeAt(i)
        myList.removeAt(i)
        myList.removeAt(i - 1)
    }

    fun hisobla2(l: MutableList<Any>): MutableList<Any> {
        var list = l
        var i = 0
        while (list.contains('+') || list.contains('-')) {

            if (list[i] == '-' || list[i] == '+') {
                var old = list[i - 1] as Float
                var next = list[i + 1] as Float
                var amal = list[i]
                var res = 0f
                when (amal) {
                    '+' -> {
                        res = old + next
                    }
                    '-' -> {
                        res = old - next
                    }
                }
                list[i - 1] = res
                list.removeAt(i)
                list.removeAt(i)
                i -= 2
            }
            i++
        }
        Log.d("TAG", list.toString())

        return l
    }

    @SuppressLint("SetTextI18n")
    private fun addSimvol(simvol: String) {
        if (isSimvol) {
            operand.text = operand.text.toString() + simvol
            isSimvol = false
        } else {
            operand.text = operand.text.dropLast(1).toString() + simvol
        }

    }


    fun initUI() {
        foiz = findViewById(R.id.foiz)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        point = findViewById(R.id.dot)
        clear = findViewById(R.id.C)
        div = findViewById(R.id.divide)
        multiply = findViewById(R.id.multiply)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        operand = findViewById(R.id.textView2)
        result = findViewById(R.id.textView3)
        equal = findViewById(R.id.teng)
        backspace = findViewById(R.id.remove)
    }
}
