package instaProfilecom.example.instaProfile.controller;

import instaProfilecom.example.instaProfile.dto.Profile;
import instaProfilecom.example.instaProfile.entity.User;
import instaProfilecom.example.instaProfile.service.FollowService;
import instaProfilecom.example.instaProfile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/{username}")
    public void follow(
            @RequestHeader String me,
            @PathVariable String username
    ) {
        followService.follow(me, username);
    }

    @GetMapping("/{username}")
    public Profile getProfile(@PathVariable String username) {

        User viewer = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ProfileService.getProfile(
                viewer.getUsername(),
                username
        );
    }

}
