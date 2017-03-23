/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.soap.standalone;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "getPersonsCommon")
    public List<Person> getPersonsCommon(String name, String surname, Integer age, Boolean isEmployee, String contactDate) {
        Person person = new Person(name, surname, age, isEmployee, contactDate);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersonsByParameters(person);
        return persons;
    }
}
