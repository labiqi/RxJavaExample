package com.example.lcq.myapp.mode;


public class HighFinanceInfo {
    private String prodCode;
    private String prodNme;
    private String investType;

    public String getProdCode() {
        return prodCode;
    }

    public String getProdNme() {
        return prodNme;
    }

    public String getInvestType() {
        return investType;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public void setProdNme(String prodNme) {
        this.prodNme = prodNme;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    private int typeCode;
}
