package specs.theIntenet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HyperlinkTest {

    WebDriver browser;
    String baseUrl = "https://the-internet.herokuapp.com/status_codes";

    @BeforeClass
    void setUp() {
        browser = new ChromeDriver();
    }

    @BeforeMethod
    void openPage() {
        browser.get(baseUrl);
    }

    @Test
    void validateHyperlink200() {
        browser.findElement(By.linkText("200")).click();
        Assert.assertEquals(browser.getCurrentUrl(), baseUrl+"/200");

        goBackHome();
        Assert.assertEquals(browser.getCurrentUrl(), baseUrl);

        browser.findElement(By.linkText("301")).click();
        Assert.assertEquals(browser.getCurrentUrl(), baseUrl+"/301");

        goBackHome();
        Assert.assertEquals(browser.getCurrentUrl(), baseUrl);

        browser.findElement(By.linkText("404")).click();
        Assert.assertEquals(browser.getCurrentUrl(), baseUrl+"/404");

        goBackHome();
        Assert.assertEquals(browser.getCurrentUrl(), baseUrl);


        browser.findElement(By.linkText("500")).click();
        Assert.assertEquals(browser.getCurrentUrl(), baseUrl+"/500");

        goBackHome();
        Assert.assertEquals(browser.getCurrentUrl(), baseUrl);
    }
    void goBackHome(){
        browser.navigate().back();
    }
      void clickByLinkText(String linkText){
        browser.findElement(By.linkText(linkText)).click();
    }

    @AfterClass
    void tearDown() {
        browser.quit();
    }
}
