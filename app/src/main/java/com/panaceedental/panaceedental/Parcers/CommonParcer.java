package com.panaceedental.panaceedental.Parcers;

import java.io.Serializable;

/**
 * Created by aristomichael on 12/09/17.
 */

public class CommonParcer implements Serializable {

    String error;
    String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
