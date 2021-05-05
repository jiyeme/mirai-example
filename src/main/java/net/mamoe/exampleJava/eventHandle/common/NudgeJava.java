package net.mamoe.exampleJava.eventHandle.common;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.NudgeEvent;

/**
 * @Author jiyec
 * @Date 2021/5/5 19:00
 * @Version 1.0
 **/
public class NudgeJava {

    public static void main(Bot bot){
        nudge(bot);
    }

    /**
     * 戳一戳
     * 触发条件：
     * 在好友对话界面或QQ群聊天界面双击机器人头像
     *
     * 触发效果：
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
