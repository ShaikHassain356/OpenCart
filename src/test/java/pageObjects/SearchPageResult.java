package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPageResult extends BaseClass {
    public SearchPageResult(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='caption']//a[contains(text(),'iPhone')]")
    WebElement productresult;

    @FindBy(xpath = "(//button[@type='button'])[10]")
    WebElement addtocart;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement afteraddingtocart;

    @FindBy(xpath = "//span[normalize-space()='Shopping Cart']")
    WebElement shoppingcart;

    public String resultproduct()
    {
        return productresult.getText();
    }

    public void clickaddtocart()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Scroll to the button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addtocart);

        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(addtocart));

        // Click using JavaScript (optional fallback)
        try {
            addtocart.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", addtocart);
        }

    }

    public boolean addedtocart()
    {
        return afteraddingtocart.isDisplayed();
    }

    public void clickshoppingcart()
    {
        shoppingcart.click();
    }
}
