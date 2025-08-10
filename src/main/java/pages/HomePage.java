package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        setDriver(driver);
        driver.get("https://github.com/");
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//button[@placeholder='Search or jump to...']")
    WebElement btnSearch;
    @FindBy(id = "query-builder-test")
    WebElement inputDataSearch;

    public void typeSearchForm(String str){
        btnSearch.click();
        inputDataSearch.sendKeys(str);
        inputDataSearch.sendKeys(Keys.ENTER);
    }
}
