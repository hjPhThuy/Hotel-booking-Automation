package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;
import pages.LoginPage;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchRoom() {

        // 👉 login (reuse lại)
        LoginPage login = new LoginPage(driver, wait);
        login.login("pthuy@gmail.com", "123456");

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("login")
        ));

        // 👉 search
        HomePage home = new HomePage(driver, wait);
        home.searchRoom("Hồ Chí Minh");

        // 👉 verify result
        Assertions.assertTrue(home.isResultListDisplayed());
    }
}