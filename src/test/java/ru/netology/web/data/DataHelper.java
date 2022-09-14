package ru.netology.web.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static String getCardNumber(int index) {
        String cardNumber[] = {"5559 0000 0000 0001",
                "5559 0000 0000 0002"};
        return cardNumber[index];
    }

    public static String getOtherCardNumber(int index) {
        int cardFrom;
        if (index == 0) {cardFrom = 1; } else {cardFrom = 0; }
        String cardNumber[] = {"5559 0000 0000 0001",
                "5559 0000 0000 0002"};
        return cardNumber[cardFrom];
    }

        @Value
        public static class VerificationCode {
            private String code;
        }

        public static VerificationCode getVerificationCodeFor (AuthInfo authInfo){
            return new VerificationCode("12345");
        }
    }
