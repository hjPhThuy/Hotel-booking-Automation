package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔥 mở form login
    By openLoginBtn = By.xpath("//button[contains(text(),'Đăng nhập')]");

    // 🔥 form
    By emailInput = By.cssSelector("input[type='email']");
    By passwordInput = By.cssSelector("input[type='password']");

    // 🔥 submit
    By loginBtn = By.xpath("//button[contains(text(),'Đăng Nhập')]");

    public void login(String email, String password) {

        // 👉 STEP 1: mở form
        wait.until(ExpectedConditions.elementToBeClickable(openLoginBtn)).click();

        // 👉 STEP 2: nhập email
        WebElement emailEl = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailEl.clear();
        emailEl.sendKeys(email);

        // 👉 STEP 3: nhập password
        WebElement passEl = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passEl.clear();
        passEl.sendKeys(password);

        // 👉 STEP 4: submit
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }
}