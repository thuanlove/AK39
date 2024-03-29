package specs.theIntenet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SortedTableTest {
    List<Person> personList;
    @BeforeClass
    void getTableData(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");

        int totalRow = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
        personList = new ArrayList<>();

        for (int i = 1; i <= totalRow; i++) {
            // Mapping text of WebElement to Object
            String lastName = driver.findElement(By.xpath(String.format("//table[@id='table1']/tbody/tr[%d]/td[1]", i ))).getText();
            String firstName = driver.findElement(By.xpath(String.format("//table[@id='table1']/tbody/tr[%d]/td[2]", i ))).getText();
            String email    = driver.findElement(By.xpath(String.format("//table[@id='table1']/tbody/tr[%d]/td[3]", i ))).getText();
            String due      = driver.findElement(By.xpath(String.format("//table[@id='table1']/tbody/tr[%d]/td[4]", i ))).getText();
            String website  = driver.findElement(By.xpath(String.format("//table[@id='table1']/tbody/tr[%d]/td[5]", i ))).getText();
            personList.add(new Person(lastName, firstName, email, due, website));
        }
        driver.quit();
    }

    @Test
    void verifyLargestDuePerson() {
        String maxDuePerson =personList.stream().max(Comparator.comparing(Person::getDue)).get().getFullName();
        Assert.assertEquals(maxDuePerson,"Jason Doe");
    }

    @Test
    void verifyMinimumDuePerson(){
        Float minDueValue = personList.stream().min(Comparator.comparing(Person::getDue)).get().getDue();
        List<String> minDuePerson = personList
                .stream()
                .filter(person -> Objects.equals(person.getDue(), minDueValue))
                .collect(Collectors.toList())
                .stream()
                .map(Person::getFullName)
                .collect(Collectors.toList());

        Assert.assertEquals(minDuePerson, List.of("John Smith", "Tim Conway"));
    }
}
