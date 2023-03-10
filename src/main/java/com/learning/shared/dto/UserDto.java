package com.learning.shared.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class UserDto  implements Serializable {

    private static final long serialVersionUID = 4793540891647859172L;
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;
    private List<AddressDTO> addresses;
}
