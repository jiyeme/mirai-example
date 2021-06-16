package org.example.robot.plugins;

import org.example.robot.entity.EventType;
import net.mamoe.mirai.event.events.MemberLeaveEvent;

/**
 * @Author jiyec
 * @Date 2021/6/15 22:31
 * @Version 1.0
 **/
public class MemberLeavePlugin extends EventPluginImpl{
    @Override
    public void handle() {

        MemberLeaveEvent e = (MemberLeaveEvent) event;
        e.getGroup().sendMessage(e.getMember().getNick() + " 静静地离开了(；′⌒`)");
    }

    @Override
    public EventType event() {
        return EventType.Quit;
    }
}
