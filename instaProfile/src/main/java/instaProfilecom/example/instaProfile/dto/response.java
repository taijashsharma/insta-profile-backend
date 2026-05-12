package instaProfilecom.example.instaProfile.dto;

import lombok.Data;

@Data
public class response {

    private Long id;
    private String username;
    private String fullName;
    private String bio;
    private String profilePic;
}
