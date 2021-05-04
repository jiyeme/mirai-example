package net.mamoe.exampleKotlin.eventHandle

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.events.NudgeEvent
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.Face

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