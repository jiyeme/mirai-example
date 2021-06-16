package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.robot.RobotMain;

import java.io.IOException;

/**
 * @Author jiyec
 * @Date 2021/5/3 23:02
 * @Version 1.0
 **/
@Slf4j
public class RunRobot {

    public static void main(String[] args) throws IOException {
        System.setProperty("java.io.tmpdir", "D:/tomcat/apache-tomcat-9.0.43/temp");
        String qqid = System.getenv("QQID");
        String pass = System.getenv("QQPASS");
        // String token = System.getenv("HANLP_TOKEN");
        // WSeg.setToken(token);

        if(qqid == null){
            log.error("账号为空");
            return;
        }

        long id = Long.parseLong(qqid);
        if(id != 0L && pass != null)
            RobotMain.init(id, pass);
        else
            log.info("机器人账号数据似乎未初始化，无法启动机器人！");
    }

}