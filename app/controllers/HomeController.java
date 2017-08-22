package controllers;

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

public class HomeController extends Controller {

    private FormFactory formFactory;

    @Inject
    public HomeController(FormFactory f){
        this.formFactory = f;
    }

    private User getUserFromSession(){
        return User.getUserById(session().get("email"));
    }

    public Result index() {
        return ok(index.render(getUserFromSession()));
    }

    public Result flights(){
        List<FlightSchedule> flightsList = FlightSchedule.findAll();
        return ok(userViewFlights.render(flightsList, User.getUserById(session().get("email"))));
    }

}