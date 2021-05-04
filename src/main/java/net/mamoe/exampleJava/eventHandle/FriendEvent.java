package net.mamoe.exampleJava.eventHandle;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.Face;
import net.mamoe.mirai.message.data.PlainText;

/**
 * @Author jiyec
 * @Date 2021/5/4 12:48
 * @Version 1.0
 **/
public class FriendEvent {

    public static void main(Bot bot){
        plainText(bot);
        rawFace(bot);
    }

    /**
     * 纯文本
     *
     * @param bot
     */
    public static void plainText(Bot bot){
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, event->{
            String msg = event.getMessage().toString();
            if(msg.contains("纯文本")){
                event.getSubject().sendMessage(new PlainText("这是一条纯文本消息"));
            }
        });
    }

    /**
     * 原生表情
     *
     */
    public static void rawFace(Bot bot){

        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, event->{
            String msg = event.getMessage().toString();
            if(msg.contains("表情")){
                event.getSubject().sendMessage(new Face(Face.XIE_YAN_XIAO));
            }
        });
    }
}
