package saleforce;

import Utils.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Card_CheckRequireFields {
    WebDriver driver;
    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://comehome--test.sandbox.lightning.force.com/lightning/r/Card__c/a0C1e000003e8a8EAA/view");
        driver.manage().window().maximize();
    }
    @Test()
    public void login() throws InterruptedException {
        Thread.sleep(5000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("hmvuong@comehome.impl.test");
        Thread.sleep(3000);
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("123456Pw!");
        Thread.sleep(3000);
        WebElement login = driver.findElement(By.id("Login"));
        login.click();
        Thread.sleep(10000);
    }

    @Test()
    public void testRequireField() throws InterruptedException{//a[contains(@class,'btnX')
        //List<WebElement> row = driver.findElements(By.xpath(".//table//tbody/tr"));
        //        int rowsize = row.size();
        Functions.waitForPageLoaded(driver);
        WebElement m = driver. findElement(By.xpath("//button[contains(@class,'test-id__inline-edit-trigger')]"));
        m.click();
        Thread.sleep(10000);

        List<String> expectFieldRequire = new ArrayList<>();
        expectFieldRequire.add("*Card ID");
        expectFieldRequire.add("*Status");

        List<WebElement> requiredFields = driver.findElements(By.xpath("//abbr[contains(@title, 'required')]"));

        int i=0;
        for (WebElement abbrElement : requiredFields) {
            // Truy xuất phần tử label mẹ của abbr
            WebElement labelElement = abbrElement.findElement(By.xpath("./ancestor::label"));
            // Lấy nội dung văn bản của label và xuất ra màn hình
            String field = labelElement.getText();
            Assert.assertEquals(field,expectFieldRequire.get(i));
            i++;
        }
    }
    @AfterTest
    public void closedDriver(){
        driver.quit();
    }
}
