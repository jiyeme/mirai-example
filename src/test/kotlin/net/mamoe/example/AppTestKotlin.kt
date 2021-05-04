package net.mamoe.example

import org.junit.Test

/**
 * @Author jiyec
 * @Date  2021/5/4 8:30
 * @Version 1.0
 **/
class AppTestKotlin {

    @Test
    fun testEnv(): Unit {
        print(System.getenv("QQID"));
        print(System.getenv("QQPASS"));
    }
}