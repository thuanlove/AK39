package specs.theIntenet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropDownTest {
    WebDriver driver;

    @Test
    void validateDropDownSuccessfully(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropDownElement = webDriver.findElement(By.id("dropdown"));

        Select select = new Select(dropDownElement);
        select.selectByVisibleText("Option 1");

        WebElement option1 = webDriver.findElement(By.xpath("//select[@id='dropdown']//option[.='Option 1']"));
        Assert.assertTrue(option1.isSelected());
    }

    @Test
    void validateSelectMultiOptionSuccessfully(){
        driver = new ChromeDriver();
        driver.get("https://output.jsbin.com/osebed/2");

        Select select = new Select(driver.findElement(By.id("fruits")));

        select.selectByVisibleText("Banana");
        select.selectByVisibleText("Apple");

        Assert.assertTrue(driver.findElement(By.xpath("//option[.='Banana']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//option[.='Apple']")).isSelected());

        Assert.assertFalse(driver.findElement(By.xpath("//option[.='Orange']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//option[.='Grape']")).isSelected());

        select.deselectAll();

        Assert.assertFalse(driver.findElement(By.xpath("//option[.='Banana']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//option[.='Apple']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//option[.='Orange']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//option[.='Grape']")).isSelected());
    }
}
