import io.qameta.allure.junit4.DisplayName;
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
import pom.RestorePasswordPagePOM;

import java.time.Duration;

import static defaults.Defaults.MY_USER;

public class LogOutTest {
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
    @DisplayName("Check log out")
    public void correctUserLogOut() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        LogInPagePOM logInPage = new LogInPagePOM(driver);
        AccountPagePOM accountPage = new AccountPagePOM(driver);
        mainPage.clickButtonToAccountPage();
        logInPage.logIn(MY_USER);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//p[text()='Конструктор']")));
        mainPage.clickButtonToAccountPage();
        accountPage.clickButtonLogOut();
        Assert.assertTrue(logInPage.checkThatButtonForgotPasswordExists());
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
