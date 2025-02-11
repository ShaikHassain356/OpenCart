package pageObjects;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {
    public HomePage(WebDriver driver)
    {
     super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myaccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement register;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement login;

    public void clickmyaccount()
    {
        myaccount.click();
    }

    public void clickregister()
    {
        register.click();
    }

    public void clicklogin()
    {
        login.click();
    }
}
