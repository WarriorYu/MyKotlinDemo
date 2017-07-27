package com.example.yuxibing.mykotlindemo.kotlincode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.yuxibing.mykotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tt.setText("我是你大爷")
        val array = ArrayList<Int>()
        for ((index, value) in array.withIndex()) {
            println("the element at $index is $value")
        }
        joinOptions(listOf("ues","no","maybe"))

    }

    fun maxof(a: Int, b: Int) : Int{
        if (a > b) {
            return a
        } else {
            return b
        }
    }
    fun minof(a:Int,b: Int) = if (a>b) a else b
    fun joinOptions(options : Collection<String>) = options.joinToString(prefix = "[",postfix = "]")

}
