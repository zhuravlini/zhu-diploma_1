package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.SQL;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        var statusExpected = getValidCardStatus();
        var statusActual = getCardStatusForCreditRequest();
        assertEquals(statusExpected, statusActual);

        var expectedAmount = "4500000";
        var actualAmount = getAmountPayment();
        assertEquals(expectedAmount, actualAmount);

        var expectedId = getTransactionId();
        var actualId = getPaymentId();
        assertNotNull(actualId);
        assertNotNull(expectedId);
        assertEquals(expectedId, actualId);
    }

    @Test
    public void failResultIfDeclinedCardBuyFormOnCredit() {
        var cardData = getCorrectlyFormForDeclinedCardForm();
        paymentPage.completedPaymentForm(cardData);
        paymentPage.waitError();

        var statusExpected = getInvalidCardStatus();
        var statusActual = getCardStatusForCreditRequest();
        assertEquals(statusExpected, statusActual);

        var expectedId = getBankId();
        var actualId = getPaymentId();
        assertNotNull(expectedId);
        assertNotNull(actualId);
        assertEquals(expectedId, actualId);
    }
}