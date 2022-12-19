package com.learning.ui.model.response;

import lombok.Data;

import java.util.List;

@Data
public class UserRest {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    //TODO : make inner class
    private List<AddressesRest> addresses;
  }
