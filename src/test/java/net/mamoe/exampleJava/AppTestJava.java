package net.mamoe.exampleJava;


import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.RichMessage;
import net.mamoe.mirai.message.data.ServiceMessage;
import net.mamoe.mirai.message.data.SimpleServiceMessage;
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

        ServiceMessage share = RichMessage.share(
                "https://www.jysafe.cn",
                "标题",
                "内容",
                "http://gchat.qpic.cn/gchatpic_new/1169088181/4177879595-3121117498-74EB28C523F12CE933EEF3BCF50D4ED1/0"
        );
        String xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>" +
                "<msg templateID='12345' serviceID='1' action='web' actionData='' brief='[分享] 标题' flag='3' url='https://www.jysafe.cn'>" +
                "<item bg='0' layout='2'>" +
                "<picture cover='http://gchat.qpic.cn/gchatpic_new/1169088181/4177879595-3121117498-74EB28C523F12CE933EEF3BCF50D4ED1/0'/>" +
                "<title size='25' color='#000000'>标题</title>" +
                "<summary color='#000000'>内容</summary>" +
                "</item>" +
                "<source name='' icon=''/>" +
                "</msg>";
        String xml2 = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\"1\" templateID=\"1\" action=\"web\" brief=\"欢迎新人加入本群\" sourceMsgId=\"0\" url=\"https://post.mp.qq.com/group/article/33303433373836353238-35707230.html\" flag=\"0\" adverSign=\"0\" multiMsgFlag=\"0\"><item layout=\"2\" advertiser_id=\"0\" aid=\"0\"><picture cover=\"http://t.cn/RE60mXx\" w=\"0\" h=\"0\" /><title>欢迎加入本群，遵守本群群规！</title><summary></summary></item><source name=\"群名称 : 123456789\" icon=\"http://url.cn/JS8oE7\" action=\"\" appid=\"-1\" /></msg>";
        SimpleServiceMessage simpleServiceMessage = new SimpleServiceMessage(1, xml2);

        bot.getEventChannel().subscribeAlways(MessageEvent.class, event->{
            if(event.getMessage().toString().contains("test")){
                event.getSubject().sendMessage("测试消息");
                event.getSubject().sendMessage(simpleServiceMessage);
            }
        });
    }

    @Test
    public void testIS() throws FileNotFoundException {

    }
}
