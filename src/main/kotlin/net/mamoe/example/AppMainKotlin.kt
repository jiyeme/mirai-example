package net.mamoe.example

import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.event.events.GroupMessageEvent

/**
 * @Author jiyec
 * @Date  2021/5/4 9:35
 * @Version 1.0
 **/
class AppMainKotlin {
    suspend fun main(): Unit{

        /**
         * 设置：
         * Edit Configurations --->  Environment variables:  ----->  QQID=xxx;QQPASS=xxx
         */
        val qqid = System.getenv("QQID");
        val qqpass = System.getenv("QQPASS");

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

    private fun afterLogin(bot: Bot): Unit {

    }

    private fun groupEvent(bot: Bot): Unit{
        bot.eventChannel.subscribeAlways<GroupMessageEvent> {
            event->{

        }
        }
    }
}