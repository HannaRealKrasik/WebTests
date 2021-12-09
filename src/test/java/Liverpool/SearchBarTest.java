package Liverpool;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchBarTest {
    public static WebDriver driver;

    @BeforeMethod
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterMethod
    public void after(){
      //  driver.quit();
    }

    public void searchBarInput(String key, By element){
        driver.get("https://www.liverpool.com.mx/tienda/home");
        driver.findElement(By.id("mainSearchbar")).sendKeys(key);
        driver.findElement(By.xpath("//button[@class='input-group-text']/i")).click();
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }

    @Test
    public void testValidInput(){
       searchBarInput("Mesa de regalos",By.id("Mesa de Regalos"));

       Assert.assertEquals(driver.findElement(By.id("Mesa de Regalos"))
               .getText(),"Mesa de Regalos");
       Assert.assertEquals(driver.findElement(By.xpath("//button[@class='btnPrimary btnExtraLarge']"))
               .getText(),"Buscar una Mesa de Regalos");
       Assert.assertEquals(driver.findElement(By.xpath("//button[@class='btnSecondary btnExtraLarge']"))
                .getText(),"Crear Mesa de Regalos");
    }

    @Test
    public void testInValidInput(){
        searchBarInput("QQQ",By.xpath("//h1[@class='a-headline__results d-none d-lg-block']"));

        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='a-headline__results d-none d-lg-block']"))
                .getText(),"Tu Búsqueda \"QQQ\" arrojó \"0\" resultados");
        Assert.assertTrue(driver.findElement(By.id("mainSearchbar")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='a-title__searchAdvice mt-5']")).getText(),"Consejos de Búsqueda");
    }

    @Test
    public void testBrendInput(){
        final String TEXT = "armani";
        searchBarInput("Armani",By.xpath("//a[@class='a-breadcrumb__label 6']"));

        Assert.assertEquals(driver.findElement(By.xpath("//a[@class='a-breadcrumb__label 6']")).getText(),"Armani");
        List<WebElement> item = driver.findElements(By.xpath("//h5[@class='card-title a-card-description']"));
        for (int i = 0; i < item.size(); i++){
            Assert.assertTrue(item.get(i).getText().toLowerCase().contains(TEXT));
        }

    }
}
