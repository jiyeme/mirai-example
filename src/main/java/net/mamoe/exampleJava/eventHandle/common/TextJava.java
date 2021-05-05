package net.mamoe.exampleJava.eventHandle.common;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.PlainText;

/**
 * @Author jiyec
 * @Date 2021/5/5 18:59
 * @Version 1.0
 **/
public class TextJava {

    public static void main(Bot bot){
        plainText(bot);
    }
    /**
     * 纯文本
     *
     * 触发条件：发送字符串 ---> “java纯文本”
     *
     * 触发效果：机器人回复 “这是一条纯文本消息”
     *
     * @param bot
     */
    public static void plainText(Bot bot){
        bot.getEventChannel().subscribeAlways(MessageEvent.class, event->{
            String msg = event.getMessage().toString();
            if(msg.contains("java纯文本")){
                event.getSubject().sendMessage(new PlainText("这是一条纯文本消息"));
            }
        });
    }

}
