package net.mamoe.example.eventHandle;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;

/**
 * @Author jiyec
 * @Date 2021/5/4 8:54
 * @Version 1.0
 **/
public class GroupEventJava {
    public static void at(Bot bot, GroupMessageEvent event){

            System.out.println(event);
            /**
             * 机器人被AT事件
             *
             * 参考：https://github.com/mamoe/mirai/issues/350
             */
            if(event.getMessage().stream().anyMatch(it -> it instanceof At && ((At) it).getTarget() == bot.getId())){
                MessageChain at_resp = MessageUtils.newChain(new PlainText("其一--"), new At(event.getSender().getId()), new PlainText("AT 我干嘛啊"));
                event.getSubject().sendMessage(at_resp);
            };

            // 似乎不太推荐？
            if(event.getMessage().contains(new At(bot.getId()))){
                MessageChain at_resp = MessageUtils.newChain(new PlainText("其二--"), new At(event.getSender().getId()), new PlainText("AT 我干嘛啊"));
                event.getSubject().sendMessage(at_resp);
            }
    }
}
