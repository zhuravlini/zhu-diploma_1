package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private final SelenideElement fieldNumberCard = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement fieldMonth = $("input[placeholder='08']");
    private final SelenideElement fieldYear = $("input[placeholder='22']");
    private final SelenideElement fieldHolder = $(withText("Владелец")).parent().$("input");
    private final SelenideElement fieldCvc = $("input[placeholder='999']");
    private final SelenideElement buttonContinue = $x("//span[text()=\"Продолжить\"]");
    private final SelenideElement successNotification = $(withText("Операция одобрена Банком."));
    private final SelenideElement errorNotification = $(withText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement invalidCardExpirationDate = $(withText("Неверно указан срок действия карты"));
    private final SelenideElement incorrectFormat = $(withText("Неверный формат"));
    private final SelenideElement cardExpired = $(withText("Истёк срок действия карты"));

    public void completedPaymentForm(DataHelper.CardData cardData) {
        fieldNumberCard.setValue(cardData.getNumber());
        fieldMonth.setValue(cardData.getMonth());
        fieldYear.setValue(cardData.getYear());
        fieldHolder.setValue(cardData.getHolder());
        fieldCvc.setValue(cardData.getCvc());
        buttonContinue.click();
    }

    public void waitSuccessResult() {

        successNotification.waitUntil(Condition.visible, 12000);
    }

    public void waitError() {

        errorNotification.waitUntil(Condition.visible, 12000);
    }

    public void waitInvalidCardExpirationDate() {

        invalidCardExpirationDate.waitUntil(Condition.visible, 12000);
    }

    public void waitIncorrectFormat() {

        incorrectFormat.waitUntil(Condition.visible, 12000);
    }

    public void waitCardExpired() {

        cardExpired.waitUntil(Condition.visible, 12000);
    }

}