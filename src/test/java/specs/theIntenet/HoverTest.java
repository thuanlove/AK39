package specs.theIntenet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HoverTest {
    WebDriver driver;

    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/hovers");
    }

    @Test
    void validateImageName(){
        Actions mouse = new Actions(driver);

        mouse.moveToElement(driver.findElement(By.xpath("//div[@class='figure'][1]"))).perform();

        String imageCaption = driver.findElement(By.cssSelector(".figcaption h5")).getText();
        Assert.assertTrue(imageCaption.contains("user1"),"wrong image caption");
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
