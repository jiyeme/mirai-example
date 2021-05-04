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
        at(bot);
    }

    /**
     * 机器人被AT事件
     *
     * 触发条件：机器人被AT
     *
     * 触发效果：机器人 @发送者 [两条]
     *
     * 参考：https://github.com/mamoe/mirai/issues/350
     */
    public static void at(Bot bot){
        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event-> {
            if (event.getMessage().stream().anyMatch(it -> it instanceof At && ((At) it).getTarget() == bot.getId())) {
                MessageChain at_resp = MessageUtils.newChain(new PlainText("其一--"), new At(event.getSender().getId()), new PlainText("AT 我干嘛啊"));
                event.getSubject().sendMessage(at_resp);
            }

            // 似乎不太推荐？
            if (event.getMessage().contains(new At(bot.getId()))) {
                MessageChain at_resp = MessageUtils.newChain(new PlainText("其二--"), new At(event.getSender().getId()), new PlainText("AT 我干嘛啊"));
                event.getSubject().sendMessage(at_resp);
            }
        });
    }

}
