package pom;

import static defaults.Defaults.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Page Object Model for Main page of Stellar Burgers site
// https://stellarburgers.nomoreparties.site/
public final class MainPagePOM {
    // Locator for log in button
    private final By locatorButtonLogIn = By.xpath(".//button[text()='Войти в аккаунт']");
    // Locator for button "buns"
    private final By locatorButtonBuns = By.xpath(".//div[span[text()='Булки']]");
    // Locator for button "fillings"
    private final By locatorButtonFillings = By.xpath(".//div[span[text()='Начинки']]");
    // Locator for button "sauces"
    private final By locatorButtonSauces = By.xpath(".//div[span[text()='Соусы']]");
    // Locator for make order button
    private final By locatorButtonMakeOrder = By.xpath(".//div[span[text()='Оформить заказ']]");
    // Locator for button to enter account page
    private final By locatorButtonToAccountPage = By.xpath(".//div[span[text()='Личный Кабинет']]");

    private final WebDriver driver;

    public MainPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(BASE_URI);
    }

    private void clickButton(By locatorButton) {
        driver.findElement(locatorButton).click();
    }

    public void clickButtonLogIn() {
        clickButton(locatorButtonLogIn);
    }

    public void clickButtonBuns() {
        clickButton(locatorButtonBuns);
    }

    public void clickButtonFillings() {
        clickButton(locatorButtonFillings);
    }

    public void clickButtonSauces() {
        clickButton(locatorButtonSauces);
    }

    public void clickButtonMakeOrder() {
        clickButton(locatorButtonMakeOrder);
    }

    public void clickButtonToAccountPage() {
        clickButton(locatorButtonToAccountPage);
    }

    public boolean checkThatButtonMakeOrderExists() {
        return driver.findElements(locatorButtonMakeOrder).size() > 0;
    }
}
