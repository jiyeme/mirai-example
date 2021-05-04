package net.mamoe.example

import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*
import java.util.function.Predicate

/**
 * @Author jiyec
 * @Date  2021/5/4 9:35
 * @Version 1.0
 **/
object AppMainKotlin {
    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {

        /**
         * 设置：
         * Edit Configurations --->  Environment variables:  ----->  QQID=xxx;QQPASS=xxx
         */
        val qqid = System.getenv("QQID");
        val qqpass = System.getenv("QQPASS");

        if (qqid == null || qqpass == null) {
            println("未配置账号密码")
            return@runBlocking
        }

        /**
         * 机器人创建登录
         *
         * 参见： https://github.com/mamoe/mirai/blob/dev/docs/Bots.md
         */
        // Kotlin
        val bot = BotFactory.newBot(qqid.toLong(), qqpass) {
            // 配置，例如：
            fileBasedDeviceInfo()
        }
        bot.login()

        afterLogin(bot)
    }

    @JvmStatic
    private fun afterLogin(bot: Bot): Unit {
        groupEvent(bot)
    }

    @JvmStatic
    private fun groupEvent(bot: Bot): Unit{
        bot.eventChannel.subscribeAlways<GroupMessageEvent> {
            /**
             * 机器人被AT事件
             *
             * 参考：https://github.com/mamoe/mirai/issues/350
             */
            if (message.stream()
                    .anyMatch(Predicate { it: SingleMessage? -> it is At && (it as At).target == bot.id })
            ) {
                val at_resp = messageChainOf(PlainText("其一--"), At(sender.id), PlainText("AT 我干嘛啊"))
                subject.sendMessage(at_resp)
            }
        }
    }
}