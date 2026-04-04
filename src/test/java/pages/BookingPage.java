package pages;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingPage {

    WebDriver driver;
    WebDriverWait wait;

    public BookingPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ===== LOCATORS =====
    private By checkInDate = By.xpath("(//input[@type='date'])[1]");
    private By checkOutDate = By.xpath("(//input[@type='date'])[2]");
    private By numberInput = By.xpath("//input[@type='number']");
    private By nameInput = By.xpath("//input[@placeholder='Họ tên']");
    private By emailInput = By.xpath("//input[@placeholder='Email']");
    private By phoneInput = By.xpath("//input[@placeholder='Số điện thoại']");
    private By cashOption = By.xpath("//input[@value='cash']");
    private By bookBtn = By.xpath("//button[@type='submit' and contains(text(),'Đặt phòng')]");

    // ===== WAIT PAGE LOAD =====
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkInDate));
    }

    // ===== ACTION =====
    public void fillBookingForm() {

        // Check-in & Check-out Date (Sử dụng JavascriptExecutor để bypass việc input
        // type="date" chặn đánh máy)
        WebElement checkIn = wait.until(ExpectedConditions.visibilityOfElementLocated(checkInDate));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='2026-04-20';", checkIn);

        WebElement checkOut = driver.findElement(checkOutDate);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='2026-04-25';", checkOut);

        // Quantity (Số người / Số phòng)
        WebElement quantity = driver.findElement(numberInput);
        quantity.clear();
        quantity.sendKeys("2");

        // info (Có thể Form ẩn các trường này khi User đã Login)
        // Chúng ta nên dùng findElements thay vì wait.until() để tránh lỗi Timeout 10s nếu ô nhập biến mất
        List<WebElement> names = driver.findElements(nameInput);
        if (!names.isEmpty() && names.get(0).isDisplayed()) {
            names.get(0).sendKeys("Test User");
        }

        List<WebElement> emails = driver.findElements(emailInput);
        if (!emails.isEmpty() && emails.get(0).isDisplayed()) {
            emails.get(0).sendKeys("test@gmail.com");
        }

        List<WebElement> phones = driver.findElements(phoneInput);
        if (!phones.isEmpty() && phones.get(0).isDisplayed()) {
            phones.get(0).sendKeys("0123456789");
        }

        // payment
        WebElement cash = wait.until(ExpectedConditions.elementToBeClickable(cashOption));
        if (!cash.isSelected()) {
            cash.click();
        }

        // submit
        WebElement btn = driver.findElement(bookBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    // ===== VERIFY =====
    public boolean isBookingSubmitted() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("booking"),
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[contains(text(),'thành công')]")))) != null;
        } catch (Exception e) {
            return false;
        }
    }
}