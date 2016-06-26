package com.vico.license.pojo;

public class LicenseDetail {
    private Integer serialNumberId;

    private String sourceNumber;

    private String createDay;

    private String expiredDate;

    private String encryptedNumber;

    private Integer expiredFlag;

    private Integer validDays;

    private Integer hospitalNumber;
    
    private String hospitalName;
   

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber == null ? null : sourceNumber.trim();
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay == null ? null : createDay.trim();
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate == null ? null : expiredDate.trim();
    }

    public String getEncryptedNumber() {
        return encryptedNumber;
    }

    public void setEncryptedNumber(String encryptedNumber) {
        this.encryptedNumber = encryptedNumber == null ? null : encryptedNumber.trim();
    }

    public Integer getExpiredFlag() {
        return expiredFlag;
    }

    public void setExpiredFlag(Integer expiredFlag) {
        this.expiredFlag = expiredFlag;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Integer getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(Integer hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }
}