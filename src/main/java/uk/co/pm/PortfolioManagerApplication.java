package uk.co.pm;

import uk.co.pm.controller.HelloController;

import static spark.Spark.port;

public class PortfolioManagerApplication {

    public static void main(String[] args) {
        port(getAssignedPort());
        String baseUrl = "https://portfolio-manager-api.herokuapp.com";
        new HelloController(baseUrl);
    }

    private static int getAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
