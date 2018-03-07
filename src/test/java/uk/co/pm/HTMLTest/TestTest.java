package uk.co.pm.HTMLTest;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import uk.co.pm.controller.Controller;
import uk.co.pm.controller.PriceController;
import uk.co.pm.externalapi.EquityExternalApiService;
import uk.co.pm.externalapi.PriceExternalApiService;
import uk.co.pm.model.Equity;
import uk.co.pm.model.Price;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static spark.Spark.port;

public class TestTest {
        @Test
        public  void runTest() throws IOException {
            port(getAssignedPort());


            String remoteApiBaseUrl = "https://portfolio-manager-api.herokuapp.com";
            EquityExternalApiService e = new EquityExternalApiService(remoteApiBaseUrl);
            new PriceController(remoteApiBaseUrl);
            new Controller((remoteApiBaseUrl));

            WebDriver driver = new ChromeDriver();
            driver.get("http://localhost:4567/equities");


            WebElement table = driver.findElement(By.id("table1"));
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            List<Equity> equities = e.getEquities();
            List<String> EPICS = new ArrayList<String>();

            for(WebElement row: allRows){
                String r = row.getText().trim();
                if(r.contains("EPIC is:")){
                    String EPIC  = r.substring(9);
                    EPICS.add(EPIC);
                }
            }
            int i = 0;
            for(Equity eq : equities){
                assertThat(eq.getEPIC()).isEqualTo(EPICS.get(i));
                i++;
            }
            //Find the body
            WebElement element = driver.findElement(By.id("Body"));
            WebElement csvElement = driver.findElement(By.id("CSV"));

            driver.navigate().to("http://localhost:4567/equities/prices");

            assertThat(runPrice(driver)).isTrue();
            driver.close();
            driver.quit();
        }


        public boolean runPrice(WebDriver d) throws IOException {


            PriceExternalApiService p = new PriceExternalApiService("https://portfolio-manager-api.herokuapp.com");
            WebElement table = d.findElement(By.id("table1"));
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            List<Price> equities = p.getPrices();
            List<String> EPICS = new ArrayList<String>();

            for(WebElement row: allRows){
                String r = row.getText().trim();
                if(r.contains("EPIC is:")){
                    String EPIC  = r.substring(9);
                    EPICS.add(EPIC);
                }
            }
            int i = 0;
            while(i<EPICS.size()){
                assertThat(equities.get(i).getEPIC()).isEqualTo(EPICS.get(i));
                System.out.println(equities.get(i).getEPIC() +":"+EPICS.get(i));
                i++;
            }

            return true;

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
