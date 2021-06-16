package org.example.robot.plugins.msg;

import lombok.SneakyThrows;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.MessageReceipt;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/19 10:34
 * @Version 1.0
 **/
public abstract class MessagePluginImpl implements MessagePlugin {

    protected MessageEvent event;
    // 在全局指令传入时，此属性为null
    protected List<String> cmds;

    public void init(MessageEvent event, List<String> cmds){
        this.event = event;
        this.cmds = cmds;
    }
    @Override
    public abstract String getMainCmd();
    @Override
    public abstract @NotNull String getHelp();

    @Override
    public void defaultAction() {

    }

    @Override
    public Map<String, String> getRegisterAsFirstCmd() {
        return null;
    }

    @Override
    public boolean checkAdmin() {
        return false;
    }

    @Override
    public void initPluginData(Map<String, Object> config) {
    }

    @Override
    public abstract List<String> getGlobalCmd();

    public void updatePluginData(Map<String, Object> pluginData){
        // DataHandle.updatePluginData(pluginData);
    }
}

class MessageRecall extends Thread{
    private final MessageReceipt messageReceipt;
    private long time = 20;

    public MessageRecall(MessageReceipt messageReceipt){
        this.messageReceipt = messageReceipt;
    }

    /**
     *
     * @param messageReceipt    // 消息回执
     * @param time              // 延迟时间[秒]
     */
    public MessageRecall(MessageReceipt messageReceipt, long time){
        this.messageReceipt = messageReceipt;
        this.time = time;
    }
    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(time * 1000);
        messageReceipt.recall();
    }
}
