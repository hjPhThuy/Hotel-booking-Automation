package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

public class BookingTest extends BaseTest {

    @Test
    public void testBookingFlow() {

        // login
        LoginPage login = new LoginPage(driver, wait);
        login.login("pthuy@gmail.com", "123456");

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("login")));

        // search
        HomePage home = new HomePage(driver, wait);
        home.searchRoom("Hồ Chí Minh");

        // click result
        ResultPage result = new ResultPage(driver, wait);
        result.clickFirstResult();

        // booking
        BookingPage booking = new BookingPage(driver, wait);

        // 👉 FIX QUAN TRỌNG (đợi page load)
        booking.waitForPageLoaded();

        booking.fillBookingForm();

        // verify
        Assertions.assertTrue(booking.isBookingSubmitted());
    }
}