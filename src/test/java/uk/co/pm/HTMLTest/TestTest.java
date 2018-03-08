package uk.co.pm.HTMLTest;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import uk.co.pm.controller.Controller;
import uk.co.pm.controller.PriceController;
import uk.co.pm.externalapi.EquityExternalApiService;
import uk.co.pm.externalapi.PriceExternalApiService;
import uk.co.pm.model.Equity;
import uk.co.pm.model.Price;
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
            System.setProperty("webdriver.chrome.driver", "src/driver");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/usr/lib/google-chrome");
            WebDriver driver = new SafariDriver();
            driver.get("http://localhost:4567/equities");
            driver.manage().window().maximize();

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
            WebElement pButton = driver.findElement(By.id("pButton"));

            driver.navigate().to("http://localhost:4567/equities/prices");
            assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:4567/equities/prices");
            assertThat(runPrice(driver)).isTrue();
            assertThat(runQuarter(driver,"Q1")).isTrue();



            driver.close();
            driver.quit();
        }

        public boolean runQuarter(WebDriver d, String quarter) throws IOException {
            PriceExternalApiService p = new PriceExternalApiService("https://portfolio-manager-api.herokuapp.com");
            WebElement table = d.findElement(By.id("table1"));
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            List<Price> prices = p.getQPrices(quarter);
            List<String> EPICS = new ArrayList<String>();

            for(WebElement row: allRows){
                String r = row.getText().trim();
                if(r.contains("EPIC is:")){
                    String EPIC  = r.substring(9);
                    EPICS.add(EPIC);
                }
            }
            int i = 0;
            while(i<prices.size()){
                assertThat(prices.get(i).getEPIC()).isEqualTo(EPICS.get(i));

                i++;
            }

            return true;

        }
    public boolean runPrice(WebDriver d) throws IOException {


        PriceExternalApiService p = new PriceExternalApiService("https://portfolio-manager-api.herokuapp.com");
        WebElement table = d.findElement(By.id("table1"));
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        List<Price> prices = p.getPrices();
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
            assertThat(prices.get(i).getEPIC()).isEqualTo(EPICS.get(i));
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
