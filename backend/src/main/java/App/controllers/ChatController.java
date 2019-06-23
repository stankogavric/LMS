package App.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import App.models.Message;
import App.services.MessageService;

@Controller
@CrossOrigin("http://localhost:4200")
public class ChatController {
	
	@Autowired
	MessageService messageService;
	
	@MessageMapping("/ws")
	@SendTo("/topic/ws")
	public Message poruka(@Payload Message receivedMessage) {
		messageService.addMessage(receivedMessage);
		return receivedMessage;
	}
}