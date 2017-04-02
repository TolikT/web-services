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

    /*@GET
    public List<Person> getPersons() {
        List<Person> persons = new PostgreSQLDAO().getPersons();
        return persons;
    }*/
}
