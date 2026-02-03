package com.yupi.yuaiagent;

import com.yupi.yuaiagent.demo.invoke.HttpAIInvoke;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class HttpAIInvokeTest {

    @Test
    public void testInvokeAI() {
        // 这里只是测试代码结构是否正确，不会实际调用API
        // 因为我们没有真实的API密钥
        String apiKey = System.getenv("DASHSCOPE_API_KEY");
        
        if (apiKey != null && !apiKey.isEmpty()) {
            String response = HttpAIInvoke.invokeAI(apiKey);
            assertNotNull(response);
            System.out.println("API Response: " + response);
        } else {
            System.out.println("跳过测试 - 未设置DASHSCOPE_API_KEY环境变量");
        }
    }
    
    @Test
    public void testInvokeAIWithMessage() {
        String apiKey = System.getenv("DASHSCOPE_API_KEY");
        
        if (apiKey != null && !apiKey.isEmpty()) {
            String response = HttpAIInvoke.invokeAIWithMessage(apiKey, "你好，世界！");
            assertNotNull(response);
            System.out.println("API Response: " + response);
        } else {
            System.out.println("跳过测试 - 未设置DASHSCOPE_API_KEY环境变量");
        }
    }
}