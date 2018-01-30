package uk.co.pm.externalapi;

import com.google.gson.Gson;
import uk.co.pm.model.Person;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PersonExternalApiService {

    private OkHttpClient client;
    private Gson gson;
    private String baseUrl;

    public PersonExternalApiService(String baseUrl) {
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.baseUrl = baseUrl;
    }

    public Person getPerson() throws IOException {
        String url = baseUrl + "/person";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return gson.fromJson(response.body().string(), Person.class);
    }
}
