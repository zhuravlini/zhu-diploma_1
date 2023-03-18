package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.API.buyForm;
import static ru.netology.data.API.creditForm;
import static ru.netology.data.DataHelper.getCorrectlyFormForApprovedCardForm;
import static ru.netology.data.DataHelper.getCorrectlyFormForDeclinedCardForm;

public class TestApi {

    @Test
    void approvedCardBuyForm() {
        val validApprovedCard = getCorrectlyFormForApprovedCardForm();
        val status = buyForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void declinedCardBuyForm() {
        val validDeclinedCard = getCorrectlyFormForDeclinedCardForm();
        val status = buyForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void approvedCardCreditForm() {
        val validApprovedCard = getCorrectlyFormForApprovedCardForm();
        val status = creditForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void declinedCardCreditForm() {
        val validDeclinedCard = getCorrectlyFormForDeclinedCardForm();
        val status = creditForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }
}
