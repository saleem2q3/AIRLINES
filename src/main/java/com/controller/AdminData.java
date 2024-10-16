package com.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.entity.Flight;
import com.model.FlightRemote;

@ManagedBean(name = "admindatabean")
@RequestScoped
public class AdminData implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AdminData.class.getName());

    private List<Flight> bookings;

    @EJB(lookup = "java:global/AIRLINKS/FlightManager!com.model.FlightRemote")
    private FlightRemote fr;

    public AdminData() {
        logger.log(Level.INFO, "Admindata bean instantiated");
    }

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "Admindata bean post-construct method called");
        fetchBookings();
    }

    public List<Flight> getBookings() {
        return bookings;
    }

    public void setBookings(List<Flight> bookings) {
        this.bookings = bookings;
    }

    private void fetchBookings() {
        try {
            bookings = fr.getFlightsOnAdmin();
            logger.log(Level.INFO, "Fetched bookings: {0}", bookings);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching bookings", e);
        }
    }
}
