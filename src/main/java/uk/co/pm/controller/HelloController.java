package uk.co.pm.controller;

import com.google.gson.Gson;
import uk.co.pm.externalapi.PersonExternalApiService;
import uk.co.pm.model.Person;
import uk.co.pm.service.HelloMessageService;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static uk.co.pm.utils.Renderer.render;
import static uk.co.pm.utils.ResponseTypeUtil.shouldReturnHtml;

public class HelloController {

    private Gson gson;
    private final PersonExternalApiService personExternalApiService;

    public HelloController(String baseUrl) {
        gson = new Gson();
        personExternalApiService = new PersonExternalApiService(baseUrl);
        setupRoutes();
    }

    private void setupRoutes() {
        get("/hello", (req, res) -> {
            Person person = personExternalApiService.getPerson();

            if (shouldReturnHtml(req)) {
                Map<String, Object> model = new HashMap<>();
                model.put("person", person);
                return render(model, "templates/hello.vtl");
            } else {
                return gson.toJson(HelloMessageService.getHelloMessage(person));
            }
        });
    }
}
