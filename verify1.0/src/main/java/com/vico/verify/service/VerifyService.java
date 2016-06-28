package com.vico.verify.service;

public interface VerifyService {

	public String sourceNumber();

	public boolean checkDate(String code);

	public void insertCode(String code);

	public boolean checkSourceNUmber(String sourcenumber);

	public boolean checkComID(String code);

}
