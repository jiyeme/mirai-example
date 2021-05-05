package net.mamoe.exampleKotlin.eventHandle.group

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.*
import java.util.function.Predicate

/**
 * @Author jiyec
 * @Date  2021/5/5 20:00
 * @Version 1.0
 **/
object AtKotlin {

    @JvmStatic
    fun main(bot: Bot){
        at(bot)
    }


    /**
     * 机器人被AT事件
     *
     * 触发条件：机器人被AT, 且消息包含字符串"kotlin"
     *
     * 触发效果：机器人 @发送者 [两条]
     *
     * 参考：https://github.com/mamoe/mirai/issues/350
     */
    @JvmStatic
    fun at(bot: Bot): Unit {
        bot.eventChannel.subscribeAlways<GroupMessageEvent> {
            if (message.content.contains("kotlin") && message.stream()
                    .anyMatch(Predicate { it: SingleMessage? -> it is At && (it as At).target == bot.id })
            ) {
                val at_resp = messageChainOf(PlainText("其一--"), At(sender.id), PlainText("AT 我干嘛啊"))
                subject.sendMessage(at_resp)
            }
        }

        bot.eventChannel.subscribeGroupMessages {
            this.atBot(){
                if(message.content.contains("kotlin")) {
                    val at_resp = messageChainOf(PlainText("其二--"), At(sender.id), PlainText("AT 我干嘛啊"))
                    subject.sendMessage(at_resp)
                }
            }
        }
    }

}