package ru.tpu.andrew.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tpu.andrew.dtos.MessageDto;
import ru.tpu.andrew.dtos.MessagesStatistics;
import ru.tpu.andrew.dtos.UserStatDto;
import ru.tpu.andrew.models.Message;
import ru.tpu.andrew.models.User;
import ru.tpu.andrew.services.MessageService;
import ru.tpu.andrew.services.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {
    private final UserService userService;
    private final MessageService messageService;

    public static Logger slf4jLogger = LoggerFactory.getLogger(ServiceController.class);


    @GetMapping
    public String getAllUsers(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("messageDto", new MessageDto());
        model.addAttribute("current_user", currentUser);
        return "service";
    }

    @PostMapping()
    public String writeMessageToUser(@ModelAttribute("messageDto") MessageDto messageDto) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        slf4jLogger.info("Пользователь " + currentUser.getUsername() + " отправил сообщение " + messageDto.getText() + " пользователю "+ messageDto.getReceiver());
        messageService.addMessageToUser(messageDto);
        return "redirect:service";
    }

    //TODO говно испраить
    @GetMapping("/my_messages")
    public String showMessages( Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> messageList = messageService.getCurrentUserMessages();
        model.addAttribute("current_user", user);
        model.addAttribute("messages", messageList);
        return "personal-messages";
    }


}
