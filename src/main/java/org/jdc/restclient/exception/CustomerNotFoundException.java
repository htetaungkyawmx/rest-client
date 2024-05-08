package org.jdc.restclient.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CustomerNotFoundException extends ResponseStatusException {
    public CustomerNotFoundException() {
        super(HttpStatusCode.valueOf(404),"Customer Not Found!");
    }
}
