package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.main;

public class ReportingController extends Controller {

    public static Result index() {
        return ok(main.render());
    }
}
