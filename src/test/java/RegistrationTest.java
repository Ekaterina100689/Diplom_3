import java.time.Duration;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import model.User;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static defaults.Defaults.BASE_URI;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.startsWith;

import api.client.UserClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.LogInPagePOM;
import pom.MainPagePOM;
import pom.RegistryPagePOM;
import util.UserGenerator;

public class RegistrationTest {
    private String token;
    public WebDriver driver;
    public UserClient client;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        MainPagePOM mainPage = new MainPagePOM(driver);
        mainPage.openPage();
    }

    @Test
    @DisplayName("Check correct registration")
    public void correctUserCreate() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        LogInPagePOM logInPage = new LogInPagePOM(driver);
        RegistryPagePOM registryPage = new RegistryPagePOM(driver);
        mainPage.clickButtonToAccountPage();
        logInPage.clickButtonRegistry();
        User user = UserGenerator.GenerateUser();
        registryPage.registerUser(user);
        Assert.assertTrue(logInPage.checkThatButtonForgotPasswordExists());
        token = client.getAccessTokenForUser(user);
    }

    @Test
    @DisplayName("Check not correct registration, with wrong password less then 6 symbols")
    public void attemptToCreateUserWithShortPassword() {
        MainPagePOM mainPage = new MainPagePOM(driver);
        LogInPagePOM logInPage = new LogInPagePOM(driver);
        RegistryPagePOM registryPage = new RegistryPagePOM(driver);
        mainPage.clickButtonToAccountPage();
        logInPage.clickButtonRegistry();
        User user = UserGenerator.GenerateUser();
        user.setPassword("4444");
        registryPage.registerUser(user);
        String errorText = registryPage.getPasswordError();
        MatcherAssert.assertThat("Вход", errorText, startsWith("Некорректный пароль"));
    }

    @After
    public void tearDown() throws Exception {
        if (token != null && !token.isEmpty()) {
            // cleanup steps
            client.getDeleteUserResponse(token)
                    .then()
                    .statusCode(HTTP_ACCEPTED);
        }
        driver.quit();
    }
}
