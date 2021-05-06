package net.mamoe.exampleKotlin.eventHandle.common

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.event.subscribeMessages

/**
 * @Author jiyec
 * @Date  2021/5/5 19:55
 * @Version 1.0
 **/
object TextKotlin {

    @JvmStatic
    fun main(bot: Bot){
        plainText(bot)
    }
    /**
     * 纯文本
     *
     * 触发条件：发送字符串 ---> “kotlin纯文本”
     *
     * 触发效果：机器人回复 “这是一条纯文本消息”
     *
     * @param bot
     */
    private fun plainText(bot: Bot) {
        bot.eventChannel.subscribeMessages{
            case("kotlin纯文本"){
                subject.sendMessage("纯文本消息-Kotlin")
            }
        }
    }
}