package testCases;

import TestBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.loginpage;
import pageObjects.myaccountpage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TC_002_logintest extends BaseTest {
    @Test(groups = {"Regression","Master"})
    public void login() throws IOException {
        try {
            logger.info("starting the test");
            HomePage hp = new HomePage(driver);
            logger.info("clicking on my account");
            hp.clickmyaccount();
            logger.info("clicking on login");
            hp.clicklogin();
            loginpage lg = new loginpage(driver);
            logger.info("passing the email");

            lg.entermail(p.getProperty("email"));
            logger.info("passing the password");
            lg.enterpaswd(p.getProperty("password"));
            logger.info("clicking on button");
            lg.clickbutton();

            myaccountpage mp = new myaccountpage(driver);
            boolean status = mp.myaccountdisplayed();
            Assert.assertTrue(status);
        }
        catch (Exception e)
        {
            Assert.fail("Test failed due to error" +e.getMessage());
        }
        logger.info("Test completed");
    }
}
