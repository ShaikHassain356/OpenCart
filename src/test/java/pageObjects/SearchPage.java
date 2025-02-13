package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BaseClass {

public SearchPage(WebDriver driver)
{
    super(driver);
}

@FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchinput;

@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    WebElement searchbutton;

public void inputsearch(String product)
{
    searchinput.sendKeys(product);
}

public void clicksearchbutton()
{
    searchbutton.click();
}
}
