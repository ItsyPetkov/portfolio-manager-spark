package externalapi;

import com.google.gson.Gson;
import model.Person;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PersonExternalApiService {

    private OkHttpClient client;
    private Gson gson;

    public PersonExternalApiService() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public Person getPerson() throws IOException {
        String url = "https://portfolio-manager-api.herokuapp.com/person";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return gson.fromJson(response.body().string(), Person.class);
    }
}
