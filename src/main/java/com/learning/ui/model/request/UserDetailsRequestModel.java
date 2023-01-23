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
    private List<AddressRequestModel> addresses;

    @Getter
    @Setter
    static class AddressRequestModel {
        private String city;
        private String country;
        private String streetName;
        private String postalCode;
        private String type;
    }

}
