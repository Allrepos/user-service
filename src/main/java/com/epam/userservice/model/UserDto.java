package com.epam.userservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String emailId;

    private String password;

    private String firstName;

    private String lastName;

    private String panNumber;

    private int bankAccountNumber;
}
