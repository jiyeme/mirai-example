package net.mamoe.exampleKotlin.eventHandle

import net.mamoe.mirai.Bot

/**
 * @Author jiyec
 * @Date  2021/5/5 18:37
 * @Version 1.0
 **/
object MainHandleKotlin {
    @JvmStatic
    fun main(bot: Bot): Unit {

        GroupEventKotlin.main(bot)
        FriendEventKotlin.main(bot)
        CommonEventKotlin.main(bot)
    }
}