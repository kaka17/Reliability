package com.kaiser.reliability.load.bena;


/**
 * Created by ex-huangkeze001 on 2018/5/6.
 */

public class Users{
    private String onlineId;
    private String id;
    private String mobile;
    private String createTime;
    private String modifyTime;
    private String userName;
    private String idCard;
    private String jobTime;
    private String nowAddress;
    private String homeAddress;
    private String houseAddress;
    private String isCredit;
    private String isLoan;
    private String isOverDue;
    private String isMarry;
    private String familyMembers;
    private String salary;
    private String education;
    private String isSubmit;

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }



    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(String isCredit) {
        this.isCredit = isCredit;
    }

    public String getIsLoan() {
        return isLoan;
    }

    public void setIsLoan(String isLoan) {
        this.isLoan = isLoan;
    }

    public String getIsOverDue() {
        return isOverDue;
    }

    public void setIsOverDue(String isOverDue) {
        this.isOverDue = isOverDue;
    }

    public String getIsMarry() {
        return isMarry;
    }

    public void setIsMarry(String isMarry) {
        this.isMarry = isMarry;
    }

    public String getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(String familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOnlineId() {
        return onlineId;
    }

    public void setOnlineId(String onlineId) {
        this.onlineId = onlineId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "onlineId='" + onlineId + '\'' +
                ", id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", userName='" + userName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", jobTime='" + jobTime + '\'' +
                ", newAddress='" + nowAddress + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", houseAddress='" + houseAddress + '\'' +
                ", isCredit='" + isCredit + '\'' +
                ", isLoad='" + isLoan + '\'' +
                ", isOverDur='" + isOverDue + '\'' +
                ", isMarry='" + isMarry + '\'' +
                ", familymembers='" + familyMembers + '\'' +
                ", salary='" + salary + '\'' +
                ", education='" + education + '\'' +
                ", isSubmit='" + isSubmit + '\'' +
                '}';
    }
}
