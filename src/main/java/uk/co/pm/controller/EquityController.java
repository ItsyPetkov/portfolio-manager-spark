package uk.co.pm.controller;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import uk.co.pm.externalapi.PersonExternalApiService;
import uk.co.pm.model.Equity;
import uk.co.pm.model.HelloReference;
import uk.co.pm.model.Person;
import uk.co.pm.service.EqService;
import uk.co.pm.service.HelloMessageService;
import uk.co.pm.externalapi.*;
import uk.co.pm.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static uk.co.pm.utils.Renderer.render;
import static uk.co.pm.utils.ResponseTypeUtil.shouldReturnHtml;

public class EquityController {

    private Gson gson;
    private final EquityExternalApiService EquityExternalApiService;

    public EquityController(String remoteApiBaseUrl) {
        gson = new Gson();
        EquityExternalApiService = new EquityExternalApiService(remoteApiBaseUrl);
        setupRoutes();
    }

    private void setupRoutes() {
        //This is how you set up a REST GET method, using Spark (http://sparkjava.com)
        get("/hello", (Request request, Response response) -> 
        
        {
            //call the external api
            List<Equity> Equities = EquityExternalApiService.getEquities();
            
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
                    Eqm.add(EqService.getStuff(eq));
                }
              
                //Use Gson (Google's json parser) to turn the Java object into json
              
                
                return gson.toJson(Eqm);
            }
        });
    }
}
