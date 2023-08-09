package com.adminapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUnlockDto {

    private String empEmail;

    private String tempPwd;

    private String newPass;

}
