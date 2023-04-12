import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.LogInPagePOM;
import pom.MainPagePOM;
import pom.RestorePasswordPagePOM;

import java.time.Duration;

import static defaults.Defaults.MY_USER;

public class LogInTest {
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
    @DisplayName("Check correct login using button 'my account'")
    public void correctUserLoginUsingButtonMyAccount() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        LogInPagePOM logInPage = new LogInPagePOM(driver);
        mainPage.clickButtonToAccountPage();
        logInPage.logIn(MY_USER);
        Assert.assertTrue(mainPage.checkThatButtonMakeOrderExists());
    }

    @Test
    @DisplayName("Check correct login using button 'my account'")
    public void correctUserLoginUsingButtonEnterAccount() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        LogInPagePOM logInPage = new LogInPagePOM(driver);
        mainPage.clickButtonToAccountPage();
        logInPage.logIn(MY_USER);
        Assert.assertTrue(mainPage.checkThatButtonMakeOrderExists());
    }

    @Test
    @DisplayName("Check correct login using button from recovery form")
    public void correctUserLoginUsingButtonInRestorePasswordForm() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        LogInPagePOM logInPage = new LogInPagePOM(driver);
        RestorePasswordPagePOM restorePagePOM = new RestorePasswordPagePOM(driver);
        mainPage.clickButtonToAccountPage();
        logInPage.clickButtonForgotPassword();
        restorePagePOM.clickButtonLogIn();
        logInPage.logIn(MY_USER);
        Assert.assertTrue(mainPage.checkThatButtonMakeOrderExists());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
