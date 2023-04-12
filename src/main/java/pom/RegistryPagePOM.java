package pom;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static defaults.Defaults.BASE_URI;

// Page Object Model for Registry page of Stellar Burgers site
// https://stellarburgers.nomoreparties.site/
public final class RegistryPagePOM {
    // Locator for input field 'name'
    private final By locatorInputFieldName = By.xpath(".//label[text()='Имя']/following-sibling::input");
    // Locator for input field 'email'
    private final By locatorInputFieldEmail = By.xpath(".//label[text()='Email']/following-sibling::input");
    // Locator for input field 'password'
    private final By locatorInputFieldPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    // Locator for button 'register user'
    private final By locatorButtonRegistry = By.xpath(".//button[text()='Зарегистрироваться']");
    // Locator for error pop-up window 'not correct password'
    private final By locatorErrorWindow = By.xpath(".//p[text()='Некорректный пароль']");
    // Locator for button 'log in'
    private final By locatorButtonLogIn = By.xpath(".//a[text()='Войти']");

    private final WebDriver driver;

    public RegistryPagePOM(WebDriver driver) {
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

    private void inputField(By locatorInputField, String fieldContent) {
        driver.findElement(locatorInputField).sendKeys(fieldContent);
    }

    public void inputFieldName(String content) {
        inputField(locatorInputFieldName, content);
    }

    public void inputFieldEmail(String content) {
        inputField(locatorInputFieldEmail, content);
    }

    public void inputFieldPassword(String content) {
        inputField(locatorInputFieldPassword, content);
    }

    public String getPasswordError() {
        return driver.findElement(locatorErrorWindow).getText();
    }

    public void registerUser(User user) {
        inputFieldName(user.getName());
        inputFieldEmail(user.getEmail());
        inputFieldPassword(user.getPassword());
        clickButtonRegistry();
    }
}
