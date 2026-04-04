package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {

    WebDriver driver;
    WebDriverWait wait;

    public ResultPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Dùng index [1] để bảo đảm luôn lấy nút chi tiết đầu tiên của trang search,
    // hạn chế click nhầm
    // (Nếu HTML web có class bọc khách sạn ví dụ như <div class="hotel-item"> thì
    // thêm vào trước //button)
    private By viewDetailBtn = By.xpath("(//button[contains(text(),'Xem chi tiết')])[1]");

    public void clickFirstResult() {

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(viewDetailBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }
}