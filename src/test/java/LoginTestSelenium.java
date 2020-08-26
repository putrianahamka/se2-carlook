import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class LoginTestSelenium {
    public static void main(String[] args) throws MalformedURLException {

        System.setProperty("webdriver.gecko.driver","src/test/java/geckodriver.exe");
        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);



        try {
            driver.get("http://localhost:8080/#!loginView");
            WebElement elementEmail = driver.findElement(By.id("gwt-uid-3"));


            elementEmail.sendKeys("hassam@carlook.de");

            WebElement elementPasswort = driver.findElement(By.id("gwt-uid-5"));
            elementPasswort.sendKeys("12345678");

            WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div[2]/div/div[5]/div"));
            button.click();
        } finally{

            //driver.close();
        }
    }
}
