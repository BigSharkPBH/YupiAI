package com.yupi.yuaiagent.demo.invoke;

import cn.hutool.http.HttpUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
/**
 * 使用Hutool发送HTTP请求调用DashScope的AI模型
 */
public class HttpAIInvoke {

    private static final String DASHSCOPE_API_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

    /**
     * 使用Hutool发送AI请求
     *
     * @param apiKey API密钥
     * @return 响应结果
     */
    public static String invokeAI(String apiKey) {
        // 构建请求体JSON
        JSONObject requestBody = new JSONObject();
        requestBody.set("model", "qwen-plus");
        
        JSONObject input = new JSONObject();
        JSONArray messages = new JSONArray();
        
        JSONObject systemMessage = new JSONObject();
        systemMessage.set("role", "system");
        systemMessage.set("content", "You are a helpful assistant.");
        messages.add(systemMessage);
        
        JSONObject userMessage = new JSONObject();
        userMessage.set("role", "user");
        userMessage.set("content", "你是谁？");
        messages.add(userMessage);
        
        input.set("messages", messages);
        requestBody.set("input", input);
        
        JSONObject parameters = new JSONObject();
        parameters.set("result_format", "message");
        requestBody.set("parameters", parameters);

        // 发送POST请求
        String result = HttpRequest.post(DASHSCOPE_API_URL)
                .header("Authorization", "Bearer " + apiKey)  // 设置Authorization头
                .header("Content-Type", "application/json")   // 设置内容类型
                .body(requestBody.toString())                 // 设置请求体
                .timeout(20000)                               // 设置超时时间（毫秒）
                .execute()
                .body();

        return result;
    }

    /**
     * 使用Hutool发送AI请求（更灵活的参数版本）
     *
     * @param apiKey API密钥
     * @param userMessage 用户输入的消息
     * @return 响应结果
     */
    public static String invokeAIWithMessage(String apiKey, String userMessage) {
        // 构建请求体JSON
        JSONObject requestBody = new JSONObject();
        requestBody.set("model", "qwen-plus");
        
        JSONObject input = new JSONObject();
        JSONArray messages = new JSONArray();
        
        JSONObject systemMessage = new JSONObject();
        systemMessage.set("role", "system");
        systemMessage.set("content", "You are a helpful assistant.");
        messages.add(systemMessage);
        
        JSONObject msg = new JSONObject();
        msg.set("role", "user");
        msg.set("content", userMessage);
        messages.add(msg);
        
        input.set("messages", messages);
        requestBody.set("input", input);
        
        JSONObject parameters = new JSONObject();
        parameters.set("result_format", "message");
        requestBody.set("parameters", parameters);

        // 发送POST请求
        String result = HttpRequest.post(DASHSCOPE_API_URL)
                .header("Authorization", "Bearer " + apiKey)  // 设置Authorization头
                .header("Content-Type", "application/json")   // 设置内容类型
                .body(requestBody.toString())                 // 设置请求体
                .timeout(20000)                               // 设置超时时间（毫秒）
                .execute()
                .body();

        return result;
    }

    // 测试方法
    public static void main(String[] args) {
        // 注意：这里需要替换成真实的API Key才能实际运行
        String apiKey = TestApiKey.API_KEY; // 从环境变量获取API Key
        
        if (StrUtil.isBlank(apiKey)) {
            System.out.println("请设置环境变量 DASHSCOPE_API_KEY");
            return;
        }

        try {
            String response = invokeAI(apiKey);
            System.out.println("API响应: " + response);
        } catch (Exception e) {
            System.err.println("请求失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}