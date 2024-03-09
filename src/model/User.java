package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class User {
    private Integer userID;
    private String userUUID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Boolean isDeleted;
    private Boolean isVerified;

    public User(Integer userID, String userUUID, String userName, String userEmail, String userPassword, Boolean isDeleted, Boolean isVerified) {
        this.userID = userID;
        this.userUUID = userUUID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isDeleted = isDeleted;
        this.isVerified = isVerified;
    }
}
