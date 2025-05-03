package com.example.rag_demo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {


    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder, VectorStore vectorStore ) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String solicitud){
        return chatClient.prompt().user(solicitud).call().content();
    }


}
