package pom;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Page Object Model for restore password page of Stellar Burgers site
// https://stellarburgers.nomoreparties.site/
public final class RestorePasswordPagePOM {
    // Locator for button 'log in'
    private final By locatorButtonLogIn = By.xpath(".//a[text()='Войти']");

    private final WebDriver driver;

    public RestorePasswordPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    private void clickButton(By locatorButton) {
        driver.findElement(locatorButton).click();
    }

    public void clickButtonLogIn() {
        clickButton(locatorButtonLogIn);
    }
}
