/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.rest.service;

public class IllegalNameException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    public static IllegalNameException DEFAULT_INSTANCE = new IllegalNameException("personName cannot be null or empty");

    public IllegalNameException(String message) {
        super(message);
    }
}
