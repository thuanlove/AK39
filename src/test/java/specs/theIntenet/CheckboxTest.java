package specs.theIntenet;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CheckboxTest {
    public static  WebDriver driver;

    @Test
    void validateCheckboxSuccessfully(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement checkbox1 = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]"));
        check(checkbox1);
        Assert.assertTrue(isChecked(checkbox1));

        WebElement checkbox2 = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]"));
        check(checkbox2);
        Assert.assertTrue(isChecked(checkbox2));
    }

    public static void check(WebElement element){
        if(!isChecked(element)){
            element.click();
        }
    }

    public static void uncheck(WebElement element){
        if(isChecked(element)){
            element.click();
        }
    }
    public static boolean isChecked(WebElement element){
        return element.getAttribute("checked").equals("true");
    }

    @AfterMethod(alwaysRun = true)
    void closeBrowser(){
        driver.quit();
    }
}


