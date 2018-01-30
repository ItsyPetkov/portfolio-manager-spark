package uk.co.pm;

import uk.co.pm.controller.HelloController;

public class HelloWorld {

    public static void main(String[] args) {
        String baseUrl = "https://portfolio-manager-api.herokuapp.com";
        new HelloController(baseUrl);
    }
}
