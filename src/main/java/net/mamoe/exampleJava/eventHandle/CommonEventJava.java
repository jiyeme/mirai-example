package net.mamoe.exampleJava.eventHandle;

import net.mamoe.exampleJava.eventHandle.common.*;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import net.mamoe.mirai.message.data.*;

import java.io.*;
import java.net.URL;

/**
 * 通用事件
 *
 * @Author jiyec
 * @Date 2021/5/4 13:34
 * @Version 1.0
 **/
public class CommonEventJava {

    public static void main(Bot bot){
        FaceJava.main(bot);
        ImageJava.main(bot);
        NudgeJava.main(bot);
        RichMessageJava.main(bot);
        TextJava.main(bot);
    }

}
