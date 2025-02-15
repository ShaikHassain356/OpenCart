package testCases;

import TestBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.Objects;

public class TC_004_searchingproduct extends BaseTest {

    @Test
    public void searchproduct()
    {
     try {
         logger.info("Test Started");
         HomePage hp=new HomePage(driver);
         logger.info("clicking on my account");
         hp.clickmyaccount();
         logger.info("clicking on login");
         hp.clicklogin();
         loginpage lp=new loginpage(driver);
         logger.info("Passing the emailid");
         lp.entermail(p.getProperty("email"));
         logger.info("Passing the password");
         lp.enterpaswd(p.getProperty("password"));
         logger.info("cliking on loginbutton");
         lp.clickbutton();
         SearchPage sp=new SearchPage(driver);
         logger.info("fetching product name from properties file");
         sp.inputsearch(p.getProperty("product"));
         logger.info("clicking on search button");
         sp.clicksearchbutton();
         Thread.sleep(5000);
         SearchPageResult spr=new SearchPageResult(driver);
         String result= spr.resultproduct();
         logger.info("validating the result");
         System.out.println(result);
         logger.debug("After printing the result");

        if (Objects.equals(result, "iPhone"))
         {
             System.out.println("product is searched");
             System.out.println(result);
             logger.info("adding to cart");
             Thread.sleep(10000);
             spr.clickaddtocart();
             Thread.sleep(5000);
             logger.info("validating the message after adding to cart");
             boolean cartstatus= spr.addedtocart();
             if (cartstatus)
             {
                 logger.info("clicking on shoppingcart");
                 spr.clickshoppingcart();
                 shoppingcartpage scp=new shoppingcartpage(driver);
                 String price= scp.priceofunit();
                 System.out.println("price of the product is "+price);


             }
             else{
                 System.out.println("product not added to cart");
             }

         }

         else {
             System.out.println("Test failed");
         }

     }
     catch (Exception e)
     {
         Assert.fail("Test failed due to error" +e.getMessage());
     }

     logger.info("Test Completed");

    }

}
