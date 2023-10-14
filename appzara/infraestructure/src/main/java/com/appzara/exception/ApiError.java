package com.appzara.exception;

import org.springframework.http.HttpStatus;

public record ApiError(HttpStatus status, String message) {

}


