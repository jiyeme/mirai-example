package net.mamoe.exampleKotlin

import kotlinx.coroutines.runBlocking
import net.mamoe.exampleJava.eventHandle.MainHandleJava
import net.mamoe.exampleKotlin.eventHandle.MainHandleKotlin
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.event.subscribeMessages

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
        bot.eventChannel.subscribeMessages {
            case("test"){
                subject.sendMessage("群员发送了一个测试消息")
            }
        }
        // 调kotlin的实现
        MainHandleKotlin.main(bot)
        // 调JAVA的实现
        MainHandleJava.main(bot)
    }

}