package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import com.entity.Flight;


@Stateful
public class FlightManager implements FlightRemote {
    private String url = "jdbc:mysql://localhost:3306/airlines";
    private String username = "root";
    private String password = "Saleem@123";

    @Override
    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureTime) {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM flights WHERE departure_city = ? AND arrival_city = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, departureCity);
            stmt.setString(2, arrivalCity);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setFlightNumber(rs.getString("flight_number"));
                flight.setDepartureCity(rs.getString("departure_city"));
                flight.setArrivalCity(rs.getString("arrival_city"));
                flight.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
                flight.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
                flight.setAirline(rs.getString("airline"));
                flight.setPrice(rs.getBigDecimal("price"));
                flight.setEconomyseats(rs.getInt("econnomyseats"));
                flight.setBusinessseats(rs.getInt("businessseats"));
                flight.setFirstclassseats(rs.getInt("firstclassseats"));
                flights.add(flight);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }
    @Override
    public String postFlight(Flight flight) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
        	String query = "INSERT INTO flights (flight_number, departure_city, arrival_city, departure_time, arrival_time, airline, price, econnomyseats, businessseats, firstclassseats) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, flight.getFlightNumber());
            stmt.setString(2, flight.getDepartureCity());
            stmt.setString(3, flight.getArrivalCity());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(flight.getDepartureTime()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(flight.getArrivalTime()));
            stmt.setString(6, flight.getAirline());
            stmt.setBigDecimal(7, flight.getPrice());
            stmt.setInt(8, flight.getEconomyseats());
            stmt.setInt(9, flight.getBusinessseats());
            stmt.setInt(10, flight.getFirstclassseats());
            stmt.executeUpdate();
            return "sucess";
        } catch (Exception e) {
            e.printStackTrace();
            return "unsucess";
        }
    }
    @Override
    public List<Flight> getFlightsOnAdmin() {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM flights";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setFlightNumber(rs.getString("flight_number"));
                flight.setDepartureCity(rs.getString("departure_city"));
                flight.setArrivalCity(rs.getString("arrival_city"));
                flight.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
                flight.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
                flight.setAirline(rs.getString("airline"));
                flight.setPrice(rs.getBigDecimal("price"));
                flight.setEconomyseats(rs.getInt("econnomyseats"));
                flight.setBusinessseats(rs.getInt("businessseats"));
                flight.setFirstclassseats(rs.getInt("firstclassseats"));
                flights.add(flight);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }
    
    

}
