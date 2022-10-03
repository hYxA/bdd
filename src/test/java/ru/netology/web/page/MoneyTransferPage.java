package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MoneyTransferPage {
    private final SelenideElement amount = $("[data-test-id=amount]");
    private final SelenideElement amountTransfer = $$(".input__control").get(0);
    private final SelenideElement cardFrom = $$(".input__control").get(1);
    private final SelenideElement actionTransfer = $("[data-test-id=action-transfer]");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");


    public MoneyTransferPage() {
        amount.shouldBe(visible);
    }

    public void errorTransfer() {
        actionTransfer.click();
        errorNotification.shouldBe(visible);
    }

    public void setAmount(int sum) {
        amountTransfer.setValue(String.valueOf(sum));
    }

    public void setCardFrom(String cardNumber) {
        cardFrom.setValue(cardNumber);
    }

    public void topUpCardBalance(int amount, int index) {
        amountTransfer.sendKeys(String.valueOf(amount)); // Ввод суммы перевода
        cardFrom.sendKeys(String.valueOf(DataHelper.getCardNumber(index))); // Ввод номера карты, с которой произвести транзакцию
        actionTransfer.click(); // Кнопка пополнить
    }
}
