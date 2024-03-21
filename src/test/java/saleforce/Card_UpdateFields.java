package saleforce;

import Utils.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Card_UpdateFields {
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
    public void updateField() throws InterruptedException{//a[contains(@class,'btnX')
        // Click Edit
        WebElement Edit = driver.findElement(By.xpath("//button[@name = 'Edit']"));
        Edit.click();
        Thread.sleep(5000);

        String code="0000000047166";
        WebElement element_enter = driver.findElement(By.xpath("//*[@name='Customer_Code__c']"));
        element_enter.sendKeys(code);

        //click Save
        WebElement save = driver.findElement(By.xpath("//button[@name = 'SaveEdit']"));
        save.click();


        //Check result
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[contains(@class,'toastMessage')]")));
        String expectError = "Can not close Case manually";
        String errorText = errorMessage.getText();
        Assert.assertEquals(expectError,errorText);
    }
    @AfterTest
    public void closedDriver(){
        driver.quit();
    }
}
