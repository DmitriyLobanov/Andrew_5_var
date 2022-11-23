package ru.tpu.andrew.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tpu.andrew.dtos.UserDto;
import ru.tpu.andrew.models.User;

import javax.servlet.http.HttpServletRequest;


@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    public static Logger slf4jLogger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    //TODO сделать вывод ошибок
    @GetMapping("/success")
    public String startPage( HttpServletRequest request) {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (request.isUserInRole("ROLE_ADMIN")) {
            slf4jLogger.info("Пользователь " + user.getUsername() +" авторизовался как администратор");
            return "redirect:/admin/";
        }
        slf4jLogger.info("Пользователь " + user.getUsername() +" авторизовался");

        return "redirect:/service/";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/auth/login";
    }


}
