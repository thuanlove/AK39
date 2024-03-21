package saleforce;

import org.openqa.selenium.By;
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
import java.util.List;
import java.util.Random;

public class Account_CreateNew {
    WebDriver driver;
    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://comehome--test.sandbox.lightning.force.com/lightning/o/Account/list?filterName=Recent");
        driver.manage().window().maximize();
    }
    @Test()
    public void login() throws InterruptedException {
        Thread.sleep(5000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("vivo@comehome.impl.test");
        Thread.sleep(3000);
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("123456Ca");
        Thread.sleep(3000);
        WebElement login = driver.findElement(By.id("Login"));
        login.click();
        Thread.sleep(10000);
    }

    @Test()
    public void updateField() throws InterruptedException{//a[contains(@class,'btnX')
        //Get total rows of list
        int currentRows = 0;
        List<WebElement> oldrows = driver.findElements(By.xpath("//table[@class='slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable']/tbody/tr"));
        currentRows = oldrows.size();

        // Click Edit
        WebElement newButton = driver.findElement(By.xpath("//li[@data-target-selection-name = 'sfdc:StandardButton.Account.New']"));
        newButton.click();
        Thread.sleep(10000);

        Random rn = new Random();
        int codenumber = rn.nextInt(Integer.MAX_VALUE-1) + 1;
        String code = String.valueOf(codenumber);
        WebElement element_code = driver.findElement(By.xpath("//*[@name='AccountNumber']"));
        element_code.sendKeys(code);

        WebElement element_name = driver.findElement(By.xpath("//*[@name='Name']"));
        String acountName="Account_"+code;
        element_name.sendKeys(acountName);

        int phonenumber1 = rn.nextInt(8) + 1;
        int phonenumber2 = rn.nextInt(8) + 1;
        int phonenumber3 = rn.nextInt(8) + 1;
        int phonenumber4 = rn.nextInt(8) + 1;
        int phonenumber5 = rn.nextInt(8) + 1;
        String phone = "03345"+phonenumber1+phonenumber2+phonenumber3+phonenumber4+phonenumber5;
        WebElement element_phone = driver.findElement(By.xpath("//*[@name='Company_Phone__c']"));
        element_phone.sendKeys(phone);

        WebElement element_email = driver.findElement(By.xpath("//*[@name='Email__c']"));
        element_email.sendKeys("Account"+code+"@admin.com");

        //click Save
        WebElement save = driver.findElement(By.xpath("//button[@name = 'save']"));
        save.click();

        //Check result
        Thread.sleep(10000);
        //Find the account name after save action successful.
        WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'"+acountName+"')]"));
        String actual_name= ele.getText();
        //compare with original account name.
        Assert.assertTrue(actual_name.contains(acountName));
    }
    @AfterTest
    public void closedDriver(){
        driver.quit();
    }
}
