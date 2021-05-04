package net.mamoe.exampleKotlin.eventHandle

import net.mamoe.mirai.Bot
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.event.events.NudgeEvent
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.Face
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.sendAsImageTo
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.io.FileInputStream

/**
 * 通用事件
 *
 * @Author jiyec
 * @Date  2021/5/4 13:49
 * @Version 1.0
 **/
object CommonEvent {
    @JvmStatic
    fun main(bot: Bot){
        plainText(bot)
        nudge(bot);
        rawFace(bot)
        sendImage(bot)
    }

    /**
     * 发送图片
     *
     * 触发条件：发送字符串 ---> “图片”
     *
     * 触发效果：机器人回复 “这是你要的图片 + [图片]”
     *
     * 参考：https://github.com/mamoe/mirai/issues/1045
     *      https://github.com/mamoe/mirai/issues/430
     *
     * @param bot
     */
    private fun sendImage(bot: Bot) {
        bot.eventChannel.subscribeMessages{
            case("图片"){

                val resource = CommonEvent.javaClass.getResource("/6ae5b66fddc451dacf74cd38bdfd5266d1163210.jpg")
                val fileInputStream = FileInputStream(resource.file)

//                fileInputStream.sendAsImageTo(subject)
                val uploadAsImage = fileInputStream.uploadAsImage(subject)
                subject.sendMessage(PlainText("这是你要的图片: ") + uploadAsImage)
                fileInputStream.close()

            }
        }
    }

    /**
     * 纯文本
     *
     * 触发条件：发送字符串 ---> “纯文本”
     *
     * 触发效果：机器人回复 “这是一条纯文本消息”
     *
     * @param bot
    */
    private fun plainText(bot: Bot) {
        bot.eventChannel.subscribeGroupMessages{
            case("纯文本"){
                subject.sendMessage("纯文本消息-Kotlin")
            }
        }
    }

    /**
     * 戳一戳
     * 触发条件：
     * 在好友对话界面或QQ群聊天界面双击机器人头像
     *
     * 触发效果：
     * 发起者被机器人“戳了一下”
     *
     */
    @JvmStatic
    fun nudge(bot: Bot){
        bot.eventChannel.subscribeAlways<NudgeEvent> {
            if(target == bot)
                from.nudge().sendTo(subject)
        }
    }

    /**
     * 原生表情
     *
     * 触发条件：机器人收到 “表情” 信息
     *
     * 触发效果：发送这收到一个斜眼笑表情
     *
     */
    @JvmStatic
    fun rawFace(bot: Bot){
        bot.eventChannel.subscribeMessages {
            case("表情"){
                subject.sendMessage(Face(Face.XIE_YAN_XIAO))
            }
        }
    }
}