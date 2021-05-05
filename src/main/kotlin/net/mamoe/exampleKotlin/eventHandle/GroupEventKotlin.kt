package net.mamoe.exampleKotlin.eventHandle

import net.mamoe.exampleKotlin.eventHandle.group.AtKotlin
import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.*
import java.util.function.Predicate

/**
 * QQ群事件
 * @Author jiyec
 * @Date  2021/5/4 12:33
 * @Version 1.0
 **/
object GroupEventKotlin {
    @JvmStatic
    fun main(bot: Bot){
        AtKotlin.main(bot)
    }

}