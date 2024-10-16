package com.controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.entity.Customer;
import com.model.CustomerRemote;

@ManagedBean(name = "customer", eager = true)
@SessionScoped
public class CustomerData {
    private String fullName;
    private String email;
    private String password;
    private String mobileNo;
    private boolean response;
    private Customer cur; 

    @EJB(lookup = "java:global/AIRLINKS/CustomerManager!com.model.CustomerRemote")
    private CustomerRemote cr;

    public void login() {
        try {
            if (cr.Login_customer(email, password)) {
                cur = cr.getData(email);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().getSessionMap().put("email", email);
                fc.getExternalContext().getSessionMap().put("cur", cur);
                FacesContext.getCurrentInstance().getExternalContext().redirect("homepage.jsf?email=" + email);
            } else {
                System.out.println("Login failed for email: " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register() {
        Customer c = new Customer();
        c.setFullName(fullName);
        c.setEmail(email);
        c.setMobileNo(mobileNo);
        c.setPassword(password);
        try {
            response = cr.signupCustomer(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public CustomerRemote getCr() {
        return cr;
    }

    public void setCr(CustomerRemote cr) {
        this.cr = cr;
    }

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Customer getCur() {
		return (Customer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cur");
	}

	public void setCur(Customer cur) {
		this.cur = cur;
	}
}
