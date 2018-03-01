package uk.co.pm;

import uk.co.pm.controller.Controller;
import uk.co.pm.controller.PriceController;
import uk.co.pm.gui.MyGUI;
import uk.co.pm.utils.CSVUtilFile;

import static spark.Spark.port;

public class PortfolioManagerApplication {

    public static void main(String[] args) {
        port(getAssignedPort());
        String remoteApiBaseUrl = "https://portfolio-manager-api.herokuapp.com";
        new PriceController(remoteApiBaseUrl);
        new Controller((remoteApiBaseUrl));
        new CSVUtilFile(remoteApiBaseUrl);
        new MyGUI();
    }

    //This will be used when we push to a cloud server
    private static int getAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // this port is what is used for running on localhost
    }
}
