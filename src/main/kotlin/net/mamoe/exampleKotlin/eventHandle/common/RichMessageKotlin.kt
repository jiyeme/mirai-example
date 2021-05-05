package net.mamoe.exampleKotlin.eventHandle.common

import net.mamoe.exampleKotlin.eventHandle.group.AtKotlin
import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.RichMessage
import net.mamoe.mirai.message.data.sendTo

/**
 * @Author jiyec
 * @Date  2021/5/5 18:44
 * @Version 1.0
 **/
object RichMessageKotlin {

    @JvmStatic
    fun main(bot: Bot){
        sendShareCard(bot)
    }

    @JvmStatic
    fun sendShareCard(bot: Bot){
        bot.eventChannel.subscribeMessages {
            case("kotlin分享卡片"){
                print("kotlin分享卡片")
                val share = RichMessage.share(
                    "https://github.com/mamoe/mirai",
                    "这是标题",
                    "这是内容",
                    "https://github.com/mamoe/mirai/raw/dev/docs/mirai.png"
                )
                share.sendTo(subject)
            }
        }
    }
}