package ru.fls.privateoffice.util.stub;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 21.03.12
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ProfilePageStub {
    String lastName="ИВАНОВ";
    String firstName="ИВАН";
    String middleName="ИВАНОВИЧ";
    String sex="муж";
    String birthDay="21.03.1981";
    String mail="ivan_ivanov@firstlinesoftware.com";
    String phone="+7 (911) 111 22 33";
    String address="Cанкт-Петербург, Рентгена 5А";
    String docType= "паспорт РФ";
    String docNumber="123 45 67";
    String issueDate="21.03.1995";
    String endDate="21.03.2015";
    String premiumBals="90";
    String qualifiedBals="92";
    String regDate="21.03.2012";
    String wordCode="кодовоеСлово";
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPremiumBals() {
        return premiumBals;
    }

    public void setPremiumBals(String premiumBals) {
        this.premiumBals = premiumBals;
    }

    public String getQualifiedBals() {
        return qualifiedBals;
    }

    public void setQualifiedBals(String qualifiedBals) {
        this.qualifiedBals = qualifiedBals;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getWordCode() {
        return wordCode;
    }

    public void setWordCode(String wordCode) {
        this.wordCode = wordCode;
    }
}
