package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Value;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

@Value
@AllArgsConstructor
public class MainPage {
    private final SelenideElement buyField = $(withText("Купить"));
    private final SelenideElement buyOnCreditField = $(withText("Купить в кредит"));

    public void clickBuy() {

        buyField.click();
    }

    public void clickBuyOnCredit() {

        buyOnCreditField.click();
    }

}