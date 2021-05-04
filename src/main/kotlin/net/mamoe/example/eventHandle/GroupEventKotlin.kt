package net.mamoe.example.eventHandle

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.SingleMessage
import net.mamoe.mirai.message.data.messageChainOf
import java.util.function.Predicate

/**
 * QQ群事件
 * @Author jiyec
 * @Date  2021/5/4 12:33
 * @Version 1.0
 **/
object GroupEventKotlin {

    /**
     * 机器人被AT事件
     *
     * 参考：https://github.com/mamoe/mirai/issues/350
     */
    @JvmStatic
    fun at(bot: Bot): Unit {
        bot.eventChannel.subscribeAlways<GroupMessageEvent> {
            if (message.stream()
                    .anyMatch(Predicate { it: SingleMessage? -> it is At && (it as At).target == bot.id })
            ) {
                val at_resp = messageChainOf(PlainText("其一--"), At(sender.id), PlainText("AT 我干嘛啊"))
                subject.sendMessage(at_resp)
            }
        }

        bot.eventChannel.subscribeGroupMessages {
            this.atBot(){
                val at_resp = messageChainOf(PlainText("其二--"), At(sender.id), PlainText("AT 我干嘛啊"))
                subject.sendMessage(at_resp)
            }
        }
    }
}