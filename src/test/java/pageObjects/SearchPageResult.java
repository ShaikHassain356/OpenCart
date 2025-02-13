package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPageResult extends BaseClass {
    public SearchPageResult(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='iPhone']")
    WebElement productresult;

    public String resultproduct()
    {
        return productresult.getText();
    }
}
