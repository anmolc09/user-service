package com.learning.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDetailsRequestModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //TODO : make address request model as static inner class
    private List<AddressRequestModel> addresses;
}
