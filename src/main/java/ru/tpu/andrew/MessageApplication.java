package ru.tpu.andrew;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tpu.andrew.dtos.MessagesStatistics;
import ru.tpu.andrew.dtos.UserDto;
import ru.tpu.andrew.models.Message;
import ru.tpu.andrew.repositories.MessageRepository;
import ru.tpu.andrew.services.MessageService;
import ru.tpu.andrew.services.UserService;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class MessageApplication  implements CommandLineRunner {

    private UserService userService;
    private MessageService messageService;

    private MessageRepository messageRepository;

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        userService.createUser(new UserDto("Dima", "1234"));
//        userService.createUser(new UserDto("Alex", "1234"));
        messageService.countMessagesFromUserToAnotherUser();
        List<MessagesStatistics> messagesStatistics = messageRepository.countMessagesFromUserToAnotherUser();
        System.out.println(messagesStatistics);
    }
}
