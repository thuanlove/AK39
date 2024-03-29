package specs.hooks;

import org.testng.annotations.*;

public class TestSuite1 {
    @BeforeSuite
    void beforeSuite(){
        System.out.println("before suite1");
    }

    @BeforeTest
    void beforeTest(){
        System.out.println("before test1");
    }

    @BeforeClass
    void beforeClass(){
        System.out.println("before class1");
    }
    @BeforeMethod
    void beforeMethod(){
        System.out.println("before method");
    }

    @Test
    void tc01(){
        System.out.println( "tc01");
    }

    @Test
    void tc02(){
        System.out.printf("tc02");
    }

    @Test
    void tc03(){
        System.out.printf("tc03");
    }

    @Test
    void tc04(){
        System.out.printf("tc04");
    }

    @Test
    void tc05(){
        System.out.printf("tc05");
    }

    @AfterMethod
    void afterMethod(){
        System.out.println("after method");
    }

    @AfterClass
    void afterClass(){
        System.out.println("after class1");
    }

    @AfterTest
    void afterTest(){
        System.out.println("after test1");
    }

    @AfterSuite
    void afterSuite(){
        System.out.println("after suite1");
    }
}
