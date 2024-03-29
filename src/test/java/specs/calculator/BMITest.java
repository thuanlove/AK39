package specs.calculator;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BMIPage;
import supports.Browser;

public class BMITest extends BaseTest {

    BMIPage bmiPage;
    @Parameters({"browser"})
    @BeforeClass
    void setup(String browser) {
        Browser.open(browser);
        bmiPage = new BMIPage();
    }

    @DataProvider
    Object[][] testData(){
        return new Object[][]{
                //String age,String gender,String height,String weight, String expectedResult
                {"25", "male", "180", "65","BMI = 20.1 kg/m2"},
                {"25", "male", "180", "100","BMI = 30.9 kg/m2"},
                {"25", "male", "180", "35","BMI = 10.8 kg/m2"},
        };
    }

    @BeforeMethod
    void cleanForm(){
        bmiPage.open();
        bmiPage.selectMetricTab();
        bmiPage.clearForm();
    }

    @Test(description = "validate normal category BMI",dataProvider = "testData")
    void tc10(String age,String gender,String height,String weight, String expectedResult) {
        bmiPage.fillForm(age, gender, height, weight);
        Assert.assertEquals(bmiPage.getResult(), expectedResult);
    }
}
