/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.soap.standalone;

import com.tikhoa.soap.errors.AuthException;
import com.tikhoa.soap.errors.IllegalNameException;
import com.tikhoa.soap.errors.PersonServiceFault;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password) throws AuthException {
        throwAuthException(username, password);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersons();
        return persons;
    }

    @WebMethod(operationName = "insertPerson")
    public int insertPerson(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "personName") String name,
            @WebParam(name = "personSurname") String surname,
            @WebParam(name = "personAge") Integer age,
            @WebParam(name = "personIsEmployee") Boolean isEmployee,
            @WebParam(name = "personContactDate") String contactDate) throws AuthException {
        throwAuthException(username, password);
        Person person = new Person(name, surname, age, isEmployee, contactDate);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.insertPerson(person);
        return exitStatus;
    }

    @WebMethod(operationName = "updatePerson")
    public int updatePerson(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "personName") String name,
            @WebParam(name = "personSurname") String surname,
            @WebParam(name = "personAge") Integer age,
            @WebParam(name = "personIsEmployee") Boolean isEmployee,
            @WebParam(name = "personContactDate") String contactDate,
            @WebParam(name = "id") Integer id
    ) throws AuthException {
        throwAuthException(username, password);
        Person person = new Person(name, surname, age, isEmployee, contactDate);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.updatePerson(id, name, surname, age, isEmployee, contactDate);
        return exitStatus;
    }

    @WebMethod(operationName = "deletePerson")
    public int deletePerson(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "id") Integer id
    ) throws AuthException {
        throwAuthException(username, password);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.deletePerson(id);
        return exitStatus;
    }

    @WebMethod(operationName = "getPersonsByParameters")
    public List<Person> getPersonsByParameters(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "personName") String name,
            @WebParam(name = "personSurname") String surname,
            @WebParam(name = "personAge") Integer age,
            @WebParam(name = "personIsEmployee") Boolean isEmployee,
            @WebParam(name = "personContactDate") String contactDate
    ) throws IllegalNameException, AuthException {
        throwAuthException(username, password);
        if (name == null || name.trim().isEmpty()) {
            PersonServiceFault fault = PersonServiceFault.defaultInstance();
            throw new IllegalNameException("personName is not specified", fault);
        }

        Person person = new Person(name, surname, age, isEmployee, contactDate);

        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersonsByParameters(person);
        return persons;
    }

    @WebMethod(operationName = "getId")
    public Integer getId(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "personName") String name,
            @WebParam(name = "personSurname") String surname,
            @WebParam(name = "personAge") Integer age,
            @WebParam(name = "personIsEmployee") Boolean isEmployee,
            @WebParam(name = "personContactDate") String contactDate) throws AuthException {
        throwAuthException(username, password);
        Person person = new Person(name, surname, age, isEmployee, contactDate);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer id = dao.getId(person);
        return id;
    }

    private int throwAuthException(String user, String password) throws AuthException {
        if (!"tikhoa".equals(user) || !"123".equals(password)) {
            PersonServiceFault fault = PersonServiceFault.defaultInstance();
            throw new AuthException("Authentication failed", fault);
        }
        return 0;
    }
}
