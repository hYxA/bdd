package ru.netology.web.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class CardInfo {
        String cardNumber;
    }

    public static String getCardNumber(int index) {
        String[] cardNumber = {"5559 0000 0000 0001", "5559 0000 0000 0002"};
        return cardNumber[index];
    }


    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
