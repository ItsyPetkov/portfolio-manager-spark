package uk.co.pm.externalapi;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import uk.co.pm.model.Equity;

public class EquityExternalApiService {
	
	 //This is used to communicate with the extenal API (http://square.github.io/okhttp/)
    private OkHttpClient client;
    //This is used for converting back & forth between JSON and java objects (https://github.com/google/gson)
    private Gson gson;
    private String baseUrl;

    //This is used to turn a json representation of a list of Equity into a List<Equity>
    //If this was to be a list of another type, it would just be changed to TypeToken<List<MyType>>
    private TypeToken<List<Equity>> equitiesListTypeToken = new TypeToken<List<Equity>>() {
    };

    public EquityExternalApiService(String baseUrl) {
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.baseUrl = baseUrl;
    }

    public List<Equity> getEquities() throws IOException {
        String url = baseUrl + "/equities";

        //Create a okHttp "request"
        Request request = new Request.Builder().url(url).build();

        //Use the okHttp client to make a call, using our request object, returning a response
        Response response = client.newCall(request).execute();

        //Extract the response body as a string
        String responseString = response.body().string();
        //use Gson to turn your json string into a list of Person objects
        List<Equity> equities = gson.fromJson(responseString, equitiesListTypeToken.getType());
        return equities;
    }
	
}
