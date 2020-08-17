import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class LoginTestSelenium {
    public static void main(String[] args) throws MalformedURLException {

        System.setProperty("webdriver.gecko.driver","D:\\6. Semester\\SE2\\Hausarbeit\\geckodriver.exe");
        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);



        try {
            //FirefoxDriver driver=new FirefoxDriver();
            driver.get("http://localhost:8080/#!loginView");
            WebElement element_UserName = driver.findElement(By.id("gwt-uid-3"));

            //WebElement element = driver.findElement(By.xpath("//input[@name='email']"));
            element_UserName.sendKeys("hassam@carlook.de");

            WebElement elementTwo = driver.findElement(By.id("gwt-uid-5"));
            elementTwo.sendKeys("12345678");

            WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[5]/div/div[2]/div/div[5]/div"));
            button.click();
        } finally{

            //driver.close();
        }
    }
}
