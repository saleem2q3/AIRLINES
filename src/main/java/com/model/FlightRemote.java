package com.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Remote;
import com.entity.Flight;


@Remote
public interface FlightRemote {
	public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureTime);
    public String postFlight(Flight f);
    public List<Flight> getFlightsOnAdmin();
}
