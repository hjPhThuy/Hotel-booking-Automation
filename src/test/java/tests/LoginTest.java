package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccess() {
        LoginPage login = new LoginPage(driver);

        login.login("pthuy@gmail.com", "123456");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("login"));

        String url = driver.getCurrentUrl();
        System.out.println("URL: " + url);

        Assertions.assertTrue(url.contains("login"));
    }
    }
