package org.example.robot.plugins;

import lombok.SneakyThrows;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.MessageReceipt;

import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/19 10:34
 * @Version 1.0
 **/
public abstract class BasePluginImpl implements BasePlugin{

    protected MessageEvent event;
    // 在全局指令传入时，此属性为null
    protected List<String> cmds;

    public void init(MessageEvent event, List<String> cmds){
        this.event = event;
        this.cmds = cmds;
    }
    @Override
    public abstract String getCmd();
    @Override
    public abstract String getHelp();

    @Override
    public void defaultAction() {

    }

    @Override
    public boolean checkAdmin() {
        return false;
    }

    public static void initPluginData(Map<String, Object> config) {

    }

    @Override
    public List<String> getGlobalCmd() {
        return null;
    }

    protected static void updatePluginData(Map<String, Object> pluginData){
        // DataHandle.updatePluginData(pluginData);
    }
}

class MessageRecall extends Thread{
    private MessageReceipt messageReceipt;
    private long time = 20000;

    public MessageRecall(MessageReceipt messageReceipt){
        this.messageReceipt = messageReceipt;
    }
    public MessageRecall(MessageReceipt messageReceipt, long time){
        this.messageReceipt = messageReceipt;
        this.time = time;
    }
    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(time);
        messageReceipt.recall();
    }
}
