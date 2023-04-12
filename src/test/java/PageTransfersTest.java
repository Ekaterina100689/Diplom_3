import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.AccountPagePOM;
import pom.LogInPagePOM;
import pom.MainPagePOM;

import java.time.Duration;

import static defaults.Defaults.MY_USER;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.describedAs;

public class PageTransfersTest {
    public WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        MainPagePOM mainPage = new MainPagePOM(driver);
        mainPage.openPage();
    }

    @Test
    @DisplayName("Check transfers to section buns")
    public void correctTransferToBunsSection() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        mainPage.clickButtonBuns();
        String content = driver.findElement(By.xpath(".//div[@style]/div[1]")).getAttribute("class");
        MatcherAssert.assertThat(content, containsString("current"));
    }

    @Test
    @DisplayName("Check transfers to section fillings")
    public void correctTransferToFillingsSection() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        mainPage.clickButtonFillings();
        String content = driver.findElement(By.xpath(".//div[@style]/div[2]")).getAttribute("class");
        MatcherAssert.assertThat(content, containsString("current"));
    }

    @Test
    @DisplayName("Check transfers to section sauces")
    public void correctTransferToSaucesSection() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        mainPage.clickButtonSauces();
        String content = driver.findElement(By.xpath(".//div[@style]/div[3]")).getAttribute("class");
        MatcherAssert.assertThat(content, containsString("current"));
    }

    @Test
    @DisplayName("Check transfer from account page to constructor using button")
    public void transferFromAccountPageToConstructorUsingButton() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        AccountPagePOM accountPage = new AccountPagePOM(driver);
        LogInPagePOM logInPagePOM = new LogInPagePOM(driver);
        mainPage.clickButtonToAccountPage();
        logInPagePOM.logIn(MY_USER);
        mainPage.clickButtonToAccountPage();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Конструктор']")));
        accountPage.clickButtonConstructor();
        Assert.assertTrue(mainPage.checkThatButtonMakeOrderExists());
    }

    @Test
    @DisplayName("Check transfer from account page to constructor using logo")
    public void transferFromAccountPageToConstructorUsingLogo() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        AccountPagePOM accountPage = new AccountPagePOM(driver);
        LogInPagePOM logInPagePOM = new LogInPagePOM(driver);
        mainPage.clickButtonToAccountPage();
        logInPagePOM.logIn(MY_USER);
        mainPage.clickButtonToAccountPage();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Конструктор']")));
        accountPage.clickButtonLogo();
        Assert.assertTrue(mainPage.checkThatButtonMakeOrderExists());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
