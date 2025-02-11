package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {

    WebDriver driver;

    public BaseClass(WebDriver driuer)
    {
        this.driver=driuer;
        PageFactory.initElements(driuer,this);
    }
}
