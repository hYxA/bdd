package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection deposit1 = $$("[data-test-id=action-deposit]").first(1);
    private final ElementsCollection deposit2 = $$("[data-test-id=action-deposit]").last(1);
    private final ElementsCollection cards = $$(".list__item");

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
        // убеждаемся, что перешли на третью страницу
    }

    public void dashboardPage() {
        heading.shouldBe(Condition.visible);
        // убеждаемся, что перешли на третью страницу
    }

    public int getCardBalance(int cardIndex) {
        val text = cards.get(cardIndex).text();
        return extractBalance(text);
    }
    private int extractBalance(String text) {
        String balanceStart = "баланс: ";
        String balanceFinish = " р.";

        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void topUpCard1Balance() {
        deposit1.get(0).click(); // Нажатие "Пополнить" у соответствующей карты
    }

    public void topUpCard2Balance() {
        deposit2.get(0).click(); // Нажатие "Пополнить" у соответствующей карты
    }


}
