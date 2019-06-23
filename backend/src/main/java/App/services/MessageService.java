package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Message;
import App.repositories.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepo;

    public MessageService() {
    }

    public Iterable<Message> getMessages() {
        return messageRepo.findAll();
    }
    
    public Iterable<Message> getMessagesByUser(String username) {
        return messageRepo.findByRecipientOrSender(username, username);
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepo.findById(id);
    }

    public void addMessage(Message message) {
    	String recipientUsername = message.getRecipient();
    	String senderUsername = message.getSender();
    	if(recipientUsername != null && senderUsername != null) {
    		message.setRecipient(recipientUsername);
    		message.setSender(senderUsername);
    		messageRepo.save(message);
    	}
    }

    public void removeMessage(Long id) {
        Optional<Message> message = messageRepo.findById(id);
        messageRepo.delete(message.get());
    }

    public void updateMessage(Long id, Message message) {
        Optional<Message> Mes = messageRepo.findById(id);
        if(Mes.isPresent()) {
            message.setId(Mes.get().getId());
            messageRepo.save(message);
        }
    }

}

