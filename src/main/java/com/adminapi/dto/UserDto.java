package com.adminapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class UserDto {

    private String userName;

    private String userEmail;

    private String userPhoneNumber;

    private String gender;

    private LocalDate dob;

    private String ssn;

    private String userState;
}
