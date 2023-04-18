package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQL.*;

public class PaymentPositiveTest extends TestBaseUI {
    private PaymentPage paymentPage = new PaymentPage();
    private MainPage mainPage = new MainPage();

    @BeforeEach
    public void clickBuy() {

        mainPage.clickBuy();
    }

    @Test
    public void successResultIfApprovedCardsBuyForm() {
        var cardData = getCorrectlyFormForApprovedCardForm();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitSuccessResult();
        var paymentEntity = getCardDataForPayment();

        var statusExpected = getValidCardStatus();
        var statusActual = paymentEntity.getStatus();
        assertEquals(statusExpected, statusActual);

        var expectedAmount = "4500000";
        var actualAmount = paymentEntity.getAmount();
        assertEquals(expectedAmount, actualAmount);

    }

    @Test
    public void failResultIfDeclinedCardBuyForm() {
        var cardData = getCorrectlyFormForDeclinedCardForm();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitError();
        var paymentEntity = getCardDataForPayment();

        var statusExpected = getInvalidCardStatus();
        var statusActual = paymentEntity.getStatus();
        assertEquals(statusExpected, statusActual);

    }
}