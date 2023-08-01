package com.epam.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_TBL")
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_EMAIL_ID", unique = true)
    private String emailId;

    @Column(name = "USER_PSWD")
    private String password;

    @Column(name = "USER_FIRST_NAME")
    private String firstName;

    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @Column(name = "USER_PAN_NUMBER", unique = true)
    private String panNumber;

    @Column(name = "USER_BANK_ACCOUNT_NUMBER")
    private String bankAccountNumber;
}
