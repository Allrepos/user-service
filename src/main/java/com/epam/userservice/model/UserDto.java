package com.epam.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String emailId;

    private String password;

    private String firstName;

    private String lastName;

    private String panNumber;

    private String bankAccountNumber;
}
