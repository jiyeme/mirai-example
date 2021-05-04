package net.mamoe.exampleJava.eventHandle;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.action.Nudge;
import net.mamoe.mirai.message.data.*;

/**
 * @Author jiyec
 * @Date 2021/5/4 8:54
 * @Version 1.0
 **/
public class GroupEvent {

    public static void main(Bot bot){
        plainText(bot);
        rawFace(bot);
        at(bot);
    }

    /**
     * 纯文本
     *
     * @param bot
     */
    public static void plainText(Bot bot){
        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event->{
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

        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event->{
            String msg = event.getMessage().toString();
            if(msg.contains("表情")){
                event.getSubject().sendMessage(new Face(Face.XIE_YAN_XIAO));
            }
        });
    }

    /**
     * 机器人被AT事件
     *
     * 参考：https://github.com/mamoe/mirai/issues/350
     */
    public static void at(Bot bot){
        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event-> {
            if (event.getMessage().stream().anyMatch(it -> it instanceof At && ((At) it).getTarget() == bot.getId())) {
                MessageChain at_resp = MessageUtils.newChain(new PlainText("其一--"), new At(event.getSender().getId()), new PlainText("AT 我干嘛啊"));
                event.getSubject().sendMessage(at_resp);
            }
            ;

            // 似乎不太推荐？
            if (event.getMessage().contains(new At(bot.getId()))) {
                MessageChain at_resp = MessageUtils.newChain(new PlainText("其二--"), new At(event.getSender().getId()), new PlainText("AT 我干嘛啊"));
                event.getSubject().sendMessage(at_resp);
            }
        });
    }

}
