import com.google.gson.Gson;
import externalapi.PersonExternalApiService;
import model.Person;
import service.HelloMessageService;

import static spark.Spark.get;

public class HelloWorld {

    public static void main(String[] args) {
        Gson gson = new Gson();
        PersonExternalApiService personExternalApiService = new PersonExternalApiService();

        get("/hello", (req, res) -> {
            Person person = personExternalApiService.getPerson();
            return HelloMessageService.getHelloMessage(person);
        }, gson::toJson);
    }
}
