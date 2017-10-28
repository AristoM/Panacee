package com.panaceedental.panaceedental.Parcers;

import java.io.Serializable;

/**
 * Created by aristomichael on 12/09/17.
 */

public class CommonParcer implements Serializable {

    private String status;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
