package instaProfilecom.example.instaProfile.repository;

import instaProfilecom.example.instaProfile.entity.Follow;
import instaProfilecom.example.instaProfile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    long countByFollowing(User user); // followers
    long countByFollower(User user);  // following

    boolean existsByFollowerAndFollowing(User follower, User following);
}
