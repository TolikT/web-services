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

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersons();
        return persons;
    }
    
    @WebMethod(operationName = "insertPerson")
    public int insertPerson(String name, String surname, Integer age, Boolean isEmployee, String contactDate) {
        Person person = new Person(name, surname, age, isEmployee, contactDate);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.insertPerson(person);
        return exitStatus;
    }
    
    @WebMethod(operationName = "updatePerson")
public int updatePerson(String name, String surname, Integer age, Boolean isEmployee, String contactDate, Integer id) {
        Person person = new Person(name, surname, age, isEmployee, contactDate);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.updatePerson(id, name, surname, age, isEmployee, contactDate);
        return exitStatus;
    }
    
    @WebMethod(operationName = "deletePerson")
    public int deletePerson(Integer id) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.deletePerson(id);
        return exitStatus;
    }

    @WebMethod(operationName = "getPersonsByParameters")
    public List<Person> getPersonsByParameters(String name, String surname, Integer age, Boolean isEmployee, String contactDate) {
        Person person = new Person(name, surname, age, isEmployee, contactDate);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersonsByParameters(person);
        return persons;
    }
    
    @WebMethod(operationName = "getId")
    public Integer getId(String name, String surname, Integer age, Boolean isEmployee, String contactDate) {
        Person person = new Person(name, surname, age, isEmployee, contactDate);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer id = dao.getId(person);
        return id;
    }
}
