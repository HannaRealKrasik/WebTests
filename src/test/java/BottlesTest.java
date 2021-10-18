import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BottlesTest {

    public static WebDriver driver;

    @BeforeMethod
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }
    @AfterMethod
    public void after(){
   // driver.close();
  //  driver.quit();

    }

    @Test
    public void testButtles(){
        String url = "http://www.99-bottles-of-beer.net";
        String espected = "http://www.99-bottles-of-beer.net/";
         driver.get( url);
         String actual = driver.getCurrentUrl();

        Assert.assertEquals(actual,espected);


        WebElement searchName = driver.findElement(By.partialLinkText("99 Bottles"));
        String extectedName = "Welcome to 99 Bottles of Beer";
        Assert.assertEquals(searchName.getText(), extectedName );



    }

}
