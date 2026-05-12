package instaProfilecom.example.instaProfile.dto;

import lombok.Data;

@Data
public class Profile {

    private String username;
    private String fullName;
    private String bio;
    private String profilePic;

    private Long posts;
    private Long followers;
    private Boolean following;

    private boolean isFollowing;


}
