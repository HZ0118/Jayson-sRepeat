package controllers;

import models.shopping.*;
import models.users.Customer;
import models.users.User;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.*;
import models.*;

import javax.inject.Inject;


@Security.Authenticated(Secured.class)
@With(AuthCustomer.class)
public class ShoppingController extends Controller {

    private FormFactory formFactory;

    @Inject
    public ShoppingController(FormFactory f){
        this.formFactory = f;
    }

    // Get a user - if logged in email will be set in the session
    private Customer getCurrentUser() {
        return (Customer)User.getUserById(session().get("email"));
    }

    @Transactional
    public Result placeOrder(){
        Customer c = getCurrentUser();

        ShopOrder order = new ShopOrder();

        order.setCustomer(c);
        order.setItems(c.getBasket().getBasketItems());
        order.save();

        for(OrderItem i: order.getItems()){
            i.setOrder(order);
            i.setBasket(null);
            i.update();

        }

        order.update();
        c.getBasket().setBasketItems(null);
        c.getBasket().update();

        return ok(orderConfirmed.render(c,order));
    }

    @Transactional
    public Result showBasket(){
        Customer customer = (Customer)User.getUserById(session().get("email"));
        if(customer.getBasket() == null){
            customer.setBasket(new Basket());
            customer.getBasket().setCustomer(customer);
            customer.update();
        }
        return ok(basket.render(getCurrentUser()));
    }

    @Transactional
    public Result addToBasket(Long id) {
        Booking b = Booking.find.byId(id);
        Customer customer = (Customer)User.getUserById(session().get("email"));
        if (customer.getBasket() == null) {
            customer.setBasket(new Basket());
            customer.getBasket().setCustomer(customer);
            customer.update();
        }
        customer.getBasket().addBooking(b);
        customer.update();
        return ok(basket.render(customer));
    }

    // Add an item to the basket
    @Transactional
    public Result addOne(Long itemId) {

        // Get the order item
        OrderItem item = OrderItem.find.byId(itemId);
        // Increment quantity
        item.increaseQty();
        // Save
        item.update();
        // Show updated basket
        return redirect(routes.ShoppingController.showBasket());
    }

    @Transactional
    public Result removeOne(Long itemId) {

        // Get the order item
        OrderItem item = OrderItem.find.byId(itemId);
        // Get user
        Customer c = getCurrentUser();
        // Call basket remove item method
        c.getBasket().removeItem(item);
        c.getBasket().update();
        // back to basket
        return ok(basket.render(c));
    }


    // Empty Basket
    @Transactional
    public Result emptyBasket() {

        Customer c = getCurrentUser();
        c.getBasket().removeAllItems();
        c.getBasket().update();

        return ok(basket.render(c));
    }

    @Transactional
    public Result cancelBooking(Long id){
        OrderItem item = OrderItem.find.byId(id);
        Customer c = getCurrentUser();
        c.getBasket().cancelOrder(item);
        c.getBasket().update();
        return ok(basket.render(c));
    }

    public Result addBooking(Long id){
        FlightSchedule f = FlightSchedule.find.byId(id);
        Form<Booking> addBookingForm = formFactory.form(Booking.class);
        return ok(booking.render(addBookingForm, User.getUserById(session().get("enail")), f));
    }

    public Result addBookingForm(Long id){
        FlightSchedule f = FlightSchedule.find.byId(id);
        Customer c = (Customer)User.getUserById(session().get("email"));
        Form<Booking> newBookingForm = formFactory.form(Booking.class).bindFromRequest();
        if(newBookingForm.hasErrors()) {
            //Display the form again
            return badRequest(booking.render(newBookingForm, User.getUserById(session().get("email")), f));
        }
        Booking newBooking = new Booking(c, f, newBookingForm.get().getNoOfTickets(), newBookingForm.get().getFirstname(), newBookingForm.get().getLastname(), newBookingForm.get().getTicketClass());
        newBooking.save();
        addToBasket(newBooking.getId());
        flash("success");
        return redirect(controllers.routes.ShoppingController.showBasket());
    }

    public Result pay(Long id){
        Booking b = Booking.find.byId(id);
        Form<Payment> paymentForm = formFactory.form(Payment.class);
        return ok(payment.render(paymentForm, User.getUserById(session().get("email")), b));
    }

    public Result addPaymentForm(Long id){
        Booking b = Booking.find.byId(id);
        Form<Payment> newPaymentForm = formFactory.form(Payment.class).bindFromRequest();
        if(newPaymentForm.hasErrors()){
            return badRequest(payment.render(newPaymentForm, User.getUserById(session().get("email")), b));
        }
        Payment newPayment = new Payment(b, newPaymentForm.get().getCardHolderName(), newPaymentForm.get().getCardNumber(), newPaymentForm.get().getCardNumber());
        newPayment.save();
        return redirect(controllers.routes.ShoppingController.placeOrder());
    }

}