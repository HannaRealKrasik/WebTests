package Liverpool;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchBarTest extends Main {

    public void searchBarInput(String key, By element){
        driver.get("https://www.liverpool.com.mx/tienda/home");
        driver.findElement(By.id("mainSearchbar")).sendKeys(key);
        driver.findElement(By.xpath("//button[@class='input-group-text']/i")).click();
        waiter(element);

    }

    public  void scrollClick(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
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
    public void testBrandInput() {
        final String TEXT = "gucci";
        searchBarInput("GUCCI",By.xpath("//a[@class='a-breadcrumb__label 6']"));

        Assert.assertEquals(driver.findElement(By.xpath("//a[@class='a-breadcrumb__label 6']")).getText(), "GUCCI");

            for (int a = 2; a <= 4; a++) {
                waiter(By.xpath("//h5[@class='card-title a-card-description']"));
                List<WebElement> brandTitles = driver.findElements(By.xpath("//h5[@class='card-title a-card-description']"));

                for (int i = 0; i < brandTitles.size(); i++){
                    Assert.assertTrue(brandTitles.get(i).getText().toLowerCase().contains(TEXT));}
                scrollClick(driver, driver.findElement(By.xpath("//li/a[@class='page-link'][contains(text(),'" + a + "')]")));
            }
        }
}





