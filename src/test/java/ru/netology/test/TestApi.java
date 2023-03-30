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
        var validApprovedCard = getCorrectlyFormForApprovedCardForm();
        var status = buyForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void declinedCardBuyForm() {
        var validDeclinedCard = getCorrectlyFormForDeclinedCardForm();
        var status = buyForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void approvedCardCreditForm() {
        var validApprovedCard = getCorrectlyFormForApprovedCardForm();
        var status = creditForm(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void declinedCardCreditForm() {
        var validDeclinedCard = getCorrectlyFormForDeclinedCardForm();
        var status = creditForm(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }
}
