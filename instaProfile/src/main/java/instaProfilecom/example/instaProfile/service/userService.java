package instaProfilecom.example.instaProfile.service;

import instaProfilecom.example.instaProfile.dto.login;
import instaProfilecom.example.instaProfile.dto.signup;
import instaProfilecom.example.instaProfile.entity.User;
import instaProfilecom.example.instaProfile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User signup(signup dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setBio("");
        user.setProfilePic("");

        return userRepo.save(user);
    }

    public String login(login dto) {

        User user = userRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.createToken(user);
    }

    public User myProfile(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserProfile(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
