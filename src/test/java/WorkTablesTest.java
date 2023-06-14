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

public class WorkTablesTest {

    public static WebDriver driver;

    @BeforeMethod
    public void Before(){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterMethod
    public void After(){
        driver.quit();
    }
    @Test
    public void TestTables() {
        final String TEXT = "table";

        driver.get("https://www.webstaurantstore.com/");

        WebElement search = driver.findElement(By.xpath("//input[@name='searchval']"));
        search.sendKeys("stainless work table");
        WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
         searchButton.click();
        driver.get("https://www.webstaurantstore.com/search/stainless-work-table.html");
       List<WebElement> item = driver.findElements(By.xpath("//a[@data-testid='itemDescription']"));
      for (int i=0; i<item.size(); i++){
           Assert.assertTrue(item.get(i).getText().toLowerCase().contains(TEXT));
    }
          item.get(item.size()-1).click();
           driver.get("https://www.webstaurantstore.com/regency-30-x-96-18-gauge-304-stainless-steel-commercial-work-table-with-4-backsplash-and-galvanized-undershelf/600TB3096G.html");
        WebElement button = driver.findElement(By.id("buyButton"));
         button.click();
        WebElement card = driver.findElement(By.xpath("//div[@class='amount-in-cart']//button[@class='btn btn-primary']"));
        card.click();
         driver.get("https://www.webstaurantstore.com/viewcart.cfm");
         WebElement delete = driver.findElement(By.xpath("//a[@class='emptyCartButton btn btn-mini btn-ui pull-right']"));
        delete.click();
            WebElement deleteClick = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
           deleteClick.click();
       }

}
