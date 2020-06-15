package com.wayqui.qualified.io;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MaskifyTest {

    private static final String[] NUMBERS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final int CREDIT_CARD_MIN_SIZE = 6;
    private static final String POUND_KEY = "#";

    @Test
    public void testing() {
        assertThat(MaskifyTest.maskify("3435464576856856"))
                .isEqualTo("3###########6856");

        assertThat(MaskifyTest.maskify("12345"))
                .isEqualTo("12345");

        assertThat(MaskifyTest.maskify("4556-3646-0793-5616"))
                .isEqualTo("4###-####-####-5616");

    }

    /**
     * This function "encrypts" a credit card number with a certain criteria
     * @param creditCardNumber the credit card number to be encrypted
     * @return the encrypted ("maskified") credit card
     */
    public static String maskify(String creditCardNumber) {
        if (creditCardNumber == null || creditCardNumber.isEmpty())
            return creditCardNumber;

        if (creditCardNumber.length() < CREDIT_CARD_MIN_SIZE)
            return creditCardNumber;

        return encryptCreditCard(creditCardNumber);
    }

    private static String encryptCreditCard(String creditCardNumber) {
        StringBuilder maskedCreditCard = new StringBuilder(creditCardNumber);

        for (int i = 0; i < creditCardNumber.length(); i++) {
            if (isADigit(creditCardNumber.charAt(i))) {
                if (i > 0 && i < creditCardNumber.length() - 4) {
                    maskedCreditCard.replace(i, i+1, POUND_KEY);
                }
            }
        }

        return maskedCreditCard.toString();
    }

    private static boolean isADigit(char charAt) {
        return Arrays.asList(NUMBERS).contains(String.valueOf(charAt));
    }
}
