package pom;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static defaults.Defaults.BASE_URI;

// Page Object Model for Log in page of Stellar Burgers site
// https://stellarburgers.nomoreparties.site/
public final class LogInPagePOM {
    // Locator for input field 'email'
    private final By locatorInputFieldEmail = By.xpath(".//label[text()='Email']/following-sibling::input");
    // Locator for input field 'password'
    private final By locatorInputFieldPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    // Locator for button 'register user'
    private final By locatorButtonRegistry = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]");
    // Locator for button 'log in'
    private final By locatorButtonLogIn = By.xpath(".//a[text()='Войти']");
    // Locator for button 'forgot password'
    private final By locatorButtonForgotPassword = By.xpath(".//a[text()='Восстановить пароль']");

    private final WebDriver driver;

    public LogInPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    private void clickButton(By locatorButton) {
        driver.findElement(locatorButton).click();
    }

    public void clickButtonLogIn() {
        clickButton(locatorButtonLogIn);
    }

    public void clickButtonRegistry() {
        clickButton(locatorButtonRegistry);
    }

    public void clickButtonForgotPassword() {
        clickButton(locatorButtonForgotPassword);
    }

    private void inputField(By locatorInputField, String fieldContent) {
        driver.findElement(locatorInputField).sendKeys(fieldContent);
    }

    public void inputFieldEmail(String content) {
        inputField(locatorInputFieldEmail, content);
    }

    public void inputFieldPassword(String content) {
        inputField(locatorInputFieldPassword, content);
    }

    public boolean checkThatButtonForgotPasswordExists() {
        return driver.findElements(locatorButtonForgotPassword).size() > 0;
    }

    public void logIn(User user) {
        inputFieldEmail(user.getEmail());
        inputFieldPassword(user.getPassword());
        clickButtonLogIn();
    }


}
