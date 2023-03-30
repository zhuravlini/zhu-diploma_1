package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.DataHelper.*;

public class PaymentNegativeTest extends TestBaseUI {

    private MainPage mainPage = new MainPage();
    private PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    public void clickBuy() {

        mainPage.clickBuy();
    }

    @Test
    public void testWithEmptyAllFieldsPayByCard() {
        var cardData = getFormWithAllEmptyFields();
//        paymentPage.completedPaymentForm(cardData);
//        final ElementsCollection emptyField = $$(".input__sub");
//        final SelenideElement fieldNumberCard = emptyField.get(1);
//        final SelenideElement fieldMonth = emptyField.get(2);
//        final SelenideElement fieldYear = emptyField.get(3);
//        final SelenideElement fieldHolder = emptyField.get(4);
//        final SelenideElement fieldCvc = emptyField.get(5);
//        fieldNumberCard.shouldHave(Condition.text("Неверный формат"));
//        fieldMonth.shouldHave(Condition.text("Неверный формат"));
//        fieldYear.shouldBe(Condition.text("Неверный формат"));
//        fieldHolder.shouldBe(Condition.text("Поле обязательно для заполнения"));
//        fieldCvc.shouldBe(Condition.text("Неверный формат"));
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitIncorrectFormat();
    }

    @Test
    public void testWithDeficientNumbersCardPayByCard() {
        var cardData = getFormWithInValidNumberCardWithDeficientNumbers();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitIncorrectFormat();
    }


    @Test
    public void testZeroMonthPayByCard() {
        var cardData = getFormWithIncorrectMonthEqualTo00();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitInvalidCardExpirationDate();
    }

    @Test
    public void testWithNonexistentMonthPayByCard() {
        var cardData = getFormWithInvalidMonthEqualsToNoExistentMonth();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitInvalidCardExpirationDate();
    }

    @Test
    public void testWithIncorrectMonthEqualsOneNumberPayByCard() {
        var cardData = getFormWithIncorrectFieldMonthEqualsOneNumber();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitIncorrectFormat();
    }

    @Test
    public void testWithExpiredYearPayByCard() {
        var cardData = getFormWithExpiredYear();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitCardExpired();
    }

    @Test
    public void testWithInvalidFillingYearPayByCard() {
        var cardData = getFormWithInvalidYear();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitInvalidCardExpirationDate();
    }

    @Test
    public void testWithIncorrectFillingYearPayByCard() {
        var cardData = getFormWithIncorrectFieldYearEqualsToOneNumber();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitIncorrectFormat();
    }

    @Test
    public void testWithNumberInFieldHolderPayByCard() {
        var cardData = getFormWithHolderNameEqualsToNumbers();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitIncorrectFormat();
    }

    @Test

    public void shouldCardWithIncompleteCVCEqualToTwoNumberByCard() {
        var cardData = getFormWithCVCEqualToTwoNumber();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitIncorrectFormat();
    }

}
