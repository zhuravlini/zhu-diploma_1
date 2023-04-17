package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.SQL;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

//        var expectedAmount = "4500000";
//        var actualAmount = getAmountPayment();
//        assertEquals(expectedAmount, actualAmount);
//
//        var expectedId = getTransactionId();
//        var actualId = getPaymentId();
//        assertNotNull(actualId);
//        assertNotNull(expectedId);
//        assertEquals(expectedId, actualId);
    }

//    @Test
//    public void failResultIfDeclinedCardBuyForm() {
//        var cardData = getCorrectlyFormForDeclinedCardForm();
//        paymentPage.completedPaymentForm(cardData);
//        paymentPage.waitError();
//
//        var statusExpected = getInvalidCardStatus();
//        var statusActual = getCardDataForPayment();
//        assertEquals(statusExpected, statusActual);
//
////        var expectedId = getBankId();
////        var actualId = getPaymentId();
////        assertNotNull(expectedId);
////        assertNotNull(actualId);
////        assertEquals(expectedId, actualId);
//    }
}