package net.mamoe.exampleKotlin.eventHandle

import net.mamoe.exampleKotlin.eventHandle.common.*
import net.mamoe.mirai.Bot

/**
 * 通用事件
 *
 * @Author jiyec
 * @Date  2021/5/4 13:49
 * @Version 1.0
 **/
object CommonEventKotlin {
    @JvmStatic
    fun main(bot: Bot){
        FaceKotlin.main(bot)
        ImageKotlin.main(bot)
        NudgeKotlin.main(bot)
        RichMessageKotlin.main(bot)
        TextKotlin.main(bot)
    }
}