package specs.theIntenet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest {
    /**
     *  Element {tagName: E, Attribute {key: A, value: t}}
     *  Css=> E[A=t] or [A=t]
     *      + if A==id >> #t
     *      + if A=class >> .t
     *
     *  xpath => //E[@A='t'] or //*[@A='t'] => //E[contains(@A,'t')]
     *  absolute path, relative path
     */


    @Test
    void successfullyWithValidCredentials(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.cssSelector("button[type=submit]")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");

        String flashMessage = driver.findElement(By.className("success")).getText();
        Assert.assertTrue(flashMessage.contains("You logged in"));
        driver.quit();
    }

    @Test
    void errorWithInvalidUsername(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmi");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.cssSelector("button[type=submit]")).click();

        String flashMessage = driver.findElement(By.className("error")).getText();
        Assert.assertTrue(flashMessage.contains("Your username is invalid!"),flashMessage);
        driver.quit();
    }

    @Test
    void errorWithInvalidPassword(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");

        driver.findElement(By.cssSelector("button[type=submit]")).click();

        String flashMessage = driver.findElement(By.className("error")).getText();
        Assert.assertTrue(flashMessage.contains("Your password is invalid!"),flashMessage);
        driver.quit();
    }
}
