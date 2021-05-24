package org.example.robot.plugins;

import lombok.Getter;
import org.example.robot.eventHandle.RepeatEventJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jiyec
 * @Date 2021/5/19 10:48
 * @Version 1.0
 **/
public class RepeatPlugin extends BasePluginImpl{

    private static final List<Long> repeatList = new ArrayList<>();

    // 二级指令
    @Getter
    private final Map<String, String> subCmdList = new HashMap<String, String>(){{
        put("开启", "enableMode");
    }};

    // 需要注册为一级指令的 指令
    @Getter
    private final Map<String, String> registerCmd = new HashMap<String, String>(){{

    }};

    // 本插件一级指令
    @Override
    public String getCmd() {
        return "复读功能";
    }

    @Override
    public String getHelp() {
        return "User:\n发送 [复读功能 开启] 可进入复读模式";
    }

    public boolean enableMode(){
        long id = event.getSubject().getId();
        if(repeatList.contains(id))return true;
        repeatList.add(id);
        event.getBot().getEventChannel().registerListenerHost(new RepeatEventJava(id));
        return true;
    }

}
