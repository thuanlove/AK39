package saleforce;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SaleforceBase {
    protected static  WebDriver driver;
    private String _url;
    private String _userName;
    private String _password;

    public SaleforceBase() { }
    public SaleforceBase(String _userName, String _password) {
        this._userName = _userName;
        this._password = _password;
    }
    public SaleforceBase(String _url, String _userName, String _password) {
        this._url = _url;
        this._userName = _userName;
        this._password = _password;
    }

    public String getUrl() {
        if(this._url==null || this._url.equals(""))
            return "https://comehome--test.sandbox.lightning.force.com/lightning/o/Account/list?filterName=Recent";
        else
            return _url;
    }

    public void setUrl(String url) {
        this._url = url;
    }

    public String getUserName() {
        if(this._userName==null || this._userName.equals(""))
            return "vivo@comehome.impl.test";
        else
            return _userName;
    }

    public void setUserName(String userName) {
        this._userName = userName;
    }

    public String getPassword() {
        if(this._password==null || this._password.equals(""))
            return "123456Ca";
        else
            return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    @BeforeTest
    public void setUp(){
        ChromeOptions options  = new ChromeOptions();
        //options.addArguments("incognito");
        options.addArguments("--disable-notifications");
        //options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.get(this.getUrl());
        driver.manage().window().maximize();
    }
    @Test()
    public void login() throws InterruptedException {
        Thread.sleep(5000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(this.getUserName());
        Thread.sleep(3000);
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys(this.getPassword());
        Thread.sleep(3000);
        WebElement login = driver.findElement(By.id("Login"));
        login.click();
        Thread.sleep(10000);
    }

    @AfterMethod
    protected void captureScreenWhenFail(ITestResult iTestResult)
    {
        if(!iTestResult.isSuccess()){
            String name = iTestResult.getMethod().getMethodName();
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./target/screenshot/"
                    + name
                    + "-"
                    + System.currentTimeMillis() + ".png");
            try {
                FileUtils.copyFile(file, DestFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @AfterTest
    public void closedDriver(){
        driver.quit();
    }
}
