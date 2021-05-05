package net.mamoe.exampleJava;


import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTestJava
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testBot()
    {
        // QQID=1;QQPASS=2

        String qqid = System.getenv("QQID");
        String qqpass = System.getenv("QQPASS");

        Bot bot = BotFactory.INSTANCE.newBot(Long.parseLong(qqid), qqpass, new BotConfiguration() {{
            // 配置，例如：
            fileBasedDeviceInfo();
            // 设置链接的重置次数
            setReconnectionRetryTimes(3);
        }});
        bot.login();
        bot.getGroup(933286835).sendMessage("我登陆啦");
    }

    @Test
    public void testIS() throws FileNotFoundException {

    }
}
