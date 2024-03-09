package com.example.yuxibing.mykotlindemo.kotlincode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yuxibing.mykotlindemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
