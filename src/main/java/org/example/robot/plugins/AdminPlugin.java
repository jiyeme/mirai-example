package org.example.robot.plugins;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/19 10:48
 * @Version 1.0
 **/
public class AdminPlugin extends BasePluginImpl{

    private static final Map<String, Object> pluginData = new HashMap<>();

    // 二级指令
    @Getter
    private final Map<String, String> subCmdList = new HashMap<String, String>(){{
        put("添加管理", "addAdmin");
        put("删除管理", "delAdmin");
    }};
    // 需要注册为一级指令的 指令
    @Getter
    private final Map<String, String> registerCmd = new HashMap<String, String>(){{

    }};

    // 本插件一级指令
    @Override
    public String getCmd() {
        return "管理系统";
    }

    @Override
    public String getHelp() {
        return "这是测试帮助信息";
    }

    public boolean testSecond(){
        event.getSubject().sendMessage("测试指令触发");
        pluginData.put("test", "123");
        updatePluginData(pluginData);
        return true;
    }

    public boolean addAdmin(){

        return true;
    }

    // 初始化插件数据[从外部到内部]
    public static void setPluginData(Map<String, Object> config){
        pluginData.clear();         // 清空
        pluginData.putAll(config);  // 置入
    }
}
