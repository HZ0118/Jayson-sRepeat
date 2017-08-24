package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Payment extends Model {
    @Id
    private Long id;
    @OneToOne
    private Booking ticket;

    private String cardHolderName;
    private String cardNumber;
    private String securityNumber;
    private boolean status = false;

    public Payment(){
    }

    public Payment(Booking b, String name, String cardNo, String securityNo){
        ticket = b;
        setCardHolderName(name);
        setCardNumber(cardNo);
        setSecurityNumber(securityNo);
        setStatus(true);
    }

    public static Finder<Long,Payment> find = new Finder<Long,Payment>(Payment.class);

    public static List<Payment> findAll() {
        return Payment.find.all();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
