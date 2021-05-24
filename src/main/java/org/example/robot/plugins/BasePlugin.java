package org.example.robot.plugins;

import net.mamoe.mirai.event.events.MessageEvent;

import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/19 10:40
 * @Version 1.0
 **/
public interface BasePlugin {

    void init(MessageEvent event, List<String> cmds);
    // 本插件一级指令
    String getCmd();

    // 帮助信息
    String getHelp();

    // 插件次级指令
    Map<String, String> getSubCmdList();

    // 插件需注册为一级指令的次级指令
    Map<String, String> getRegisterCmd();

    // 插件需注册为全局指令的次级指令 (不需要匹配指令)
    List<String> getGlobalCmd();

    void defaultAction();

    boolean checkAdmin();

    // 初始化插件数据[从外部到内部]
    static void initPluginData(Map<String, Object> config){
    }

    // 更新插件数据[从内部到外部]
    static void updatePluginData(){}
}
