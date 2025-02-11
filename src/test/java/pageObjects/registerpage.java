package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registerpage extends BaseClass {

    public registerpage(WebDriver driver)
    {
        super(driver);
    }

    //locators
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstname;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmpassword;

    @FindBy(xpath = "//label[normalize-space()='Yes']")
    WebElement newslettersub;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement privacypolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueaccount;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement accountconfirmation;

    //action methods

    public void setfirstname(String fname)
    {
        firstname.sendKeys(fname);
    }

    public void setlastname(String lname)
    {
        lastname.sendKeys(lname);
    }

    public void setmail(String mail)
    {
        email.sendKeys(mail);
    }

    public void setphonenum(String telenum)
    {
        telephone.sendKeys(telenum);
    }

    public void setpassword(String pswd)
    {
        password.sendKeys(pswd);
    }

    public void setconfirmpassword(String confirmpswd)
    {
        confirmpassword.sendKeys(confirmpswd);
    }

    public void clicknewsletterconfirm()
    {
        newslettersub.click();
    }

    public void clickprivacypolicy()
    {
        privacypolicy.click();
    }

    public void clickcontinueaccount()
    {
        continueaccount.click();
    }

    public String getConfirmationmsg()
    {
        try {
            return (accountconfirmation.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }



}
