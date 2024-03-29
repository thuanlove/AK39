package specs.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxTest {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://selenium.dev/");
    }
}
