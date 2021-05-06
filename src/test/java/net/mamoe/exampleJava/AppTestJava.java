package net.mamoe.exampleJava;


import net.mamoe.exampleJava.utils.FileUtil;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.LightApp;
import net.mamoe.mirai.message.data.RichMessage;
import net.mamoe.mirai.message.data.ServiceMessage;
import net.mamoe.mirai.message.data.SimpleServiceMessage;
import net.mamoe.mirai.utils.BotConfiguration;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * Unit test for simple App.
 */
public class AppTestJava
{
    /**
     * Rigorous Test :-)
     */
    public static void main(String[] args) {
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

        bot.getEventChannel().subscribeAlways(MessageEvent.class, event->{
            System.out.println(event.getMessage().toString());
            String msg = event.getMessage().toString();
            String content = msg.substring(msg.indexOf("test"));
            content = content.replaceAll("  ", " ");
            String[] strings = content.split(" ");
            if("test".equals(strings[0])){
                event.getSubject().sendMessage("测试消息");

                String file = "/RichMessage/" + strings[1];
                String fileContent = FileUtil.ReadFile(AppTestJava.class.getResource(file).getFile());
                SimpleServiceMessage simpleServiceMessage = null;
                LightApp lightApp = null;

                if(file.endsWith("xml"))
                    simpleServiceMessage = new SimpleServiceMessage(80, fileContent);
                else if(file.endsWith("json")) {
                    simpleServiceMessage = new SimpleServiceMessage(1, fileContent);
                    lightApp = new LightApp(fileContent);
                }

                if(null != simpleServiceMessage){
                    event.getSubject().sendMessage(simpleServiceMessage);
                }
                if(null != lightApp){
                    event.getSubject().sendMessage(lightApp);
                }
            }
        });
    }

    @Test
    public void testIS() throws FileNotFoundException {

    }
}
