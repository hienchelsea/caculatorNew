package com.example.cachua.caculatornew

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.LoginFilter
import android.util.Log
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger
import kotlin.math.log

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {


    private var mInput: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        innitControl()
    }

    private fun innitControl() {
        tvNumber0.setOnClickListener(this)
        tvNumber1.setOnClickListener(this)
        tvNumber2.setOnClickListener(this)
        tvNumber3.setOnClickListener(this)
        tvNumber4.setOnClickListener(this)
        tvNumber5.setOnClickListener(this)
        tvNumber6.setOnClickListener(this)
        tvNumber7.setOnClickListener(this)
        tvNumber8.setOnClickListener(this)
        tvNumber9.setOnClickListener(this)
        tvDivision.setOnClickListener(this)
        tvMultiply.setOnClickListener(this)
        tvAdd.setOnClickListener(this)
        tvMinus.setOnClickListener(this)
        tvEqual.setOnClickListener(this)
        tvDelete.setOnClickListener(this)
        tvComma.setOnClickListener(this)

        tvDelete.setOnLongClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvNumber0 -> {
                mInput += "0"
                tvCalculator.text = mInput
            }
            R.id.tvNumber1 -> {
                mInput += "1"
                tvCalculator.text = mInput

            }
            R.id.tvNumber2 -> {
                mInput += "2"
                tvCalculator.text = mInput
            }
            R.id.tvNumber3 -> {
                mInput += "3"
                tvCalculator.text = mInput
            }
            R.id.tvNumber4 -> {
                mInput += "4"
                tvCalculator.text = mInput
            }
            R.id.tvNumber5 -> {
                mInput += "5"
                tvCalculator.text = mInput
                Log.d("Hien", mInput + "")
            }
            R.id.tvNumber6 -> {
                mInput += "6"
                tvCalculator.text = mInput
            }
            R.id.tvNumber7 -> {
                mInput += "7"
                tvCalculator.text = mInput
            }
            R.id.tvNumber8 -> {
                mInput += "8"
                tvCalculator.text = mInput
            }
            R.id.tvNumber9 -> {
                mInput += "9"
                tvCalculator.text = mInput
            }
            R.id.tvDivision -> {
                opertionInput("÷")
            }
            R.id.tvMultiply -> {
                opertionInput("x")
            }
            R.id.tvAdd -> {
                opertionInput("+")
            }
            R.id.tvMinus -> {
                opertionInput("-")
            }
            R.id.tvEqual -> {
                calculate()
            }
            R.id.tvDelete -> {
                if (mInput.isNotEmpty()) {
                    mInput = if (mInput[mInput.length - 1].toString() == " ") {
                        mInput.substring(0, mInput.length - 3)
                    } else {
                        mInput.substring(0, mInput.length - 1)
                    }
                    tvCalculator.text = mInput
                    tvScroll.fullScroll(ScrollView.FOCUS_DOWN)
                    if (mInput.isEmpty()) {
                        tvResult.text = "0"
                        tvCalculator.text = "0"
                    }
                }

            }
            R.id.tvComma -> {
                opertionInput(".")
            }

        }

    }

    override fun onLongClick(v: View): Boolean {
        when (v.id) {
            R.id.tvDelete -> {
                mInput = ""
                tvResult.text = "0"
                tvCalculator.text = "0"
                return true
            }
        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun calculate() {
        var result = mInput.split(" ")
        var resultNhan: ArrayList<String> = ArrayList()
        var resultChia: ArrayList<String> = ArrayList()

        for (i in 0..(result.size - 1)) {
            resultNhan.add(result[i])

        }
        for (i in 0..(resultNhan.size - 1)) {
            if (i == resultNhan.size - 1) {
                resultChia.add(resultNhan[i])
            } else {
                if (resultNhan[i] == "+" || resultNhan[i] == "-") {
                    resultChia.add(resultNhan[i])
                } else {
                    if (resultNhan[i + 1] == "x" || resultNhan[i + 1] == "÷") {
                        if (resultNhan[i + 1] == "x") {
                            resultNhan[i + 2] = ((resultNhan[i].toFloat()) * (resultNhan[i + 2].toFloat())).toString()
                        } else {
                            resultNhan[i + 2] = ((resultNhan[i].toFloat()) / (resultNhan[i + 2].toFloat())).toString()
                        }

                    } else {
                        if (resultNhan[i + 1] == "+" || resultNhan[i + 1] == "-") {
                            resultChia.add(resultNhan[i])
                        }
                    }
                }
            }
        }


        var sum = resultChia[0].toFloat()
        for (i in 1..(resultChia.size - 1) step 2) {
            if (resultChia[i] == "+") {
                sum += resultChia[i + 1].toFloat()
            } else {
                if (resultChia[i] == "-") {
                    sum -= resultChia[i + 1].toFloat()
                }
            }
        }
        tvResult.text = sum.toString()
        Log.e("Hien", sum.toString())


    }

    fun opertionInput(s: String) {
        if (mInput.isNotEmpty()) {

        } else {
            mInput += "0"
        }
        if (s == ".") {
            mInput.also {
                if ((it[it.length - 1]).toString() == "÷"
                        || (it[it.length - 1]).toString() == "+"
                        || (it[it.length - 1]).toString() == "-"
                        || (it[it.length - 1]).toString() == "x") {
                } else {
                    mInput += s
                    tvCalculator.text = mInput
                    tvScroll.fullScroll(ScrollView.FOCUS_DOWN)
                }

            }
        } else {
            mInput.also {
                if (mInput.length > 2) {
                    if ((it[it.length - 2]).toString() == "÷"
                            || (it[it.length - 2]).toString() == "+"
                            || (it[it.length - 2]).toString() == "-"
                            || (it[it.length - 2]).toString() == "x") {
                        mInput = it.substring(0, it.length - 3)
                    }
                }

                mInput += " "
                mInput += s
                mInput += " "
                tvCalculator.text = mInput
                tvScroll.fullScroll(ScrollView.FOCUS_DOWN)
            }
        }

    }

}
