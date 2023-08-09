package com.adminapi.customExe;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorMsg {

    private String errorMsg;

    private HttpStatus statusCode;
}
