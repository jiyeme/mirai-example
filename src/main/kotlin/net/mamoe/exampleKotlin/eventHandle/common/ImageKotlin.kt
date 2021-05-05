package net.mamoe.exampleKotlin.eventHandle.common

import net.mamoe.exampleKotlin.eventHandle.CommonEventKotlin
import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.io.FileInputStream

/**
 * @Author jiyec
 * @Date  2021/5/5 19:53
 * @Version 1.0
 **/
object ImageKotlin {

    @JvmStatic
    fun main(bot: Bot){
        sendImage(bot)
    }

    /**
     * 发送图片
     *
     * 触发条件：发送字符串 ---> “kotlin图片”
     *
     * 触发效果：机器人回复 “这是你要的图片 + [图片]”
     *
     * 参考：https://github.com/mamoe/mirai/issues/1045
     *      https://github.com/mamoe/mirai/issues/430
     *
     * @param bot
     */
    @JvmStatic
    private fun sendImage(bot: Bot) {
        bot.eventChannel.subscribeMessages{
            case("kotlin图片"){

                val resource = CommonEventKotlin.javaClass.getResource("/6ae5b66fddc451dacf74cd38bdfd5266d1163210.jpg")
                val fileInputStream = FileInputStream(resource.file)

//                fileInputStream.sendAsImageTo(subject)
                val uploadAsImage = fileInputStream.uploadAsImage(subject)
                subject.sendMessage(PlainText("这是你要的图片: ") + uploadAsImage)
                fileInputStream.close()

            }
        }
    }
}