package ru.tpu.andrew.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.tpu.andrew.dtos.MessageDto;
import ru.tpu.andrew.dtos.MessagesStatistics;
import ru.tpu.andrew.dtos.UserStatDto;
import ru.tpu.andrew.models.Message;
import ru.tpu.andrew.models.User;
import ru.tpu.andrew.repositories.MessageRepository;
import ru.tpu.andrew.repositories.UserRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public List<MessagesStatistics> countMessagesFromUserToAnotherUser() {
        return messageRepository.countMessagesFromUserToAnotherUser();
    }

    public List<Message> getCurrentUserMessages() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return messageRepository.findAllPersonalMessages(currentUser.getId());
    }

    public void addMessageToUser(MessageDto messageDto) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User receiver = userRepository.findByUsername(messageDto.getReceiver()).orElse(null);
        Message message = new Message(null, messageDto.getText(), receiver, currentUser);
        messageRepository.save(message);
    }
}
