package net.mamoe.exampleKotlin.eventHandle.common

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.Face

/**
 * @Author jiyec
 * @Date  2021/5/5 19:57
 * @Version 1.0
 **/
object FaceKotlin {

    @JvmStatic
    fun main(bot: Bot){
        rawFace(bot)
    }

    /**
     * 原生表情
     *
     * 触发条件：机器人收到 “kotlin表情” 信息
     *
     * 触发效果：发送这收到一个斜眼笑表情
     *
     */
    @JvmStatic
    fun rawFace(bot: Bot){
        bot.eventChannel.subscribeMessages {
            case("kotlin表情"){
                subject.sendMessage(Face(Face.XIE_YAN_XIAO))
            }
        }
    }
}