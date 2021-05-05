package net.mamoe.exampleJava;

import net.mamoe.exampleJava.eventHandle.MainHandleJava;
import net.mamoe.exampleKotlin.eventHandle.MainHandleKotlin;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

/**
 * 由于中文原因
 *
 * gradle构建可能会出现异常，请尝试如下操作：
 * setting>gradle>build and run using项默认的应该是gradle，改选idea项即可。
 *
 */
public class AppMainJava
{

    public static void main( String[] args )
    {

        /**
         * 设置：
         * Edit Configurations --->  Environment variables:  ----->  QQID=xxx;QQPASS=xxx
         */
        String qqid = System.getenv("QQID");
        String qqpass = System.getenv("QQPASS");

        if(qqid == null || qqpass == null) {
            System.out.println("未配置账号密码");
            return;
        }

        /**
         * 机器人创建登录
         *
         * 参见： https://github.com/mamoe/mirai/blob/dev/docs/Bots.md
         */
        // Ctrl + 鼠标点击 可查看内部函数
        BotConfiguration botConfiguration = new BotConfiguration() {{
            // 配置，例如：
            fileBasedDeviceInfo();
            // 设置链接的重置次数
            setReconnectionRetryTimes(3);
        }};

        // 创建机器人实例
        Bot bot = BotFactory.INSTANCE.newBot(Long.parseLong(qqid), qqpass, botConfiguration);
        // 登录机器人
        bot.login();

        afterLogin(bot);

    }

    private static void afterLogin(Bot bot){
        MainHandleJava.main(bot);
        MainHandleKotlin.main(bot);
    }

}
