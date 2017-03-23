/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.soap.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PersonService?wsdl");
        PersonService personService = new PersonService(url);
        List<Person> persons = personService.getPersonWebServicePort().getPersons();
        for (Person person : persons) {
            System.out.println("name: " + person.getName()
                    + ", surname: " + person.getSurname() + ", age: " + person.getAge()
                    + ", isEmployee: " + String.valueOf(person.isIsEmployee()) + ", contactDate: " + person.getContactDate());
        }
        try {
            List<Person> personsByParameters = personService.getPersonWebServicePort().getPersonsByParameters("", "", 25, Boolean.FALSE, "2017-03-06");
            for (Person person : personsByParameters) {
                System.out.println("name: " + person.getName()
                        + ", surname: " + person.getSurname() + ", age: " + person.getAge()
                        + ", isEmployee: " + String.valueOf(person.isIsEmployee()) + ", contactDate: " + person.getContactDate());
            }
        } catch (IllegalNameException ex) {
            Logger.getLogger(WebServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Total persons: " + persons.size());
        Integer status1 = personService.getPersonWebServicePort().deletePerson(17);
        System.out.println("Delete status is " + status1);
        Integer status2 = personService.getPersonWebServicePort().updatePerson("Anatoly", "Tikhomirov", 25, Boolean.TRUE, "2017-03-06", 1);
        System.out.println("Delete status is " + status2);
        Integer newId = personService.getPersonWebServicePort().insertPerson("Rasul", "Rasulovich", 25, Boolean.TRUE, "2017-03-06");
        System.out.println("New id is " + newId);
    }
}
