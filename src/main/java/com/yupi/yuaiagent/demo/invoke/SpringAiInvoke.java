package com.yupi.yuaiagent.demo.invoke;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.comments.CommentLine;

import javax.xml.stream.events.Comment;

/**
 * Spring AI 框架调用AI
 */
@Component
public class SpringAiInvoke implements CommandLineRunner {
    @Resource
    private ChatModel deshcopeChatModel;

    @Override
    public void run(String... args) throws Exception {
        AssistantMessage output = deshcopeChatModel.call(new Prompt("你是谁？"))
                .getResult()
                .getOutput();
        System.out.println(output.getText());
    }
}
