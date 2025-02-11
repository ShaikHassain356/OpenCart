package testCases;

import TestBase.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.registerpage;

import java.time.Duration;

public class TC_001_registeraccount extends BaseTest {

    @Test(groups = {"Sanity","Master"})
    void test() throws InterruptedException {
        try {
            logger.info("*** Starting Testcase ***");
            HomePage hp = new HomePage(driver);
            hp.clickmyaccount();
            logger.info("clicked on my account");
            hp.clickregister();
            logger.info("clicked on register button");

            String pass = randomalphanumeric();
            logger.info("providing customer details");
            registerpage rp = new registerpage(driver);
            rp.setfirstname(randomemail().toUpperCase());
            rp.setlastname(randomemail().toUpperCase());
            rp.setmail(randomemail() + "@gmail.com");
            rp.setphonenum(randomphone());
            Thread.sleep(5000);
            rp.setpassword(pass);
            rp.setconfirmpassword(pass);
            rp.clicknewsletterconfirm();
            rp.clickprivacypolicy();
            rp.clickcontinueaccount();
            logger.info("validating confirmation msg");
            String confirmationmsg = rp.getConfirmationmsg();
            if (confirmationmsg.equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            }
            else {

                logger.error("Test Failed");
                logger.debug("Debug logs");
                Assert.assertTrue(false);
            }

        }
        catch (Exception e)
        {
            SoftAssert sa=new SoftAssert();
          sa.fail();
        }
    }



    //this method will generate the random 5 random alphabet in string format from a-z
}
