package org.example.robot;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.example.robot.provider.WSeg;


import java.io.File;

/**
 * @Author jiyec
 * @Date 2021/5/4 23:00
 * @Version 1.0
 **/
@Slf4j
public class RobotMain {
    @Getter
    private static Bot bot;

    public static void init(Long id, String pass){
        init(id, pass, new BotConfiguration() {{
            String path = RobotMain.class.getResource("/").getPath();
            new File(path + "/robot/cache/" + id).mkdirs();
            // 配置，例如：
            fileBasedDeviceInfo(RobotMain.class.getResource("/robot/").getPath()+ "device.json");
            setCacheDir(new File(RobotMain.class.getResource("/robot/cache/" + id).getFile()));
        }});
    }

    public static void init(Long id, String pass, BotConfiguration config){

        log.info("新建机器人实例");
        bot = BotFactory.INSTANCE.newBot(id, pass, config);
        log.info("开始登录");
        bot.login();

        afterLogin();
    }

    public static void logout(){
        if(null != bot)
            bot.close();
    }

    private static void afterLogin(){
        log.info("处理登录后操作");

        // DataHandle.init(bot);
        MainHandleJava.init(bot);

        log.info("数据初始化");

        // 分词初始化
        // new InitWseg().start();
    }

}

@Slf4j
class InitWseg extends Thread{
    @Override
    public void run() {
        log.info("分词模型预加载....");
        WSeg.seg("笑死了");
        log.info("分词模型预加载完毕....");
    }
}