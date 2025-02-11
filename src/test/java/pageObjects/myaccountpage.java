package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myaccountpage extends BaseClass {

    public myaccountpage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "(//h2[normalize-space()='My Account'])[1]")
    WebElement myaccount;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logout;

    public boolean myaccountdisplayed()
    {
        try {
           return myaccount.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public void clicklogout()
    {
        logout.click();
    }

}
