package com.learn.springcloud.util;


import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Value
public class HttpErrorInfo {
    ZonedDateTime timeStamp;
    String path;
    HttpStatus httpStatus;
    String message;

    @Builder(toBuilder = true)
    HttpErrorInfo(HttpStatus httpStatus, String path, String message) {
        timeStamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.path = path;
        this.message = message;
    }

    HttpErrorInfo() {
        this.timeStamp = null;
        this.path = null;
        this.httpStatus = null;
        this.message = null;
    }
}
