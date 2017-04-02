/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.j2ee.rest.service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostgreSQLDAO {

    private final Connection connection;

    public PostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons");

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                boolean isEmployee = rs.getBoolean("is_employee");
                String contactDate = rs.getDate("contact_date").toString();

                Person person = new Person(name, surname, age, isEmployee, contactDate);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public List<Person> getPersonsByParameters(Person person) {
        List<Person> persons = new ArrayList<>();
        try {
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
                    Logger.getLogger("ERR").log(Level.SEVERE, null, ex);
                }
            }

            condition = condition.substring(0, condition.length() - 4);

            ResultSet rs = stmt.executeQuery(String.format("select * from persons %s", condition));

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

    public Integer getId(Person person) {
        Integer id = null;
        try {
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
                        if (n.equals("contactDate")) {
                            n = "contact_date";
                        }
                        if (n.equals("isEmployee")) {
                            n = "is_employee";
                        }
                        condition += String.format("%s=\'%s\' and ", n, v);
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger("ERR").log(Level.SEVERE, null, ex);
                }
            }

            condition = condition.substring(0, condition.length() - 4);

            ResultSet rs = stmt.executeQuery(String.format("select id from persons %s", condition));

            rs.next();
            id = rs.getInt("id");

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public int insertPerson(Person person) {
        try {
            Statement stmt = connection.createStatement();
            String updateLineFirstPart = "INSERT INTO persons(";
            String updateLineSecondPart = " VALUES(";
            Field[] fields = person.getClass().getDeclaredFields();
            for (Field f : fields) {
                try {
                    f.setAccessible(true);
                    Class t = f.getType();
                    Object v = f.get(person);
                    String n = f.getName();
                    if (!t.isPrimitive() && v != null && !v.toString().equals("")) // found not default value
                    {
                        if (n.equals("contactDate")) {
                            n = "contact_date";
                        }
                        if (n.equals("isEmployee")) {
                            n = "is_employee";
                        }
                        updateLineFirstPart += n + ",";
                        updateLineSecondPart += String.format("\'%s\',", v.toString());
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger("ERR").log(Level.SEVERE, null, ex);
                }
            }
            updateLineFirstPart = String.format("%s)", updateLineFirstPart.substring(0, updateLineFirstPart.length() - 1));
            updateLineSecondPart = String.format("%s)", updateLineSecondPart.substring(0, updateLineSecondPart.length() - 1));
            String updateQuery = updateLineFirstPart + updateLineSecondPart;
            int rs = stmt.executeUpdate(updateQuery);

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getId(person);
    }

    public Integer updatePerson(Integer id, String name, String surname, Integer age, Boolean isEmployee, String contactDate) {
        Integer result = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE persons SET name = ?, surname = ?, age = ?, is_employee = ?, contact_date = ? WHERE id = ?");

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setInt(3, age);
            ps.setBoolean(4, isEmployee);

            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(contactDate);

            ps.setDate(5, new java.sql.Date(date.getTime()));
            ps.setInt(6, id);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = 1;
        }
        return result;
    }

    int deletePerson(Integer id) {
        Integer result = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE from persons WHERE id = ?");

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = 1;
        }
        return result;
    }
}
