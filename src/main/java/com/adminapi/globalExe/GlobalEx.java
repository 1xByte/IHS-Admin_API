package com.adminapi.globalExe;

import com.adminapi.customExe.ErrorMsg;
import com.adminapi.customExe.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalEx {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMsg> NotFoundMehod(NotFoundException ex){

        ErrorMsg msg=new ErrorMsg(ex.getMessage(), HttpStatus.NOT_FOUND);

        log.error("Not Found Exception", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(msg);
        //return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMsg> exceptionMethod(Exception ex){
        log.error("Exception", HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok(new ErrorMsg(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
