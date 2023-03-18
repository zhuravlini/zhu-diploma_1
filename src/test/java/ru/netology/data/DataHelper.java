package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

@NoArgsConstructor
public class DataHelper {

    public void DataHelper() {
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    @Value
    public static class CardData {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static String getFieldEmpty() {

        return " ";
    }

    //номер карты

    public static String getApprovedNumberCard() {

        return "4444 4444 4444 4441";
    }

    public static CardData getCorrectlyFormForApprovedCardForm() {

        return new CardData(getApprovedNumberCard(), getValidMonth(), getValidYear(), getRandomName(), getValidCVC());
    }

    public static String getDeclinedNumberCard() {

        return "4444 4444 4444 4442";
    }

    public static CardData getCorrectlyFormForDeclinedCardForm() {

        return new CardData(getDeclinedNumberCard(), getValidMonth(), getValidYear(), getRandomName(), getValidCVC());
    }

    public static String getValidCardStatus() {

        return "APPROVED";

    }

    public static String getInvalidCardStatus() {

        return "DECLINED";

    }

    public static CardData getFormWithAllEmptyFields() {

        return new CardData(getFieldEmpty(), getFieldEmpty(), getFieldEmpty(), getFieldEmpty(), getFieldEmpty());
    }

    public static String getInValidNumberCardWithDeficientNumbers() {

        return "4444 4444 4444 444";
    }

    public static CardData getFormWithInValidNumberCardWithDeficientNumbers() {

        return new CardData(getInValidNumberCardWithDeficientNumbers(), getValidMonth(), getValidYear(), getRandomName(), getValidCVC());
    }

    //поле месяц

    public static String getValidMonth() {

        var months = new String[]{
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return months[new Random().nextInt(months.length)];

    }

    public static String getInValidMonthEqualsToNoExistentMonth() {

        return String.valueOf(faker.number().numberBetween(13, 99));
    }

    public static CardData getFormWithInvalidMonthEqualsToNoExistentMonth() {

        return new CardData(getApprovedNumberCard(), getInValidMonthEqualsToNoExistentMonth(), getValidYear(), getRandomName(), getValidCVC());
    }

    public static String getInvalidMonthEqualsToOneNumber() {

        return String.valueOf(faker.number().numberBetween(0, 9));
    }

    public static CardData getFormWithIncorrectFieldMonthEqualsOneNumber() {

        return new CardData(getApprovedNumberCard(), getInvalidMonthEqualsToOneNumber(), getValidYear(), getRandomName(), getValidCVC());
    }

    public static String getInValidMonthEqualsTo00() {

        return "00";
    }

    public static CardData getFormWithIncorrectMonthEqualTo00() {

        return new CardData(getApprovedNumberCard(), getInValidMonthEqualsTo00(), getValidYear(), getRandomName(), getValidCVC());
    }

    //поле год
    public static String getValidYear() {

        return String.valueOf(faker.number().numberBetween(23, 28));

    }

    public static String getInvalidYearEqualsToExpiredYear() {

        return String.valueOf(faker.number().numberBetween(15, 22));
    }

    public static CardData getFormWithExpiredYear() {

        return new CardData(getApprovedNumberCard(), getValidMonth(), getInvalidYearEqualsToExpiredYear(), getRandomName(), getValidCVC());
    }

    public static String getInValidYear() {

        return String.valueOf(faker.number().numberBetween(29, 99));
    }

    public static CardData getFormWithInvalidYear() {

        return new CardData(getApprovedNumberCard(), getValidMonth(), getInValidYear(), getRandomName(), getValidCVC());

    }

    public static String getInvalidYearEqualToOneNumber() {

        return String.valueOf(faker.number().numberBetween(0, 9));

    }

    public static CardData getFormWithIncorrectFieldYearEqualsToOneNumber() {

        return new CardData(getApprovedNumberCard(), getValidMonth(), getInvalidYearEqualToOneNumber(), getRandomName(), getValidCVC());
    }


    //поле владелец

    public static String getRandomName() {

        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getInvalidHolderNameEqualsToNumbers() {

        return String.valueOf(faker.number().numberBetween(1000, 5000));

    }

    public static CardData getFormWithHolderNameEqualsToNumbers() {

        return new CardData(getApprovedNumberCard(), getValidMonth(), getValidYear(), getInvalidHolderNameEqualsToNumbers(), getValidCVC());

    }

    //поле CVC

    public static String getValidCVC() {

        return String.valueOf(faker.number().numberBetween(100, 999));
    }

    public static String getInvalidCVCEqualToTwoNumber() {

        return String.valueOf(faker.number().numberBetween(11, 99));
    }

    public static CardData getFormWithCVCEqualToTwoNumber() {

        return new CardData(getApprovedNumberCard(), getValidMonth(), getValidYear(), getRandomName(), getInvalidCVCEqualToTwoNumber());
    }

}