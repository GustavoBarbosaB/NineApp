package com.example.gustavobarbosab.ninemessage.models.events;

/**
 * Created by gustavobarbosab on 31/01/18.
 */

public class ErrorEvent {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ErrorEvent(String error) {

        this.error = error;
    }
}
