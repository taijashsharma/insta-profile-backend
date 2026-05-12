package instaProfilecom.example.instaProfile.service;

import instaProfilecom.example.instaProfile.dto.Profile;
import instaProfilecom.example.instaProfile.entity.User;
import instaProfilecom.example.instaProfile.repository.FollowRepository;
import instaProfilecom.example.instaProfile.repository.PostRepository;
import instaProfilecom.example.instaProfile.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Data
@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FollowRepository followRepo;

    @Autowired
    private PostRepository postRepo;

    public Profile getProfile(String viewerUsername, String profileUsername) {

        User profileUser = userRepo.findByUsername(profileUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile p = new Profile();
        p.setUsername(profileUser.getUsername());
        p.setFullName(profileUser.getFullName());
        p.setBio(profileUser.getBio());
        p.setProfilePic(profileUser.getProfilePic());

        p.setPosts(postRepo.countByUser(profileUser));
        p.setFollowers(followRepo.countByFollowing(profileUser));
        p.setFollowing(followRepo.countByFollower(profileUser));

        if (viewerUsername != null) {
            User viewer = userRepo.findByUsername(viewerUsername).orElse(null);
            boolean isFollowing = followRepo
                    .existsByFollowerAndFollowing(viewer, profileUser);
            p.setFollowing(isFollowing);
        }

        return p;
    }
}
