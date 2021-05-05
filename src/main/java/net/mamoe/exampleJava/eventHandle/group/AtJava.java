package net.mamoe.exampleJava.eventHandle.group;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;

/**
 * @Author jiyec
 * @Date 2021/5/5 18:54
 * @Version 1.0
 **/
public class AtJava {
    public static void main(Bot bot){
        at(bot);
    }

    /**
     * 机器人被AT事件
     *
     * 触发条件：机器人被AT, 且包含字符串”java"
     *
     * 触发效果：机器人 @发送者 [两条]
     *
     * 参考：https://github.com/mamoe/mirai/issues/350
     */
    public static void at(Bot bot){
        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event-> {
            String msg = event.getMessage().toString();
            if (msg.contains("java") && event.getMessage().stream().anyMatch(it -> it instanceof At && ((At) it).getTarget() == bot.getId())) {
                MessageChain at_resp = MessageUtils.newChain(new PlainText("其一--"), new At(event.getSender().getId()), new PlainText("AT 我干嘛啊"));
                event.getSubject().sendMessage(at_resp);
            }

            // 似乎不太推荐？
            if (msg.contains("java") && event.getMessage().contains(new At(bot.getId()))) {
                MessageChain at_resp = MessageUtils.newChain(new PlainText("其二--"), new At(event.getSender().getId()), new PlainText("AT 我干嘛啊"));
                event.getSubject().sendMessage(at_resp);
            }
        });
    }

}
