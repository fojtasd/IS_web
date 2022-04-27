package com.is_web.entities;

public class Invoice_bought implements Entity {
    Integer id = null;
    String invoice_code;
    int amount;
    int price;
    int companies_id;
    int stb_type_id;
    boolean is_deleted;

    public Invoice_bought(String invoice_code, int amount, int price, int companies_id, int stb_type_id) {
        this.invoice_code = invoice_code;
        this.amount = amount;
        this.price = price;
        this.companies_id = companies_id;
        this.stb_type_id = stb_type_id;
        this.is_deleted = false;
    }

    public Invoice_bought() {
    }

    public String getInvoice_code() {
        return invoice_code;
    }

    public void setInvoice_code(String invoice_code) {
        this.invoice_code = invoice_code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    public int getStb_type_id() {
        return stb_type_id;
    }

    public void setStb_type_id(int stb_type_id) {
        this.stb_type_id = stb_type_id;
    }

    public boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean getIsDeleted() {return is_deleted;};

    @Override
    public String toString() {
        return "Invoice_bought{" +
                "id=" + id +
                ", invoice_code='" + invoice_code + '\'' +
                ", amount='" + amount + '\'' +
                ", price='" + price + '\'' +
                ", companies_id='" + companies_id + '\'' +
                ", stb_type_id='" + stb_type_id + '\'' +
                ", is_deleted='" + is_deleted + '\'' +
                '}';
    }
}
