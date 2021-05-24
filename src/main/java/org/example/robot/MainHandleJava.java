package org.example.robot;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.event.events.MessagePostSendEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import net.mamoe.mirai.message.MessageReceipt;
import org.example.robot.common.Nudge;
import org.example.robot.data.Storage;
import org.example.robot.eventHandle.OtherHandle;
import org.example.robot.plugins.BasePlugin;
import org.example.robot.plugins.BasePluginImpl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/**
 * @Author jiyec
 * @Date 2021/5/19 10:30
 * @Version 1.0
 **/
@Slf4j
public class MainHandleJava {
    // 插件列表确保首字母大写
    private static final List<String> pluginList = new LinkedList<String>(){{
        String plugins = MainHandleJava.class.getResource("plugins").getPath();
        String[] list = new File(plugins).list((dir, name) -> !name.contains("$") && !name.contains("Base") && name.endsWith("Plugin.class"));

        for (String s : list) {
            add(s.substring(0, s.indexOf("Plugin")));
        }
    }};

    // 插件主指令  [指令-类]
    private static final Map<String, Class<? extends BasePluginImpl>> cmd2plugin1 = new HashMap<>();

    // 插件的次级指令注册为主指令 ----- Object[]{clazz, method}
    private static final Map<String, Object[]> cmd2plugin2 = new HashMap<>();

    // 全局调用插件 (在指令未匹配到插件时调用)
    private static final List<Object[]> cmd2plugin3 = new ArrayList<>();

    public static void init(Bot bot){
        // 注册插件
        registerPlugin();

        // 注册事件
        registerEvent(bot);
    }

    private static void registerEvent(Bot bot){
        bot.getEventChannel().subscribeAlways(Event.class, e->{
            // 监听所有事件
            if(e instanceof MessageEvent) {
                if(OtherHandle.isIgnored((MessageEvent) e))return;
                MessageHandle((MessageEvent) e);       // 消息事件
            }else if(e instanceof NudgeEvent) Nudge.nudge((NudgeEvent)e);        // 戳一戳事件
        });

        // 监听机器人发送消息
        bot.getEventChannel().subscribeAlways(MessagePostSendEvent.class, e->{
            long targetId = e.getTarget().getId();
            MessageReceipt receipt = e.getReceipt();
            Storage.addOldMessage(targetId, receipt);
        });
    }

    private static void MessageHandle(MessageEvent event){

        // 机器人发送，忽略
        if(event.getSender().getId() == event.getBot().getId()) return;


        String[] temp = event.getMessage().contentToString().replaceAll("  ", " ").split(" ");

        List<String> cmds = new LinkedList<>(Arrays.asList(temp));

        Class<? extends BasePluginImpl> pluginClazz;
        String cmd = cmds.get(0);
        cmds.remove(0);

        if(null != (pluginClazz = cmd2plugin1.get(cmd)))              // 优先查找插件指令
            pluginHandle(pluginClazz, cmds, event);
        else {
            Object[] pluginInfo;
            if(null != (pluginInfo = cmd2plugin2.get(cmd))){       // 查找插件子指令
                pluginHandle(pluginInfo, cmds, event);
            }else {
                AtomicBoolean exc = new AtomicBoolean(false);
                cmd2plugin2.forEach((s, m) -> {
                    if (exc.get()) return;
                    // 正则匹配  查找插件子指令
                    boolean test = false;
                    try{
                        test = Pattern.compile(s).matcher(cmd).find();
                    }catch (Exception ignored){
                    }
                    if (test) {
                        exc.set(true);
                        pluginHandle(m, cmds, event);
                    }
                });

                if (!exc.get()) {
                    // 其它处理
                    cmd2plugin3.forEach(o -> pluginHandle(o, null, event));
                }
            }
        }
    }

    // 注册为一级指令的处理
    private static void pluginHandle(Object[] pluginInfo, List<String> cmds, MessageEvent event){
        try {
            Class<? extends BasePluginImpl> clazz = (Class<? extends BasePluginImpl>)pluginInfo[0];

            // 获取 INSTANCE
            BasePlugin plugin = clazz.newInstance();

            // 初始化
            plugin.init(event, cmds);

            // 调用
            ((Method)pluginInfo[1]).invoke(plugin);

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }
    // 普通指令处理
    private static void pluginHandle(Class<? extends BasePluginImpl> pluginClazz, List<String> cmds, MessageEvent event){

        if(cmds.size()==0)return;

        try {
            // 获取 INSTANCE
            BasePlugin plugin = pluginClazz.newInstance();

            plugin.init(event, cmds);

            String subCmd = cmds.get(0);
            cmds.remove(0);

            // 请求帮助
            if("?".equals(subCmd) || "？".equals(subCmd)){
                event.getSubject().sendMessage(plugin.getHelp());
                return;
            }

            Map<String, String> subCmdList = plugin.getSubCmdList();

            if(!subCmdList.containsKey(subCmd)) {
                // 这格式似乎不对呀~(>_<。)＼
                return;
            }

            String cmd = subCmdList.get(subCmd);
            Method method = pluginClazz.getMethod(cmd);
            method.invoke(plugin);

        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        // 获取 Plugin对象
    }

    // 注册插件指令
    private static void registerPlugin(){
        log.info("注册插件指令");

        pluginList.forEach(p->{
            try {

                Class<? extends BasePluginImpl> clazz = (Class<? extends BasePluginImpl>) Class.forName(MainHandleJava.class.getPackage().getName() + ".plugins." + p + "Plugin");

                BasePlugin plugin = clazz.newInstance();

                // 增加  [指令 ---> 对象] 关联
                cmd2plugin1.put(plugin.getCmd(), clazz);

                // 注册"插件中需要注册为一级指令"的指令
                Map<String, String> registerCmd = plugin.getRegisterCmd();

                registerCmd.forEach((k, v)-> {
                    try {
                        cmd2plugin2.put(k, new Object[]{clazz, clazz.getMethod(v)});
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                });

                // 注册全局指令
                List<String> globalCmd = plugin.getGlobalCmd();
                if(null != globalCmd)
                    for (String s : globalCmd) {
                        cmd2plugin3.add(new Object[]{clazz, clazz.getMethod(s)});
                    }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });

        log.info("插件指令注册完毕");
    }
}
