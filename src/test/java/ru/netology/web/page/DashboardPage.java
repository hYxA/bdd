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
        deposit.get(cardIndex).click(); // Нажатие "Пополнить" у соответствующей карты

    }


}
