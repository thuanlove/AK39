package saleforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Card_ShowFields {
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
    public void showField(){
        String[] CardInformations = {"Card Code","Owner","Card ID","Status","Account Name","Card Type","Contact Name"};
        String[] IntegrationInformations = {"Customer Code","Source"};
        String[] SystemInformations = {"Created By","Last Modified By"};
        //Session 1
        WebElement SS1 = driver.findElement(By.xpath("//span[contains(text(),'Card Information')]"));
        String SessionName1 = SS1.getText();
        System.out.println("Session: "+ SessionName1);
        WebElement sessionCardInformation = driver.findElement(By.xpath("(//records-record-layout-section)[1]"));
        List<WebElement> fieldofLayout1 = sessionCardInformation.findElements(By.xpath(".//span[contains(@class, 'test-id__field-label')]"));
        int i=0;
        for (WebElement name : fieldofLayout1){
            String n1 = name.getText();
            System.out.println("- "+n1);
            i++;
        }
        Assert.assertEquals(i,CardInformations.length);

        //Session 2
        WebElement SS2 = driver.findElement(By.xpath("//span[contains(text(),'Integration Information')]"));
        String SessionName2 = SS2.getText();
        System.out.println("Session: "+ SessionName2);
        WebElement sessionIntegrationInformation = driver.findElement(By.xpath("(//records-record-layout-section)[2]"));
        List<WebElement> fieldofLayout2 = sessionIntegrationInformation.findElements(By.xpath(".//span[contains(@class, 'test-id__field-label')]"));
        i=0;
        for (WebElement name : fieldofLayout2){
            String n2 = name.getText();
            System.out.println("- " + n2);
            i++;
        }
        Assert.assertEquals(i,IntegrationInformations.length);

        // Session 3
        WebElement SS3 = driver.findElement(By.xpath("//span[contains(text(),'System Information')]"));
        String SessionName3 = SS3.getText();
        System.out.println("Session: "+ SessionName3);
        WebElement sessionSystemInformation = driver.findElement(By.xpath("(//records-record-layout-section)[3]"));
        List<WebElement> fieldofLayout3 = sessionSystemInformation.findElements(By.xpath(".//span[contains(@class, 'test-id__field-label')]"));
        i=0;
        for (WebElement name : fieldofLayout3){
            String n3 = name.getText();
            System.out.println("- " + n3);
            i++;
        }
        Assert.assertEquals(i,SystemInformations.length);
    }
    @AfterTest
    public void closedDriver(){
        driver.quit();
    }
}
