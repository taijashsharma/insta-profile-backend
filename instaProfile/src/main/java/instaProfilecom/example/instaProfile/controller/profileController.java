package instaProfilecom.example.instaProfile.controller;

import instaProfilecom.example.instaProfile.dto.Profile;
import instaProfilecom.example.instaProfile.entity.User;
import instaProfilecom.example.instaProfile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/profile")
public class profileController {


        @Autowired
        private ProfileService profileService;

        @GetMapping("/{username}")
        public Profile getProfile(
                @RequestHeader(required = false) String viewer,
                @PathVariable String username
        ) {
            return profileService.getProfile(viewer, username);
        }
    @GetMapping("/{username}")
    public Profile getProfile(@PathVariable String username) {

        User viewer = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return profileService.getProfile(
                viewer.getUsername(),
                username
        );
    }

}
