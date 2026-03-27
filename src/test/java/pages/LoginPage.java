package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    private By openLoginBtn = By.xpath("//button[contains(text(),'Đăng nhập')]");
    private By emailInput = By.cssSelector("input[type='email']");
    private By passwordInput = By.cssSelector("input[type='password']");
    private By loginBtn = By.xpath("//button[contains(text(),'Đăng Nhập')]");

    // Action
    public void login(String email, String password) {

        wait.until(ExpectedConditions.elementToBeClickable(openLoginBtn)).click();

        WebElement emailEl = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailEl.clear();
        emailEl.sendKeys(email);

        WebElement passEl = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passEl.clear();
        passEl.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }
}