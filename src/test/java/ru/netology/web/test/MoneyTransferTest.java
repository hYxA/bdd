package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldGetBalanceOfCard1() {
        int expected = 10000;

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashboardPage();
        int cardBalance = dashboardPage.getCardBalance(0);

        assertEquals(expected, cardBalance);
    }

    @Test
    void shouldGetBalanceOfCard2() {
        int expected = 10000;

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashboardPage();
        int cardBalance = dashboardPage.getCardBalance(1);

        assertEquals(expected, cardBalance);
    }

    @Test
    void shouldTransferMoneyFromCard1ToCard2() {
        int amount = 500;
        int cardIndex = 1;

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashboardPage();
        dashboardPage.topUpCardBalance(cardIndex, amount);

        int expected1 = 10000 - amount;
        int expected2 = 10000 + amount;

        int actual1 = dashboardPage.getCardBalance(cardIndex);
        int actual2 = dashboardPage.getCardBalance(1);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

}
