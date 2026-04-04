package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // 🔥 Locators
    private By destinationInput = By.id("destinationInput");
    private By searchBtn = By.xpath("//button[@type='submit']");

    // 👉 list result (nút "Xem chi tiết")
    private By resultItems = By.xpath("//button[contains(text(),'Xem chi tiết')]");

    // 🔥 Action: search
    public void searchRoom(String location) {

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(destinationInput));
        input.clear();
        input.sendKeys(location);

        // Bấm enter để chọn suggestion, việc này có thể làm DOM re-render
        // Tùy theo thiết kế Frontend, phím ENTER có thể ĐÃ KÍCH HOẠT luôn việc tìm kiếm (auto-submit form).
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);

        // Đợi một khoảng nhỏ để DOM ổn định
        try { Thread.sleep(1000); } catch(Exception e){}

        // Thêm Try-Catch: Nếu sau khi bấm ENTER trang tự auto-navigate luôn thì nút Search sẽ không còn
        // nên nó sẽ quăng TimeoutException. Ta gom nó vào Catch để test vẫn chạy trơn tru qua bước kế tiếp.
        try {
            // Giảm timeout cục bộ ở đây để khỏi chờ 10s nếu đã nhảy trang
            WebDriverWait shortWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(2));
            WebElement btn = shortWait.until(ExpectedConditions.elementToBeClickable(searchBtn));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        } catch (TimeoutException e) {
            // Đã nhảy trang hoặc component search đã ẩn -> Bỏ qua click
        }
    }

    // VERIFY LIST RESULT (CHUẨN QA)
    public boolean isResultListDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultItems));

            List<WebElement> results = driver.findElements(resultItems);

            return results.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}