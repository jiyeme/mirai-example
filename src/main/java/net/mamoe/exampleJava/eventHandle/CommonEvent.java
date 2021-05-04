package net.mamoe.exampleJava.eventHandle;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import net.mamoe.mirai.message.action.Nudge;
import net.mamoe.mirai.message.data.MessageChainBuilder;

/**
 * 通用事件
 *
 * @Author jiyec
 * @Date 2021/5/4 13:34
 * @Version 1.0
 **/
public class CommonEvent {
    public static void main(Bot bot){
        nudge(bot);
    }

    /**
     * 戳一戳
     * 触发条件：
     * 在好友对话界面或QQ群聊天界面双击机器人头像
     *
     * 出发效果：
     * 发起者被机器人“戳了一下”
     *
     */
    public static void nudge(Bot bot){
        bot.getEventChannel().subscribeAlways(NudgeEvent.class, event-> {

            if(event.getTarget() == bot){
                event.getFrom().nudge().sendTo(event.getSubject());
            }
        });
    }
}
