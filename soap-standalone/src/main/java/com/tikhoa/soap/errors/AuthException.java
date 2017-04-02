/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.soap.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.tikhoa.soap.errors.PersonServiceFault")
public class AuthException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    private final PersonServiceFault fault;

    public AuthException(String message, PersonServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public AuthException(String message, PersonServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public PersonServiceFault getFaultInfo() {
        return fault;
    }
}
