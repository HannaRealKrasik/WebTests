import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlowersTest {

    private final String URL = "https://www.flowerchimp.co.id/";

    public static WebDriver driver;

    @BeforeMethod
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterMethod
     public void after(){
        driver.quit();
    }

    @Test
    public void testSingIn(){
        driver.get(URL);
        WebElement SingIn = driver.findElement(By.xpath("//span[text()='Login']"));
        SingIn.click();
        WebElement Email = driver.findElement(By.xpath("//div[@id='login_form']/form/input[@type='email']"));
        Email.sendKeys("Heli@dmail.com");
        WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
        Password.sendKeys("12634");
        WebElement LOGIN = driver.findElement(By.xpath("//form/input[@class='btn action_button']"));
        LOGIN.click();
        WebElement actual = driver.findElement(By.xpath("//p[@class='shopify-challenge__message']"));

        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);

        Assert.assertEquals(actual.getText(), "To continue, let us know you're not a robot.");
    }

    @Test
    public void testCountTextBox(){
        driver.get(URL);
        final String text = "box";

        WebElement Cakes = driver.findElement(By.xpath("//a[@href='/collections/trinity-collection-flower-shop-flower-chimp'][@class='hidden-product-link']"));
        Cakes.click();
        List<WebElement> itemList = driver.findElements(By.xpath("//span[text()='Trinity Box Deluxe Collection - Dainty Dreams']"));

        for (int i=0; i< itemList.size(); i++){
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(text));
        }
    }
}
