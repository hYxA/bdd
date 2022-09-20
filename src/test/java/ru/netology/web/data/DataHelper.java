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

    public static CardInfo getCard1Number() { return new CardInfo("5559 0000 0000 0001"); }

    public static CardInfo getCard2Number() {
        return new CardInfo("5559 0000 0000 0002");
    }

    public static int getIndexCardByNumber(String cardNumber) {
        int i = 9;
        if (cardNumber.equals("5559 0000 0000 0002")) {i = 1;}
        if (cardNumber.equals("5559 0000 0000 0001")) {i = 0;}
        return i;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }
}
