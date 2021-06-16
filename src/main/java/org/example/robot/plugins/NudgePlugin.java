package org.example.robot.plugins;

import org.example.robot.common.Nudge;
import org.example.robot.entity.EventType;
import net.mamoe.mirai.event.events.NudgeEvent;

/**
 * @Author jiyec
 * @Date 2021/6/15 22:02
 * @Version 1.0
 **/
public class NudgePlugin extends EventPluginImpl{

    @Override
    public void handle() {
        Nudge.nudge((NudgeEvent) event);
    }

    @Override
    public EventType event() {
        return EventType.NudgeEvent;
    }
}
