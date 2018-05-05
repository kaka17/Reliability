package com.kaiser.reliability.bean.loanbean;

/**
 * Created by ex-huangkeze001 on 2018/4/19.
 */

public class LoanBean {
    private String id;
    private String name;
    private String money;
    private String Credit;

    public LoanBean() {
    }

    public LoanBean(String id, String name, String money, String credit) {
        this.id = id;
        this.name = name;
        this.money = money;
        Credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }
}
