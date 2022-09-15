package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MoneyTransferPage {
    private final SelenideElement amount = $("[data-test-id=amount]");
    private final SelenideElement amountTransfer = $$(".input__control").get(0);
    private final SelenideElement from = $$(".input__control").get(1);
    private final SelenideElement actionTransfer = $("[data-test-id=action-transfer]");


    public MoneyTransferPage() {
        amount.shouldBe(Condition.visible);
    }

    public void topUpCard1Balance(int amount) {
        amountTransfer.sendKeys(String.valueOf(amount)); // Ввод суммы перевода
        from.sendKeys(String.valueOf(DataHelper.getCard2Number())); // Ввод номера карты, с которой произвести транзакцию
        actionTransfer.click(); // Кнопка пополнить
    }

    public void topUpCard2Balance(int amount) {
        amountTransfer.sendKeys(String.valueOf(amount)); // Ввод суммы перевода
        from.sendKeys(String.valueOf(DataHelper.getCard1Number())); // Ввод номера карты, с которой произвести транзакцию
        actionTransfer.click(); // Кнопка пополнить
    }

}
