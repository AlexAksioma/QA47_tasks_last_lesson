package ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SearchPage;

public class SearchUITests {

    private WebDriver driver;
    String searchData;

    @BeforeClass
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        searchData = System.getProperty("repo", "QA47");
    }

    @Test
    public void searchRepositoryTestValidateName() {
        String searchData = "QA47";
        HomePage homePage = new HomePage(driver);
        homePage.typeSearchForm(searchData);
        SearchPage searchPage = new SearchPage(driver);
        if (searchPage.isSearchPageOpen()) {
            Assert.assertTrue(searchPage.validateFirstRepo(searchData));
        } else
            Assert.fail("Search page didn't open");
    }

    @Test
    public void searchRepositoryTestProfilePictures() {

        HomePage homePage = new HomePage(driver);
        homePage.typeSearchForm(searchData);
        SearchPage searchPage = new SearchPage(driver);
        if (searchPage.isSearchPageOpen()) {
            Assert.assertTrue(searchPage.validateNumberProfilePictures());
        } else
            Assert.fail("Search page didn't open");
    }

    @AfterClass(enabled = false)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
