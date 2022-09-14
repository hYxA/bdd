package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransferPage;

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
        // Запомнить текщий баланс
        int currentBalance[] = {
                dashboardPage.getCardBalance(0),
                dashboardPage.getCardBalance(1)
        };
        dashboardPage.topUpCardBalance(cardIndex);

        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.topUpCardBalance(cardIndex, amount);

        int expected1 = currentBalance[0] - amount;
        int expected2 = currentBalance[1] + amount;

        int actual1 = dashboardPage.getCardBalance(0);
        int actual2 = dashboardPage.getCardBalance(cardIndex);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

}
