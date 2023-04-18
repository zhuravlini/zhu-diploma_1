package ru.netology.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQL.*;

public class CreditPositiveTest extends TestBaseUI {
    private PaymentPage paymentPage = new PaymentPage();
    private MainPage mainPage = new MainPage();

    @BeforeEach
    public void clickBuy() {

        mainPage.clickBuyOnCredit();
    }

    @Test
    public void successResultIfApprovedCardsBuyFormOnCredit() {
        var cardData = getCorrectlyFormForApprovedCardForm();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitSuccessResult();
        var creditRequestEntity = getCardDataForCreditRequest();

        var statusExpected = getValidCardStatus();
        var statusActual = creditRequestEntity.getStatus();
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void failResultIfDeclinedCardBuyFormOnCredit() {
        var cardData = getCorrectlyFormForDeclinedCardForm();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitError();
        var creditRequestEntity = getCardDataForCreditRequest();

        var statusExpected = getInvalidCardStatus();
        var statusActual = creditRequestEntity.getStatus();
        assertEquals(statusExpected, statusActual);

    }
}