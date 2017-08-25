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
        Form<User> addUserForm = formFactory.form(User.class);
        return ok(index.render(addUserForm,getUserFromSession()));
    }

    public Result flights(){
        List<FlightSchedule> flightsList = FlightSchedule.findAll();
        return ok(userViewFlights.render(flightsList, User.getUserById(session().get("email"))));
    }

    public Result signUpSubmit(){
        Form<User> newRegisterForm = formFactory.form(User.class).bindFromRequest();
        if(newRegisterForm.hasErrors()){
            return badRequest(index.render(newRegisterForm, User.getUserById(session().get("email"))));
        }
        User newUser = newRegisterForm.get();
        newUser.save();
        flash("success", "User " + newUser.getName() + " has been created");
        return redirect(controllers.routes.LoginController.login());
    }

}
