package net.mamoe.exampleJava.eventHandle;

import net.mamoe.mirai.Bot;

/**
 * @Author jiyec
 * @Date 2021/5/5 18:35
 * @Version 1.0
 **/
public class MainHandleJava {
    public static void main(Bot bot){
        CommonEventJava.main(bot);
        FriendEventJava.main(bot);
        GroupEventJava.main(bot);
    }
}
