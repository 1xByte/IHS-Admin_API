package com.adminapi.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLogin {

    private String adminEmail;

    private String adminPass;
}
