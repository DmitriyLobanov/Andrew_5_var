package ru.tpu.andrew.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tpu.andrew.dtos.MessagesStatistics;
import ru.tpu.andrew.dtos.UserDto;
import ru.tpu.andrew.models.User;
import ru.tpu.andrew.services.MessageService;
import ru.tpu.andrew.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MessageService messageService;
    private final UserService userService;

    public static Logger slf4jLogger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/message_stat")
    public String countMessagesFromUserToAnotherUser(Model model) {
        List<MessagesStatistics> messagesStatistics = messageService.countMessagesFromUserToAnotherUser();
        model.addAttribute("statistics", messagesStatistics);
        return "users-stat";
    }
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteUser(id);
        slf4jLogger.info("Пользователь с id:" + id + "был удален пользователем " + user.getUsername());
        return "redirect:/admin/users_list";
    }

    @GetMapping("/users_list")
    public String getAllRegisteredUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("all_users", users);
        return "admin-list";
    }

    @GetMapping("/registration")
    public String userRegistrationPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "admin-registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        if (user == null) {
            return "redirect:/registration";
        }
        slf4jLogger.info("Пользователь " + user.getUsername() + " успешно зарегистрирован");
        return "redirect:/admin";
    }

    @GetMapping
    public String adminStartPage(){
        return "admin-start";
    }
}
