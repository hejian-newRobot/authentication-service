package com.service.auth.entity;

/**
 * 用户信息表
 */
public class User {

    private long id;
    private String jobId;
    private String psd;
    private String userName;
    private String tPhone;
    private String userStatus;
    private long userTypeIdBySysD;
    private long sexIdBySysD;
    private java.sql.Timestamp dateOnBoard;
    private java.sql.Timestamp termDate;
    private java.sql.Timestamp healthCertificateStartDate;
    private java.sql.Timestamp healthCertificateEndDate;
    private String headPicUrl;
    private String inTheAddress;
    private String contacts;
    private String contactsPhone;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }


    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getTPhone() {
        return tPhone;
    }

    public void setTPhone(String tPhone) {
        this.tPhone = tPhone;
    }


    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }


    public long getUserTypeIdBySysD() {
        return userTypeIdBySysD;
    }

    public void setUserTypeIdBySysD(long userTypeIdBySysD) {
        this.userTypeIdBySysD = userTypeIdBySysD;
    }


    public long getSexIdBySysD() {
        return sexIdBySysD;
    }

    public void setSexIdBySysD(long sexIdBySysD) {
        this.sexIdBySysD = sexIdBySysD;
    }


    public java.sql.Timestamp getDateOnBoard() {
        return dateOnBoard;
    }

    public void setDateOnBoard(java.sql.Timestamp dateOnBoard) {
        this.dateOnBoard = dateOnBoard;
    }


    public java.sql.Timestamp getTermDate() {
        return termDate;
    }

    public void setTermDate(java.sql.Timestamp termDate) {
        this.termDate = termDate;
    }


    public java.sql.Timestamp getHealthCertificateStartDate() {
        return healthCertificateStartDate;
    }

    public void setHealthCertificateStartDate(java.sql.Timestamp healthCertificateStartDate) {
        this.healthCertificateStartDate = healthCertificateStartDate;
    }


    public java.sql.Timestamp getHealthCertificateEndDate() {
        return healthCertificateEndDate;
    }

    public void setHealthCertificateEndDate(java.sql.Timestamp healthCertificateEndDate) {
        this.healthCertificateEndDate = healthCertificateEndDate;
    }


    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }


    public String getInTheAddress() {
        return inTheAddress;
    }

    public void setInTheAddress(String inTheAddress) {
        this.inTheAddress = inTheAddress;
    }


    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }


    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

}
