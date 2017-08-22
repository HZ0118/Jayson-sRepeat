package models.shopping;

import com.avaje.ebean.Model;
import com.zaxxer.hikari.util.FastList;
import models.FlightSchedule;
import models.users.Customer;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Entity
public class Basket extends Model {

    @Id
    private Long id;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.PERSIST)
    private List<OrderItem> basketItems;

    @OneToOne
    private Customer customer;

    public  Basket() {
    }

    public void addFlight(FlightSchedule f) {
        boolean itemFound = false;
        for (OrderItem i : basketItems) {
            if (i.getFlight().getFlight_id() == f.getFlight_id()) {
                i.increaseQty();
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            OrderItem newItem = new OrderItem(f);
            basketItems.add(newItem);
        }
    }

    public void removeItem(OrderItem item) {

        // Using an iterator ensures 'safe' removal of list objects
        // Removal of list items is unreliable as index can change if an item is added or removed elsewhere
        // iterator works with an object reference which does not change
        for (Iterator<OrderItem> iter = basketItems.iterator(); iter.hasNext();) {
            OrderItem i = iter.next();
            if (i.getId().equals(item.getId()))
            {
                // If more than one of these items in the basket then decrement
                if (i.getQuantity() > 1 ) {
                    i.decreaseQty();
                }
                // If only one left, remove this item from the basket (via the iterator)
                else {
                    // delete object from db
                    i.delete();
                    // remove object from list
                    iter.remove();
                    break;
                }
            }
        }
    }

    public void cancelOrder(OrderItem item){
        item.delete();
    }

    public void removeAllItems() {
        for(OrderItem i: this.basketItems) {
            i.delete();
        }
        this.basketItems = null;
    }

    public double getBasketTotal() {

        double total = 0;

        for (OrderItem i: basketItems) {
            total += i.getItemTotal();
        }
        return total;
    }

    //Generic query helper
    public static Finder<Long,Basket> find = new Finder<Long,Basket>(Basket.class);

    //Find all Products in the database
    public static List<Basket> findAll() {
        return Basket.find.all();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<OrderItem> basketItems) {
        this.basketItems = basketItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

