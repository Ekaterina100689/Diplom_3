package pom;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Page Object Model for account page of Stellar Burgers site
// https://stellarburgers.nomoreparties.site/
public final class AccountPagePOM {
    // Locator for button 'log out'
    private final By locatorButtonLogOut = By.xpath(".//button[text() = 'Выход']");
    // Locator for button 'constructor'
    private final By locatorButtonConstructor = By.xpath(".//p[text()='Конструктор']");
    // Locator for button 'logo'
    private final By locatorButtonLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    private final WebDriver driver;

    public AccountPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    private void clickButton(By locatorButton) {
        driver.findElement(locatorButton).click();
    }

    public void clickButtonLogOut() {
        clickButton(locatorButtonLogOut);
    }

    public void clickButtonConstructor() {
        clickButton(locatorButtonConstructor);
    }

    public void clickButtonLogo() {
        clickButton(locatorButtonLogo);
    }

}
