import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class XpathTest {


        public static WebDriver driver;


        @Test
        public void testXpath() {
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            driver.get("https://github.com/SergeiDemyanenko/PlatformaticaQA_03");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

          //  WebElement find = driver.findElement(By.xpath("//button[@class='btn-with-count btn-sm btn']"));

          //  WebElement find1 = driver.findElement(By.xpath("//button[@aria-label='Star this repository'][@class='js-toggler-target btn-with-count btn-sm btn']"));

          //  WebElement find2 = driver.findElement(By.xpath("//summary[@class='rounded-right-0 btn-sm btn']"));
            WebElement find3 = driver.findElement(By.xpath("//a[@id='code-tab']"));
            WebElement find4 = driver.findElement(By.xpath("//a[@id='issues-tab']"));
            WebElement find5 = driver.findElement(By.xpath("//a[@id='pull-requests-tab']"));
        }
    }