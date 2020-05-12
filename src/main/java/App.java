import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public  class  App {
    public static void main(String[] args) {

        staticFileLocation("/public");


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/ranger/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ranger", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            String badgeNumber = req.queryParams("badgeNumber");


            if (!(firstName.trim().isEmpty() || lastName.trim().isEmpty() || badgeNumber.trim().isEmpty())) {

                Ranger ranger = new Ranger(firstName, lastName, Integer.parseInt(badgeNumber));
                ranger.save();
            } else {
                System.out.println("Please fill in all the fields");
            }

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Ranger> ranger = Ranger.all(); //refresh list of links for navbar
            model.put("ranger", ranger);
            return new ModelAndView(model, "index.hbs");

        }, new HandlebarsTemplateEngine());

        get("/ranger/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = Ranger.all();

            Ranger ranger = Ranger.find(Integer.parseInt(req.params(":id")));
            model.put("ranger", ranger);
            // model.put("sightings",ranger.AllMySightings());
            return new ModelAndView(model, "ranger.hbs");

        }, new HandlebarsTemplateEngine());


        get("/ranger/:id/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(req.params(":id")));
            model.put("ranger", ranger);


            model.put("animals", Animal.all());
            model.put("locations", Location.all());


            model.put("sightings", Sighting.find(Integer.parseInt(req.params(":id"))));
            return new ModelAndView(model, "sighting-record.hbs");

        }, new HandlebarsTemplateEngine());


        post("/sightings", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String name = req.queryParams("name");
            int animal = Integer.parseInt(req.queryParams("animal"));
            String location = req.queryParams("location");

            Sighting sighting = new Sighting(name, location, animal);
            sighting.save();


            return new ModelAndView(model, "sightings.hbs");

        }, new HandlebarsTemplateEngine());


        get("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();


            model.put("animals", Animal.all());
            model.put("endangeredAnimals", EndangeredAnimal.allEndangeredAnimals());
            return new ModelAndView(model, "animals.hbs");

        }, new HandlebarsTemplateEngine());


        post("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("inputName");
            String health = req.queryParams("healthInput");
            String age = req.queryParams("ageInput");
            if (req.queryParams("endangeredInput") != null) {
                if (!(name.trim().isEmpty() || health.trim().isEmpty() || age.trim().isEmpty())) {

                    EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
                    endangeredAnimal.save();
                } else {
                    System.out.println("Please fill in all the fields");
                }
            } else {
                if (!(name.trim().isEmpty())) {
                    Animal animal = new Animal(name);
                    animal.save();
                } else {
                    System.out.println("Please fill in all the fields");
                }


            }
            return new ModelAndView(model, "animals.hbs");


        }, new HandlebarsTemplateEngine());


        get("/sightings", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sightings.hbs");

        }, new HandlebarsTemplateEngine());


        get("/locations", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("locations", Location.all());
            return new ModelAndView(model, "locations.hbs");

        }, new HandlebarsTemplateEngine());


        post("/locations", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String locationName = req.queryParams("name");

            if (!(locationName.trim().isEmpty())) {

                Location location = new Location(locationName);
                location.save();
            }
            return new ModelAndView(model, "locations.hbs");
        }, new HandlebarsTemplateEngine());


    }
}

