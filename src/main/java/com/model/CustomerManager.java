package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateful;
import com.entity.Customer;

@Stateful
public class CustomerManager implements CustomerRemote {
    private static final String URL = "jdbc:mysql://localhost:3306/airlines";
    private static final String USER = "root";
    private static final String PASSWORD = "Saleem@123";

    @Override
    public boolean Create_customer(Customer c) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "INSERT INTO customers (fullName, mobileNo, email, password ) VALUES (?, ?, ?, ?)")) {
            psmt.setString(1, c.getFullName());
            psmt.setString(2, c.getMobileNo());
            psmt.setString(3, c.getEmail());
            psmt.setString(4, c.getPassword());
            psmt.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean Login_customer(String email, String pass) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "SELECT COUNT(*) FROM customers WHERE email = ? AND password = ?")) {
            psmt.setString(1, email);
            psmt.setString(2, pass);
            try (ResultSet res = psmt.executeQuery()) {
                if (res.next() && res.getInt(1) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean signupCustomer(Customer c) throws SQLException {
        if (isEmailRegistered(c.getEmail())) {
            return false;
        } else {
            return Create_customer(c);
        }
    }

    private boolean isEmailRegistered(String email) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "SELECT COUNT(*) FROM customers WHERE email = ?")) {
            psmt.setString(1, email);
            try (ResultSet res = psmt.executeQuery()) {
                if (res.next() && res.getInt(1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public Customer getData(String email) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "SELECT * FROM customers WHERE email = ?")) {
            psmt.setString(1, email);
            try (ResultSet res = psmt.executeQuery()) {
                if (res.next()) {
                    Customer c = new Customer();
                    c.setEmail(res.getString("email"));
                    c.setFullName(res.getString("fullName"));
                    c.setMobileNo(res.getString("mobileNo"));
                    return c;
                }
            }
        }
        return null;
    }
}
