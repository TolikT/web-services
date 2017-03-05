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
        List<Person> persons = dao.getPersons("");
        return persons;
    }

    @WebMethod(operationName = "getPersonsByParameters")
    public List<Person> getPersonsByParameters(Person params) {

        String condition = "where ";

        Field[] fields = params.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                Class t = f.getType();
                Object v = f.get(params);
                String n = f.getName();
                if (!t.isPrimitive() && v != null && !v.toString().equals("")) // found not default value
                {
                    condition += String.format("%s=\'%s\' and ", n, v);
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(PersonWebService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(PersonWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*if (params.getAge () 
        != null) {
            condition += "age=\'" + params.getAge().toString() + "\' and ";
    }

    if (params.getName () 
        != null) {
            condition += "name=\'" + params.getName().toString() + "\' and ";
    }

    if (params.getSurname () 
        != null) {
            condition += "surname=\'" + params.getSurname().toString() + "\' and ";
    }

    if (params.getIsEmployee () 
        != null) {
            condition += "is_employee=\'" + params.getIsEmployee().toString().toLowerCase() + "\' and ";
    }

    if (params.getContactDate () 
        != null) {
            condition += "contact_date=\'" + params.getContactDate().toString() + "\' and ";
    }*/
        condition = condition.substring(0, condition.length() - 4);

        System.out.println(condition);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersons(condition);
        return persons;
    }
}
