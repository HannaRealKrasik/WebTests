package Liverpool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BuySmartTV extends Main {

    @Test
    public void buyLG(){
        float min = 10000;
        float max = 15000;

         driver.get("https://www.liverpool.com.mx/tienda/home");
         Actions mouse = new Actions(driver);
         driver.findElement(By.xpath("//span[@class='a-header__strongLink nav-desktop-menu-action pr-3 pb-3']")).click();
         mouse.moveToElement(driver.findElement(By.xpath("//div/span[contains (text(),'Electrónica')]"))).build().perform();
         mouse.moveToElement(driver.findElement(By.xpath("//div/a[@class='a-desktop__categoryTitle categoryLevel-2'][text()='TV y Video']"))).click().build().perform();
         waiter(By.id("min-price-filter"));

        driver.findElement(By.id("min-price-filter")).sendKeys("10000");
        driver.findElement(By.id("max-price-filter")).sendKeys("15000");
        driver.findElement(By.xpath("//div[@class='a-price__filterButton']")).click();
        driver.findElement(By.id("variants.normalizedSize-50 pulgadas")).click();
        driver.findElement(By.id("brand-LG")).click();

        List<WebElement> listView = driver.findElements(By.xpath("//ul[@class='m-product__listingPlp']/li"));
        Assert.assertEquals(listView.size(), 4);

        for (int i = 0; i < listView.size(); i++) {
            Assert.assertTrue(listView.get(i).getText().toLowerCase().contains("lg"));
        }
        for (int i = 0; i < listView.size(); i++) {
        Assert.assertTrue(listView.get(i).getText().toLowerCase().contains("50 pulgadas"));
        }
        List<WebElement> price = driver.findElements(By.xpath("//p[@class='a-card-discount']"));
        Assert.assertEquals(price.size(), 4);

        for (int i = 0; i < price.size(); i++) {
            String a = price.get(i).getText().replace(",", "").replace("$", "").replace("75",".75");
            float priceOfEach = Float.parseFloat(a);
            System.out.println(priceOfEach);
            Assert.assertTrue(priceOfEach > min && priceOfEach < max);
        }
    }

}
