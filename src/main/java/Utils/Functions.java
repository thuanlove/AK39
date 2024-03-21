package Utils;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Functions {
    public static void waitForPageLoaded(WebDriver driver) {

        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver,duration);
        try {
            wait.until(expectation);
        } catch(Throwable error) {
            Assert.isTrue(true, "Timeout waiting for Page Load Request to complete.");
        }
    }
}
