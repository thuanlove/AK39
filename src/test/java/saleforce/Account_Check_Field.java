package saleforce;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Account_Check_Field extends SaleforceBase{
    public Account_Check_Field() {
        super();
        this.setUrl("https://comehome--test.sandbox.lightning.force.com/lightning/setup/ObjectManager/Account/FieldsAndRelationships/setHistoryTracking");
        this.setUserName("hmvuong@comehome.impl.test");
        this.setPassword("123456Pw!12");
    }
    @Test(priority = 1)
    public void checkFields() throws InterruptedException{
        //Get total rows of list
        int expectedField = 4;
        int actualField = 0;
        List<String> Informations = List.of("Account Name","Account Number","Account Owner","Address 1");//lay từ excel file

        WebElement iframe = driver.findElement(By.xpath("//iframe|//frame"));
        driver.switchTo().frame(iframe);

        List<WebElement> elements = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement e : elements) {
            if (e.isSelected()) {
                String id = e.getAttribute("id").toString();
                WebElement label = driver.findElement(By.xpath("//label[@for='"+id+"']"));
                if(label!=null){
                    String labelText = label.getText();
                    if(Informations.contains(labelText)){
                        System.out.println(labelText);
                        actualField++;
                    }
                }
            }
        }

        // Switching the focus of selenium from iframe to main page
        driver.switchTo().parentFrame();
        //compare with original account name.
        Assert.assertTrue(actualField==expectedField);
    }

    public List<String> GetFieldsInFile(){
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
                Cell cell1 = row.getCell(1); // Giả sử cột dữ liệu "ExpectedValue" nằm ở cột đầu tiên (index 1)
                if (cell1 != null) {
                    String expectedValue = cell1.getStringCellValue();
                    columnValues.add(expectedValue);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return columnValues;
    }
}
