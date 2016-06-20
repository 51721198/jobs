package com.vico.verify.pojo;

public class Client {
    private String encryptedNumber;

    private String sourceNumber;

    public String getEncryptedNumber() {
        return encryptedNumber;
    }

    public void setEncryptedNumber(String encryptedNumber) {
        this.encryptedNumber = encryptedNumber == null ? null : encryptedNumber.trim();
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber == null ? null : sourceNumber.trim();
    }
}