package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final SelenideElement amount = $("[data-test-id=amount]");
    private final SelenideElement from = $("[data-test-id=from]");
    private final SelenideElement actionTransfer = $("[data-test-id=action-transfer]");
    private final ElementsCollection deposit = $$("[data-test-id=action-deposit]");
    private final ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
        // убеждаемся, что перешли на третью страницу
    }

    public int getCardBalance(int cardIndex) {
        val text = cards.get(cardIndex).text();
        return extractBalance(text);
    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void topUpCardBalance(int cardIndex, int amount) {
        int cardFrom;
        if (cardIndex == 0) { cardFrom = 1; } else { cardFrom = 0; }

        deposit.get(cardIndex).click();
        $("[data-test-id=amount]").click();
        $("[data-test-id=amount]").sendKeys("500");
        from.click();
        from.sendKeys(DataHelper.getCardNumber(cardFrom));
        actionTransfer.click();
    }


}
