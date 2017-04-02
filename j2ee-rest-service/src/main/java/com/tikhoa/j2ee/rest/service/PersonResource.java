/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.j2ee.rest.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @Resource(lookup = "jdbc/tikhoa")
    private DataSource dataSource;

    @GET
    public List<Person> getPersons(@QueryParam("name") String name) {
        Person person = new Person();
        person.setName(name);
        List<Person> persons = new PostgreSQLDAO(getConnection()).getPersonsByParameters(person);
        return persons;
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
