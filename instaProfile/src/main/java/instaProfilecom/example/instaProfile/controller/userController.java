package instaProfilecom.example.instaProfile.controller;

import instaProfilecom.example.instaProfile.dto.login;
import instaProfilecom.example.instaProfile.dto.signup;
import instaProfilecom.example.instaProfile.entity.User;
import instaProfilecom.example.instaProfile.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class userController {

    @Autowired
    private userService service;

    @PostMapping("/signup")
    public User signup(@RequestBody signup dto) {
        return service.signup(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody login dto) {
        return service.login(dto);
    }

    @GetMapping("/me/{username}")
    public User myProfile(@PathVariable String username) {
        return service.myProfile(username);
    }

    @GetMapping("/user/{username}")
    public User getUserProfile(@PathVariable String username) {
        return service.getUserProfile(username);
    }
}
