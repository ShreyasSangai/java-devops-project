package com.devops.app.controller;

import com.devops.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null)  model.addAttribute("error", "Invalid username or password!");
        if (logout != null) model.addAttribute("message", "Logged out successfully!");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "signup";
        }
        if (userService.isUsernameExists(username)) {
            model.addAttribute("error", "Username already taken!");
            return "signup";
        }
        if (userService.isEmailExists(email)) {
            model.addAttribute("error", "Email already registered!");
            return "signup";
        }

        userService.registerUser(username, email, password);
        return "redirect:/login?registered=true";
    }
}