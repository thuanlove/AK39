package specs.theIntenet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JavaScriptAlertTest {
    WebDriver driver;

    @BeforeClass
    void setup(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    void acceptJsAlertPopUp() throws InterruptedException {
        driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();

        driver.switchTo().alert().accept(); // click on OK Button

        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You successfully clicked an alert");
    }

    @Test
    void cancelJsAlertPopUp()  {
        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();

        driver.switchTo().alert().dismiss(); // click on Cancel Button

        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
    }

    @Test
    void inputPromptJsAlertPopUp()  {
        driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();

        driver.switchTo().alert().sendKeys("selenium"); // Enter selenium to JS Prompt
        driver.switchTo().alert().accept();

        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: selenium");
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
