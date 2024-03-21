package saleforce;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Card_CheckFieldInfile {
    public HashMap<String,List<String>> GetFieldsInFile(){
        HashMap<String,List<String>> expectedValues = new HashMap<String,List<String>>();
        List<String> columnValues = new ArrayList<String>();
        String headerName ="";
        try{
            String filePath = "C:\\Local\\Projects\\Automations\\Salesforce.xlsx"; // Đường dẫn tới file Excel-nên bỏ cùng thư mục với code
            String sheetName = "Card"; // Tên sheet chứa dữ liệu
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Cell cell1 = row.getCell(0); // Giả sử cột dữ liệu "ExpectedValue" nằm ở cột đầu tiên (index 0)
                String expectedValue = cell1.getStringCellValue();
                Cell cell2 = row.getCell(1);
                if (cell2 == null || cell2.getCellType() == CellType.BLANK) {
                    columnValues.add(expectedValue);
                }
                else {
                    if(i>1){
                        expectedValues.put(headerName,columnValues);
                        columnValues = new ArrayList<String>();
                    }
                    headerName =expectedValue;
                }
            }
            expectedValues.put(headerName,columnValues);
            columnValues = new ArrayList<String>();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return expectedValues;
    }

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
        HashMap<String,List<String>> listField = GetFieldsInFile();
        //Sessions

        WebElement SS1 = driver.findElement(By.xpath("//span[contains(text(),'Card Information')]"));
        String SessionName1 = SS1.getText();
        System.out.println("Session: "+ SessionName1);
        WebElement sessionCardInformation = driver.findElement(By.xpath("(//records-record-layout-section)[1]"));
        List<WebElement> fieldofLayout1 = sessionCardInformation.findElements(By.xpath(".//span[contains(@class, 'test-id__field-label')]"));
        int i=0;
        for (WebElement name : fieldofLayout1){
            String n1 = name.getText();
            System.out.println("- "+n1);
            Assert.assertTrue(listField.get("Card Information").contains(n1));
            i++;
        }

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
            Assert.assertTrue(listField.get("Integration Information").contains(n2));
            i++;
        }

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
            Assert.assertTrue(listField.get("System Information").contains(n3));
            i++;
        }
    }
    @AfterTest
    public void closedDriver(){
        driver.quit();
    }
}
