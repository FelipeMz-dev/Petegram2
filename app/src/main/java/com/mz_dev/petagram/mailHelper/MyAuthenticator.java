package com.mz_dev.petagram.email;

import java.math.BigInteger;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
    private final String username;
    private final String password;

    public MyAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        BigInteger bigInt = new BigInteger(password, 16);
        String textPass = new String(bigInt.toByteArray());
        return new PasswordAuthentication(username, textPass);
    }
}