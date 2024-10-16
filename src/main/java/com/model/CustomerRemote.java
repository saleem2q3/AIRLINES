package com.model;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.entity.Customer;

@Remote
public interface CustomerRemote {
	public boolean Create_customer(Customer c)throws Exception;
	public boolean Login_customer(String email,String pass)throws Exception;
	public boolean signupCustomer(Customer c)throws Exception;
	public Customer getData(String email) throws SQLException;
}
