package com.vico.verify.dao;

public interface ClientDao {
    int insertSourceNumber(String code);

    String selectSourceNumber();
    
    String selectEncryptedNumber();
}