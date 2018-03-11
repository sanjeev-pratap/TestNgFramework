package base;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.logging.Logger;

public class PageObject {
    WebDriver driver;

    public void launchUrl(String url){
        try{
            System.out.println("Launching url");
            driver.get(url);
            System.out.println("url launched successfully");
        }
        catch(Exception e){
            Assert.fail("Exception in launchUrl "+e.toString()+e.getStackTrace());
        }

    }
}
