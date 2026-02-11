package com.yupi.yuaiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Test
    void testChat() {
        String chaiId = UUID.randomUUID().toString();
        //第一轮
        String message = "我是程序员鲨鱼，你好";
        String answer = loveApp.doChat(message, chaiId);
        Assertions.assertNotNull(answer);
        //第二轮
        message = "如何让我的另一半（鱼皮）更爱我";
        answer = loveApp.doChat(message, chaiId);
        Assertions.assertNotNull(answer);
        //第三轮
        message = "我的另一半叫什么，我刚刚和你说过，帮我回忆一下";
        answer = loveApp.doChat(message, chaiId);
    }

    @Test
    void doChat() {
    }

    @Test
    void doChatWithReport() {
        String chaiId = UUID.randomUUID().toString();
        String message = "我是程序员鲨鱼，我想让我的另一半（可乐）更爱我。但是我不知道要怎么做";
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport(message, chaiId);
        Assertions.assertNotNull(loveReport);
    }

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
        String message = "我已经结婚了，但是婚后关系不太亲密，怎么办？";
        String answer =  loveApp.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }

}