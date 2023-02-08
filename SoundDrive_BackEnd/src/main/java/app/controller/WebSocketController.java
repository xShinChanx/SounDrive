package app.controller;

import app.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/createSong")
    @SendTo("/song/message")
    public Message createSong(String name){
        return Message.builder()
                .message("New song is created:" + name)
                .build();
    }

    @MessageMapping("/deleteSong")
    @SendTo("/song/message")
    public Message deleteSong(String name){
        return Message.builder()
                .message("Song has been deleted:" + name)
                .build();
    }
}
