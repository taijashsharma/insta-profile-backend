package instaProfilecom.example.instaProfile.repository;

import instaProfilecom.example.instaProfile.entity.Post;
import instaProfilecom.example.instaProfile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long > {
    long countByUser(User user);
}
