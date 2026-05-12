package instaProfilecom.example.instaProfile.service;

import instaProfilecom.example.instaProfile.entity.Follow;
import instaProfilecom.example.instaProfile.entity.User;
import instaProfilecom.example.instaProfile.repository.FollowRepository;
import instaProfilecom.example.instaProfile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FollowRepository followRepo;

    public void follow(String followerUsername, String followingUsername) {

        User follower = userRepo.findByUsername(followerUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User following = userRepo.findByUsername(followingUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!followRepo.existsByFollowerAndFollowing(follower, following)) {
            Follow f = new Follow();
            f.setFollower(follower);
            f.setFollowing(following);
            followRepo.save(f);
        }
    }

    public void unfollow(String followerUsername, String followingUsername) {
    }
}

