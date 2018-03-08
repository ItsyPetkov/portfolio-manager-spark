package uk.co.pm.controller;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import uk.co.pm.externalapi.EquityExternalApiService;
import uk.co.pm.externalapi.PersonExternalApiService;
import uk.co.pm.externalapi.PriceExternalApiService;
import uk.co.pm.model.*;
import uk.co.pm.service.EqService;
import uk.co.pm.service.HelloMessageService;
import uk.co.pm.service.PriceMessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static uk.co.pm.utils.Renderer.render;
import static uk.co.pm.utils.ResponseTypeUtil.shouldReturnHtml;

public class PriceController {




    private Gson gson;
    private final EquityExternalApiService equityExternalApiService;
    private final PriceExternalApiService priceExternalApiService;
    public PriceController(String remoteApiBaseUrl) {
        gson = new Gson();
        equityExternalApiService = new EquityExternalApiService(remoteApiBaseUrl);
        priceExternalApiService = new PriceExternalApiService(remoteApiBaseUrl);
        setupRoutes();
    }

    private void setupRoutes(){
        //This is how you set up a REST GET method, using Spark (http://sparkjava.com)
        get("/equities/prices", (Request request, Response response) ->

        {
            //call the external api
            List<Price> prices = priceExternalApiService.getPrices();
            List<Equity> equities = equityExternalApiService.getEquities();

            //Here, we check whether the request is for html, or whether we should return JSON
            if (shouldReturnHtml(request)) {
                Map<String, Object> model = new HashMap<>();

                model.put("prices", prices);
                model.put("equities", equities);

                return render(model, "templates/prices2.vm");
            } else {
                //Change the Person objects we got from the external API into the format we want to return to our users
                List<PriceReference> priceReferences = new ArrayList<>();
                for (Price p : prices) {
                    priceReferences.add(PriceMessageService.getPriceMessage(p));
                }

                //Use Gson (Google's json parser) to turn the Java object into json
                return gson.toJson(priceReferences);
            }
        });

        //This is how you set up a REST GET method, using Spark (http://sparkjava.com)
        get("/equities/prices/:sector/", (Request request, Response response) ->

        {
            //call the external api
            List<Price> prices = priceExternalApiService.getPrices();
            List<Equity> equities = equityExternalApiService.getEquities();

            List<Equity> temp  = new ArrayList<Equity>();
            //Here, we check whether the request is for html, or whether we should return JSON
            if (shouldReturnHtml(request)) {

                Map<String, Object> model = new HashMap<>();
                for (Equity e :equities) {

                    if(request.params(":sector").trim().replaceAll("_", " ").equals(e.getSector())) {

                        temp.add(e);
                    }
                }
                model.put("prices", prices);
                model.put("equities", temp);
                return render(model, "templates/prices2.vm");
            } else {
                //Change the Person objects we got from the external API into the format we want to return to our users
                List<PriceReference> priceReferences = new ArrayList<>();
                for (Price p : prices) {
                    priceReferences.add(PriceMessageService.getPriceMessage(p));
                }

                //Use Gson (Google's json parser) to turn the Java object into json
                return gson.toJson(priceReferences);
            }
        });

        get("/equities/prices/:sector/:Q", (Request request, Response response) ->

        {
            String quarter = request.params(":Q");
            //call the external api
            List<Price> prices = priceExternalApiService.getQPrices(quarter);
            List<Equity> equities = equityExternalApiService.getEquities();

            List<Equity> temp  = new ArrayList<Equity>();
            //Here, we check whether the request is for html, or whether we should return JSON
            if (shouldReturnHtml(request)) {

                Map<String, Object> model = new HashMap<>();
                for (Equity e :equities) {

                    if(request.params(":sector").trim().replaceAll("_", " ").equals(e.getSector())) {

                        temp.add(e);
                    }
                }

                model.put("prices", prices);
                model.put("equities", temp);
                return render(model, "templates/prices2.vm");
            } else {
                //Change the Person objects we got from the external API into the format we want to return to our users
                List<PriceReference> priceReferences = new ArrayList<>();
                for (Price p : prices) {
                    priceReferences.add(PriceMessageService.getPriceMessage(p));
                }

                //Use Gson (Google's json parser) to turn the Java object into json
                return gson.toJson(priceReferences);
            }
        });

        get("/equities/prices/:Q", (Request request, Response response) ->

        {
            String quarter = request.params(":Q");
            //call the external api
            List<Price> prices = priceExternalApiService.getQPrices(quarter);

            //Here, we check whether the request is for html, or whether we should return JSON
            if (shouldReturnHtml(request)) {

                Map<String, Object> model = new HashMap<>();

                model.put("prices", prices);
                return render(model, "templates/prices2.vm");
            } else {
                //Change the Person objects we got from the external API into the format we want to return to our users
                List<PriceReference> priceReferences = new ArrayList<>();
                for (Price p : prices) {
                    priceReferences.add(PriceMessageService.getPriceMessage(p));
                }
                //Use Gson (Google's json parser) to turn the Java object into json
                return gson.toJson(priceReferences);
            }
        });

    }


}
