package uk.co.pm.externalapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.pm.model.Price;

import java.io.IOException;
import java.util.List;

public class PriceExternalApiService {
    //This is used to communicate with the extenal API (http://square.github.io/okhttp/)
    private OkHttpClient client;
    //This is used for converting back & forth between JSON and java objects (https://github.com/google/gson)
    private Gson gson;
    private String baseUrl;

    //This is used to turn a json representation of a list of Equity into a List<Equity>
    //If this was to be a list of another type, it would just be changed to TypeToken<List<MyType>>
    private TypeToken<List<Price>> pricesListTypeToken = new TypeToken<List<Price>>() {
    };

    public PriceExternalApiService(String baseUrl) {
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.baseUrl = baseUrl;
    }

    public List<Price> getPrices() throws IOException {
        String url = baseUrl + "/prices";

        //Create a okHttp "request"
        Request request = new Request.Builder().url(url).build();

        //Use the okHttp client to make a call, using our request object, returning a response
        Response response = client.newCall(request).execute();

        //Extract the response body as a string
        String responseString = response.body().string();

        //use Gson to turn your json string into a list of Equity objects
        List<Price> prices = gson.fromJson(responseString, pricesListTypeToken.getType());
    return prices;
    }

    public List<Price> getQPrices(String param) throws IOException {
        String url = "/prices";
        if(param.toUpperCase().equals("Q1")){
          url = baseUrl + "/prices/Q1";
        }
        else if(param.toUpperCase().equals("Q2")) {
            url = baseUrl + "/prices/Q2";
        }

        //Create a okHttp "request"
        Request request = new Request.Builder().url(url).build();

        //Use the okHttp client to make a call, using our request object, returning a response
        Response response = client.newCall(request).execute();

        //Extract the response body as a string
        String responseString = response.body().string();

        //use Gson to turn your json string into a list of Equity objects
        List<Price> prices = gson.fromJson(responseString, pricesListTypeToken.getType());



        return prices;
    }
}

