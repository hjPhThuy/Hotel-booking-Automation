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

        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(searchBtn));

        WebElement btn = driver.findElement(searchBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
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