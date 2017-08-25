package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Constraint;

import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class FlightSchedule extends Model{
    @Id
    private Long flight_id;
    @Constraints.Required
    private String destination;
    @Constraints.Required
    private String dest_city;
    @Constraints.Required
    private String start;
    @Constraints.Required
    private String departure_date;
    @Constraints.Required
    private int seats;
    @Constraints.Required
    private double price;

    public FlightSchedule(){
    }

    public FlightSchedule(Long id, String dest, String city, String startPoint, String date, int seats, double cost){
        setFlight_id(id);
        setDestination(dest);
        setDest_city(city);
        setStart(startPoint);
        setDeparture_date(date);
        this.setSeats(seats);
        setPrice(cost);
    }

    public static Finder<Long, FlightSchedule> find = new Finder<Long, FlightSchedule>(FlightSchedule.class);

    public static List<FlightSchedule> findAll(){
        return FlightSchedule.find.all();
    }

    public Long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDest_city() {
        return dest_city;
    }

    public void setDest_city(String dest_city) {
        this.dest_city = dest_city;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int updateSeats(Long id, int seats){
        FlightSchedule f = FlightSchedule.find.byId(id);
        f.seats = seats;
        return seats;
    }

}
