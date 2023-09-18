package com.appzara.rest.controller.advice;

import lombok.Getter;

@Getter
public class GlobalHandlerException extends RuntimeException {

    private final String message;

    public GlobalHandlerException(final String message) {
        this.message = message;
    }
}


