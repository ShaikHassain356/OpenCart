package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginpage extends BaseClass {

    public loginpage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "(//input[@id='input-password'])[1]")
    WebElement password;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginbutton;


    public void entermail(String mail)
    {
        email.sendKeys(mail);
    }

    public void enterpaswd(String pswd)
    {
        password.sendKeys(pswd);
    }

    public void clickbutton()
    {
        loginbutton.click();
    }
}
