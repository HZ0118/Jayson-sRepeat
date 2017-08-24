package models;

import com.avaje.ebean.*;
import models.users.Customer;

import javax.persistence.*;
import java.util.List;

@Entity
public class Booking extends Model{
    @Id
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private FlightSchedule flight;
    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Payment bookingStatus;

    private String firstname;
    private String lastname;
    private String ticketClass;
    private int noOfTickets;
    private double price;

    public Booking(){
    }

    public Booking(Customer c,FlightSchedule f,int noOfTickets, String fname, String lname, String tClass){
        setFlight(f);
        setCustomer(c);
        this.setNoOfTickets(noOfTickets);
        setFirstname(fname);
        setLastname(lname);
        setTicketClass(tClass);
        this.price = f.getPrice();
    }

    public static Finder<Long,Booking> find = new Finder<Long,Booking>(Booking.class);

    public static List<Booking> findAll() {
        return Booking.find.all();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public FlightSchedule getFlight() {
        return flight;
    }

    public void setFlight(FlightSchedule flight) {
        this.flight = flight;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = flight.getPrice();
    }

    public Payment getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(Payment bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
