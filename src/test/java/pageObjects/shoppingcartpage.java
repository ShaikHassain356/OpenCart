package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class shoppingcartpage extends BaseClass {
    public shoppingcartpage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]")
    WebElement unitprice;


    public String priceofunit()
    {
        return unitprice.getText();
    }
}
