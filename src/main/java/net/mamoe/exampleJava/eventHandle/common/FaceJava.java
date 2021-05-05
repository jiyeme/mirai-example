package net.mamoe.exampleJava.eventHandle.common;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Face;

/**
 * @Author jiyec
 * @Date 2021/5/5 19:01
 * @Version 1.0
 **/
public class FaceJava {
    public static void main(Bot bot){
        rawFace(bot);
    }
    /**
     * 原生表情
     *
     * 触发条件：机器人收到 “java表情” 信息
     *
     * 触发效果：发送这收到一个斜眼笑表情
     *
     */
    public static void rawFace(Bot bot){

        bot.getEventChannel().subscribeAlways(MessageEvent.class, event->{
            String msg = event.getMessage().toString();
            if(msg.contains("java表情")){
                event.getSubject().sendMessage(new Face(Face.XIE_YAN_XIAO));
            }
        });
    }
}
