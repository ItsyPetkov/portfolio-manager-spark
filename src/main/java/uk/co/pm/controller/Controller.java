package uk.co.pm.controller;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import uk.co.pm.externalapi.PersonExternalApiService;
import uk.co.pm.externalapi.EquityExternalApiService;
import uk.co.pm.model.Equity;
import uk.co.pm.model.EquityModel;
import uk.co.pm.model.HelloReference;
import uk.co.pm.model.Person;
import uk.co.pm.service.EqService;
import uk.co.pm.service.HelloMessageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static uk.co.pm.utils.Renderer.render;
import static uk.co.pm.utils.ResponseTypeUtil.shouldReturnHtml;

public class Controller {

    private Gson gson;
    private final PersonExternalApiService personExternalApiService;
    private final EquityExternalApiService equityExternalApiService;
    public Controller(String remoteApiBaseUrl) {
        gson = new Gson();
        personExternalApiService = new PersonExternalApiService(remoteApiBaseUrl);
        equityExternalApiService = new EquityExternalApiService(remoteApiBaseUrl);
        setupRoutes();
    }

    private void setupRoutes() {
        //This is how you set up a REST GET method, using Spark (http://sparkjava.com)
        get("/hello", (Request request, Response response) -> 
        
        {
            //call the external api
            List<Person> people = personExternalApiService.getPeople();

            //Here, we check whether the request is for html, or whether we should return JSON
            if (shouldReturnHtml(request)) {
                //Create a map to represent to "model"
                Map<String, Object> model = new HashMap<>();
                model.put("people", people);

                //The path to the tells the program to go look in the resources/templates folder for a file called hello.vm
                //The template is written in a templating language called Velocity (http://velocity.apache.org/)
                return render(model, "templates/hello.vm");
            } else {
            	
                //Change the Person objects we got from the external API into the format we want to return to our users
                List<HelloReference> helloReferences = new ArrayList<>();

                for (Person person : people) {
                    helloReferences.add(HelloMessageService.getHelloMessage(person));
                }

                //Use Gson (Google's json parser) to turn the Java object into json
                return gson.toJson(helloReferences);
            }
        });

        get("/equities",(Request request, Response response) ->

        {
            //call the external api
            List<Equity> Equities = equityExternalApiService.getEquities();

            //Here, we check whether the request is for html, or whether we should return JSON
            if (shouldReturnHtml(request)) {
                //Create a map to represent to "model"
                Map<String, Object> model = new HashMap<>();
                model.put("equities", Equities);

                //The path to the tells the program to go look in the resources/templates folder for a file called hello.vm
                //The template is written in a templating language called Velocity (http://velocity.apache.org/)
                return render(model, "templates/equity.vm");
            } else {

                //Change the Person objects we got from the external API into the format we want to return to our users
                List<EquityModel> Eqm = new ArrayList<>();

                for (Equity eq : Equities) {
                    Eqm.add(EqService.getEq(eq));
                }

                //Use Gson (Google's json parser) to turn the Java object into json


                return gson.toJson(Eqm);
            }
        });

        get("/equities/:EPIC",(Request request, Response response) ->
        {
            //call the external api
            List<Equity> Equities = equityExternalApiService.getEquities();
            List<Equity> temp = new ArrayList<Equity>();
            //Here, we check whether the request is for html, or whether we should return JSON
            if (shouldReturnHtml(request)) {
                //Create a map to represent to "model"
                Map<String, Object> model = new HashMap<>();
                for (Equity eq : Equities) {
                    if(request.params(":epic").equals(eq.getEPIC())) {
                       
                        
                        temp.add(eq);
                      model.put("equities", temp);
                    

                      break;
                    }

                }
                //The path to the tells the program to go look in the resources/templates folder for a file called hello.vm
                //The template is written in a templating language called Velocity (http://velocity.apache.org/)
                return render(model, "templates/individual.vm");
            } else {
                //Change the Equity objects we got from the external API into the format we want to return to our users
                List<EquityModel> Eqm = new ArrayList<>();
                for (Equity eq : Equities) {
                	if(request.params(":epic").equals(eq.getEPIC())) {
                    Eqm.add(EqService.getEq(eq));
                    break;
                	}
                }
                //Use Gson (Google's json parser) to turn the Java object into json
                if(Eqm.isEmpty()){
                	throw new IOException();
                }
                return gson.toJson(Eqm);
            }
        });
    }
}
