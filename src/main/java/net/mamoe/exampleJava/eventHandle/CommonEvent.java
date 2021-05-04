package net.mamoe.exampleJava.eventHandle;

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
public class CommonEvent {
    public static void main(Bot bot){
        plainText(bot);
        nudge(bot);
        rawFace(bot);
        sendImage(bot);
    }

    /**
     * 发送图片
     *
     * 触发条件：发送字符串 ---> “图片”
     *
     * 触发效果：机器人回复 “这是你要的图片 + [图片]”
     *
     * 参考：https://github.com/mamoe/mirai/issues/1045
     *
     * @param bot
     */
    public static void sendImage(Bot bot){
        bot.getEventChannel().subscribeAlways(MessageEvent.class, event->{
            String msg = event.getMessage().toString();
            if(msg.contains("图片")){
                try {
                    URL resource = CommonEvent.class.getResource("/6ae5b66fddc451dacf74cd38bdfd5266d1163210.jpg");
                    FileInputStream fileInputStream = new FileInputStream(resource.getFile());
                    Image image = Contact.uploadImage(event.getSubject(), fileInputStream);
                    MessageChain messages = MessageUtils.newChain(new PlainText("这是你要的图片"), image);
                    event.getSubject().sendMessage(messages);
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    event.getSubject().sendMessage("我没有找到要发送的文件╮(╯▽╰)╭");
                } catch (IOException e) {
                    e.printStackTrace();
                    event.getSubject().sendMessage("粗了一些奇怪的事情");
                }
            }
        });
    }

    /**
     * 纯文本
     *
     * 触发条件：发送字符串 ---> “纯文本”
     *
     * 触发效果：机器人回复 “这是一条纯文本消息”
     *
     * @param bot
     */
    public static void plainText(Bot bot){
        bot.getEventChannel().subscribeAlways(MessageEvent.class, event->{
            String msg = event.getMessage().toString();
            if(msg.contains("纯文本")){
                event.getSubject().sendMessage(new PlainText("这是一条纯文本消息"));
            }
        });
    }

    /**
     * 戳一戳
     * 触发条件：
     * 在好友对话界面或QQ群聊天界面双击机器人头像
     *
     * 触发效果：
     * 发起者被机器人“戳了一下”
     *
     */
    public static void nudge(Bot bot){
        bot.getEventChannel().subscribeAlways(NudgeEvent.class, event-> {

            if(event.getTarget() == bot){
                event.getFrom().nudge().sendTo(event.getSubject());
            }
        });
    }

    /**
     * 原生表情
     *
     * 触发条件：机器人收到 “表情” 信息
     *
     * 触发效果：发送这收到一个斜眼笑表情
     *
     */
    public static void rawFace(Bot bot){

        bot.getEventChannel().subscribeAlways(MessageEvent.class, event->{
            String msg = event.getMessage().toString();
            if(msg.contains("表情")){
                event.getSubject().sendMessage(new Face(Face.XIE_YAN_XIAO));
            }
        });
    }
}
