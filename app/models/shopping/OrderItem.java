package models.shopping;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.*;

import models.*;

// OrderItem entity managed by Ebean
@Entity
public class OrderItem extends Model {

    @Id
    private Long id;

    @ManyToOne
    private ShopOrder order;

    @ManyToOne
    private Basket basket;

    @ManyToOne
    private Booking ticket;
    private double price;
    private int noOfTickets;
    // Default constructor
    public  OrderItem() {
    }

    public OrderItem(Booking t) {
        ticket = t;
        price = t.getPrice();
        this.noOfTickets= t.getNoOfTickets();
    }

    // Increment quantity
    public void increaseQty() {
        setNoOfTickets(ticket.getNoOfTickets() + 1);
    }

    // Decrement quantity
    public void decreaseQty() {
        setNoOfTickets(ticket.getNoOfTickets() - 1);
    }

    // Calculate and return total price for this order item
    public double getItemTotal() {
        return this.price * noOfTickets;
    }

    //Generic query helper
    public static Finder<Long,OrderItem> find = new Finder<Long,OrderItem>(OrderItem.class);

    //Find all Products in the database
    public static List<OrderItem> findAll() {
        return OrderItem.find.all();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopOrder getOrder() {
        return order;
    }

    public void setOrder(ShopOrder order) {
        this.order = order;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Booking getTicket() {
        return ticket;
    }

    public void setTicket(Booking ticket) {
        this.ticket = ticket;
    }

    public int getQuantity() {
        return noOfTickets;
    }

    public void setQuantity(int quantity) {
        this.noOfTickets = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }
}

