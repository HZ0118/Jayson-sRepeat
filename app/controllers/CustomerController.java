package controllers;

import controllers.*;
import models.users.Customer;
import models.users.User;
import play.mvc.*;
import play.api.Environment;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;
import models.*;

public class CustomerController extends Controller {

    private FormFactory formFactory;

    @Inject
    public CustomerController(FormFactory f){
        this.formFactory = f;
    }

    private User getUserFromSession(){
        return User.getUserById(session().get("email"));
    }

    public Result flights(){
        List<FlightSchedule> flightsList = FlightSchedule.findAll();
        return ok(customerViewFlights.render(flightsList, User.getUserById(session().get("email"))));
    }



}