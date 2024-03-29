package specs.theIntenet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DragDropTest {


    @Test
    void changeColumns(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        Actions actions = new Actions(driver);
        //todo drag A => B
        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        actions.dragAndDrop(source,target).perform();

        List<WebElement> columns = driver.findElements(By.cssSelector("#columns div header"));

        Assert.assertEquals(columns.get(0).getText(),"B");
        Assert.assertEquals(columns.get(1).getText(),"A");
    }
}
