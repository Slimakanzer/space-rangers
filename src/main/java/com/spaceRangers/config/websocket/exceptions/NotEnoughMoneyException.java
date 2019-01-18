package com.spaceRangers.config.websocket.exceptions;

public class NotEnoughMoneyException extends Exception {
    private TypeError typeError;

    public NotEnoughMoneyException(String message, TypeError typeError){
        super(message);
        this.typeError = typeError;
    }

    public TypeError getTypeError() {
        return typeError;
    }

    public void setTypeError(TypeError typeError) {
        this.typeError = typeError;
    }
}
