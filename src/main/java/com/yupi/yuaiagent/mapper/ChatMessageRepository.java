package com.yupi.yuaiagent.mapper;

import com.baomidou.mybatisplus.extension.repository.CrudRepository;
import com.yupi.yuaiagent.pojo.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageRepository extends CrudRepository<ChatMessageMapper, ChatMessage> {
}