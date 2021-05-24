package org.example.robot.plugins;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/19 10:48
 * @Version 1.0
 **/
@Slf4j
public class TestPlugin extends BasePluginImpl{

    private static final Map<String, Object> pluginData = new HashMap<>();

    // 二级指令
    private static final Map<String, String> subCmdList = new HashMap<String, String>(){{
        put("二级", "testSecond");
    }};

    // 需要注册为一级指令的 指令
    private static final Map<String, String> registerCmd = new HashMap<String, String>(){{

    }};

    // 本插件一级指令
    @Override
    public String getCmd() {
        return "测试指令";
    }

    @Override
    public String getHelp() {
        return "这是测试帮助信息";
    }

    @Override
    public Map<String, String> getSubCmdList() {
        return subCmdList;
    }

    @Override
    public Map<String, String> getRegisterCmd() {
        return registerCmd;
    }

    // 全局指令
    @Override
    public List<String> getGlobalCmd() {
        return new ArrayList<String>(){{
            add("testGlobal");
        }};
    }

    public boolean testSecond(){
        event.getSubject().sendMessage("测试指令触发1");
        return true;
    }
    public boolean testGlobal(){
        event.getSubject().sendMessage("测试指令触发2");
        return true;
    }

    // 初始化插件数据[从外部到内部]
    public static void initPluginData(Map<String, Object> config){
        pluginData.putAll(config);  // 置入
    }
}
