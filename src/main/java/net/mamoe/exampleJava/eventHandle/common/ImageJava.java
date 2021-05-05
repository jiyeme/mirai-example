package net.mamoe.exampleJava.eventHandle.common;

import net.mamoe.exampleJava.eventHandle.CommonEventJava;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @Author jiyec
 * @Date 2021/5/5 18:57
 * @Version 1.0
 **/
public class ImageJava {

    public static void main(Bot bot){
        sendImage(bot);
    }

    /**
     * 发送图片
     *
     * 触发条件：发送字符串 ---> “java图片”
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
            if(msg.contains("java图片")){
                try {
                    URL resource = CommonEventJava.class.getResource("/6ae5b66fddc451dacf74cd38bdfd5266d1163210.jpg");
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

}
