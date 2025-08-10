package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[@data-testid='results-list']//a")
    WebElement firstRepo;  //href
    @FindBy(xpath = "//a[@data-testid='nav-item-repositories']//span[@data-testid='resolved-count-label']/span[@class='prc-Text-Text-0ima0']")
    WebElement quantityRepo;
    @FindBy(xpath = "//div[@data-testid='results-list']//img")
    List<WebElement> numberPictures;
    @FindBy(xpath = "//div[@display='inline-block']")
    WebElement inlineBlock;

    public boolean isSearchPageOpen() {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.urlContains("search"));
    }

    public boolean validateFirstRepo(String searchData) {
        String result = firstRepo.getAttribute("href");
        System.out.println(result);
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .attributeContains(firstRepo, "href", searchData));
    }

    public boolean validateNumberProfilePictures() {
        int numberProfile = Integer.parseInt(quantityRepo.getText());
        System.out.println(numberProfile);
        System.out.println(inlineBlock.getText());
        String textInlineBlock = inlineBlock.getText();
        int index1 = textInlineBlock.length() - 5;
        int index2 = textInlineBlock.length() - 4;
        int numberOfPage = Integer.parseInt(textInlineBlock.substring(index1, index2));
        System.out.println(numberOfPage);
        driver.findElement(By.xpath("//a[text()='"+textInlineBlock.substring(index1, index2)+"']")).click();
        int res = (numberOfPage - 1)*10 + numberPictures.size();
        System.out.println(res);
        return numberProfile == res;
    }
}
