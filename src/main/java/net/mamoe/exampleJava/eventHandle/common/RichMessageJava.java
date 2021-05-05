package net.mamoe.exampleJava.eventHandle.common;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.ServiceMessage;

/**
 * @Author jiyec
 * @Date 2021/5/5 18:41
 * @Version 1.0
 **/
public class RichMessageJava {

    public static void main(Bot bot){
        sendShareUrlCard(bot);
    }

    /**
     * 发送链接分享卡片
     *
     * 触发条件：机器人收到包含”java分享卡片“的消息
     *
     * 触发效果：发起人发送一条分享卡片
     *
     * @param bot
     */
    public static void sendShareUrlCard(Bot bot){
        bot.getEventChannel().subscribeAlways(MessageEvent.class, event->{

            String msg = event.getMessage().toString();
            if(msg.contains("java分享卡片")){
                ServiceMessage share = net.mamoe.mirai.message.data.RichMessage.share("https://github.com/mamoe/mirai", "这是标题", "这是内容", "https://github.com/mamoe/mirai/raw/dev/docs/mirai.png");

                event.getSubject().sendMessage(share);
            }
        });
    }
}
