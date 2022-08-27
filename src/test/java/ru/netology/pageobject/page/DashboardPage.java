package ru.netology.pageobject.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");

    private SelenideElement buttonOneBill = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement buttonTwoBill = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");

    public DashboardPage() {

        heading.shouldBe(visible);
    }

    public MoneyPage oneBill() {
        buttonOneBill.click();
        return new MoneyPage();
    }

    public MoneyPage twoBill() {
        buttonTwoBill.click();
        return new MoneyPage();
    }

    public int getOneCardBalance() {
        val text = cards.first().text();
        return showBalance(text);
    }

    public int getTwoCardBalance() {
        val text = cards.last().text();
        return showBalance(text);
    }

    private int showBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
