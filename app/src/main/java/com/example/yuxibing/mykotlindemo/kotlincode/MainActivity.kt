package com.example.yuxibing.mykotlindemo.kotlincode

import android.accounts.Account
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Log.println
import android.widget.Button
import com.example.yuxibing.mykotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    // 您可以使用 lateinit 推迟属性初始化。使用 lateinit 时，您应尽快初始化属性。
    // private lateinit var btn: Button


    // 1 !!是非 null 断言运算符: !! 运算符将其左侧的所有内容视为非 null，如果它左侧表达式的结果为 null，则您的应用会抛出 NullPointerException
    //val account = Account("name", "type")
    //val accountName = account.name!!.trim()
    // 2 ?.安全调用运算符 :  虽然安全调用运算符可使您避免潜在的 NullPointerException，但它会将 null 值传递给下一个语句
    //val account = Account("name", "type")
    //val accountName = account.name?.trim()
    // 3 如果 ?: 运算符左侧表达式的结果为 null，则会将右侧的值赋予 accountName。此方法对于提供本来为 null 的默认值很有用。
    // val accountName = account?.name?.trim() ?: "Default name"
    // 您还可以使用 Elvis 运算符提前从函数返回结果
    //account ?: return

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btn = findViewById(R.id.btn)


        val array = ArrayList<Int>()
        for ((index, value) in array.withIndex()) {
            println("the element at $index is $value")
        }

        joinOptions(listOf("ues", "no", "maybe"))
        val listWithNulls: List<String?> = listOf("A", null)
        for (item in listWithNulls) {
            item?.let {
                Log.e("空类型", it)
                Log.e("空类型", it)
            }
        }
        var a: String? = "爱读后感"
        if (a != null) {

            println(a.toLowerCase())
        }


    }

    fun maxof(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    fun minof(a: Int, b: Int) = if (a > b) a else b
    fun joinOptions(options: Collection<String>) = options.joinToString(prefix = "[", postfix = "]")

}
