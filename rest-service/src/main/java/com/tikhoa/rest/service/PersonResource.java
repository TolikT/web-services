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
    public List<Person> getPersons() {
        return new PostgreSQLDAO().getPersonsByParameters(null);
    }

    @Path("/{name}")
    @GET
    public List<Person> getPersonsByName(@PathParam("name") String name) throws
            IllegalNameException {
        if (name == null || name.trim().isEmpty()) {
            throw IllegalNameException.DEFAULT_INSTANCE;
        }
        Person person = new Person();
        person.setName(name);
        return new PostgreSQLDAO().getPersonsByParameters(person);
    }
}
