/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.soap.standalone;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

public class PostgreSQLDAO {

    public List<Person> getPersonsByParameters(Person person) {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();

            String condition = "where ";

            Field[] fields = person.getClass().getDeclaredFields();
            for (Field f : fields) {
                try {
                    f.setAccessible(true);
                    Class t = f.getType();
                    Object v = f.get(person);
                    String n = f.getName();
                    if (!t.isPrimitive() && v != null && !v.toString().equals("")) // found not default value
                    {
                        condition += String.format("%s=\'%s\' and ", n, v);
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(PersonWebService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            condition = condition.substring(0, condition.length() - 4);

            ResultSet rs = stmt.executeQuery(String.format("select id from persons %s", condition));

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                boolean isEmployee = rs.getBoolean("is_employee");
                String contactDate = rs.getDate("contact_date").toString();

                Person collectedPerson = new Person(name, surname, age, isEmployee, contactDate);
                persons.add(collectedPerson);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }
}
