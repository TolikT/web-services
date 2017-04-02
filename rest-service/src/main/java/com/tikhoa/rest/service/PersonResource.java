/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.rest.service;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPersonsByName(@QueryParam("name") String name) {
        Person person = new Person();
        person.setName(name);
        List<Person> persons = new PostgreSQLDAO().getPersonsByParameters(person);
        return persons;
    }

    @PUT
    public int insertPerson(
            @QueryParam("name") String name,
            @QueryParam("surname") String surname,
            @QueryParam("age") Integer age,
            @QueryParam("isEmployee") Boolean isEmployee,
            @QueryParam("contactDate") String contactDate) {
        Person person = new Person();
        if (name != null) {
            person.setName(name);
        }
        if (surname != null) {
            person.setSurname(surname);
        }
        if (age != null) {
            person.setAge(age);
        }
        if (isEmployee != null) {
            person.setIsEmployee(isEmployee);
        }
        if (contactDate != null) {
            person.setContactDate(contactDate);
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.insertPerson(person);
        return exitStatus;
    }

    @POST
    public int updatePerson(
            @QueryParam("id") Integer id,
            @QueryParam("name") String name,
            @QueryParam("surname") String surname,
            @QueryParam("age") Integer age,
            @QueryParam("isEmployee") Boolean isEmployee,
            @QueryParam("contactDate") String contactDate) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.updatePerson(id, name, surname, age, isEmployee, contactDate);
        return exitStatus;
    }

    @DELETE
    public int deletePerson(@QueryParam("id") Integer id) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int exitStatus = dao.deletePerson(id);
        return exitStatus;
    }
}
