package instaProfilecom.example.instaProfile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long Id;

    @Column(unique = true)
    private String username;
    private String password;
    private String fullName;
    private String bio;
    private String profilePic;


}
