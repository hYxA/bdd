package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MoneyTransferTest {

    @Test
    void shouldGetBalanceOfCards() {

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashboardPage();
        int cardBalance1 = dashboardPage.getCardBalance(0);
        int cardBalance2 = dashboardPage.getCardBalance(1);

        assertNotNull(cardBalance1);
        assertNotNull(cardBalance2);
    }

    @Test
    void shouldTransferMoneyToCard1FromCard2() {
        int amount = 5000;

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashboardPage();
        // Запомнить текущий баланс
        int currentBalance[] = {
                dashboardPage.getCardBalance(0),
                dashboardPage.getCardBalance(1)
        };
        dashboardPage.topUpCard1Balance();

        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.topUpCardBalance(amount, 1); // 1 - индекс второй карты

        int expected1 = currentBalance[0] + amount;
        int expected2 = currentBalance[1] - amount;

        int actual1 = dashboardPage.getCardBalance(0);
        int actual2 = dashboardPage.getCardBalance(1);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyToCard2FromCard1() {
        int amount = 5000;

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashboardPage();
        // Запомнить текущий баланс
        int currentBalance[] = {
                dashboardPage.getCardBalance(0),
                dashboardPage.getCardBalance(1)
        };
        dashboardPage.topUpCard2Balance();

        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.topUpCardBalance(amount, 0); // 0 - индекс первой карты

        int expected1 = currentBalance[0] - amount;
        int expected2 = currentBalance[1] + amount;

        int actual1 = dashboardPage.getCardBalance(0);
        int actual2 = dashboardPage.getCardBalance(1);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }


    @Test
    void shouldNotTransferMoneyFromCard1ToCard1() {
        int amount = 500;

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashboardPage();
        dashboardPage.topUpCard1Balance();

        // Подмена данных из другого метода
        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.setAmount(amount);
        moneyTransferPage.setCardFrom(DataHelper.getCardNumber(0));
        moneyTransferPage.errorTransfer();
    }

}
