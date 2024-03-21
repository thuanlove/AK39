package saleforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card_CheckFieldLayout {
    @Test
    public void createRecordOther() throws InterruptedException {
        String fields = "Card Code,Owner,Card ID,Status,Account Name,Card Type," +
                "Contact Name,Customer Code,Source,Created By,Last Modified By";
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://comehome--test.sandbox.lightning.force.com/lightning/r/Card__c/a0C1e000003e8a8EAA/view");
        Thread.sleep(3000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("hmvuong@comehome.impl.test");
        Thread.sleep(3000);
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("123456Pw!");
        Thread.sleep(3000);
        WebElement login = driver.findElement(By.id("Login"));
        login.click();
        Thread.sleep(5000);

        List<String> listFields = new ArrayList<>();
        listFields = new ArrayList<String>(Arrays.asList(fields.split(",")));
        int i =0;
        System.out.println(listFields);
        List<WebElement> pElements = driver.findElements(By.xpath("//flexipage-tab2[@id='tab-1']//span[@class = 'test-id__field-label']"));
        for (WebElement pElement : pElements) {
            String text = pElement.getText();
            if(listFields.contains(text)){
                i++;
            }
        }
        Assert.assertEquals(i,listFields.size());
        Thread.sleep(10000);
        driver.quit();
    }
}
