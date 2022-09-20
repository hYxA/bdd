package ru.netology.web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransferPage;
import ru.netology.web.page.VerificationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static MoneyTransferPage moneyTransferPage;
    private static DataHelper dataHelper;

    @Пусть("открыта страница с формой авторизации {string}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LoginPage.class);
    }

    @Пусть("пользователь залогинен с именем {string} и паролем {string};")
    public void openAuthPage2(String login, String password) {
        loginPage.validLogin2(login, password);
    }


    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту с главной страницы;")
    public void moneyTransfer(String amount, String cardNumber, String indexCard) {
        int i = DataHelper.getIndexCardByNumber(cardNumber);
        if (i == 0) {
            dashboardPage.topUpCard1Balance();
            moneyTransferPage.topUpCard1Balance(Integer.parseInt(amount));
        }
        if (i == 1) {
            dashboardPage.topUpCard2Balance();
            moneyTransferPage.topUpCard2Balance(Integer.parseInt(amount));
        }
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(DataHelper.AuthInfo authInfo) {
        verificationPage = verificationPage.validVerify(DataHelper.getVerificationCodeFor(authInfo));
    }

    @Тогда("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
    public void verifyDashboardPage() {
        dashboardPage.dashboardPage();
    }

    @Тогда("появляется ошибка о неверном коде проверки")
    public void verifyCodeIsInvalid() {
        verificationPage.VerifyCodeIsInvalid();
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей.")
    public void totalBalance(String indexCard, String balance) {
        int intIndexCard = Integer.parseInt(indexCard) - 1; // индекс в массиве на 1 меньше
        int actual = dashboardPage.getCardBalance(intIndexCard);
        int expected = Integer.parseInt(balance);
        assertEquals(expected, actual);
    }



}