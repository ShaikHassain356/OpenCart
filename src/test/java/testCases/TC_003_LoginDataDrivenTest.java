package testCases;

import TestBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BaseClass;
import pageObjects.HomePage;
import pageObjects.loginpage;
import pageObjects.myaccountpage;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseTest {
    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "DataDriven")// this extra parameter we need to pass if data provider is in different class or package
   public void datadriven(String username, String pswd, String res)
   {
       try {

           logger.info("Test Started");
           HomePage hp = new HomePage(driver);
           logger.info("clicking on my account");
           hp.clickmyaccount();
           logger.info("clicking on login");
           hp.clicklogin();
           loginpage lg = new loginpage(driver);
           logger.info("passing the email");

           lg.entermail(username);
           logger.info("passing the password");
           lg.enterpaswd(pswd);
           logger.info("clicking on button");
           lg.clickbutton();

           myaccountpage mp = new myaccountpage(driver);
           boolean status = mp.myaccountdisplayed();

       /* data is valid -- login successful -- test passed
                        -- login unsuccessful -- test failed
          data is invalid -- login successful -- test failed
                         -- login unsuccessful -- test passed*/

           if (res.equalsIgnoreCase("valid")) {
               if (status == true) {
                   mp.clicklogout();
                   Assert.assertTrue(true);
               } else {
                   Assert.assertTrue(false);
               }
           }

           if (res.equalsIgnoreCase("Invalid")) {
               if (status == true) {
                   mp.clicklogout();
                   Assert.assertTrue(false);
               } else {
                   Assert.assertTrue(true);
               }
           }
       }
       catch (Exception e)
       {
           Assert.fail();
       }
       logger.info("Test Completed");
   }
}
