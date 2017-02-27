/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.soap.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PersonService?wsdl");
        PersonService personService = new PersonService(url);
        Person search = new Person();
        search.setName("Ольга");
        search.setSurname("Бергольц");
        List<Person> persons = personService.getPersonWebServicePort().getPersons();
        List<Person> searchPersons = personService.getPersonWebServicePort().getPersonsByParameters(search);
        for (Person person : persons) {
            System.out.println("name: " + person.getName()
                    + ", surname: " + person.getSurname() + ", age: " + person.getAge()
                    + ", isEmployee: " + String.valueOf(person.getIsEmployee()) + ", contactDate: " + person.getContactDate());
        }
        System.out.println("Total persons: " + persons.size());
        for (Person person : searchPersons) {
            System.out.println("name: " + person.getName()
                    + ", surname: " + person.getSurname() + ", age: " + person.getAge()
                    + ", isEmployee: " + String.valueOf(person.getIsEmployee()) + ", contactDate: " + person.getContactDate());
        }
        System.out.println("Total found persons: " + searchPersons.size());
        
    }
}
