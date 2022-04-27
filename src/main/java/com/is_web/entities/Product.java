package com.is_web.entities;

public class Product implements Entity {
    Integer id;
    String serial_number;
    Integer employee_id;
    Integer invoice_sold_id;
    Integer invoice_bought_id;
    Boolean is_deleted;
    int stb_type_id;

    public Product(String serial_number, Integer employee_id, int stb_type_id, Integer invoice_sold_id, Integer invoice_bought_id) {
        this.serial_number = serial_number;
        this.stb_type_id = stb_type_id;
        this.employee_id = employee_id;
        this.invoice_sold_id = invoice_sold_id;
        this.invoice_bought_id = invoice_bought_id;
        this.is_deleted = false;
    }

    public Product() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public int getStb_type_id() {
        return stb_type_id;
    }

    public void setStb_type_id(int typeId) {
        this.stb_type_id = typeId;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getInvoice_sold_id() {
        return invoice_sold_id;
    }

    public void setInvoice_sold_id(Integer invoice_sold_id) {
        this.invoice_sold_id = invoice_sold_id;
    }

    public Integer getInvoice_bought_id() {
        return invoice_bought_id;
    }

    public void setInvoice_bought_id(Integer invoice_bought_id) {
        this.invoice_bought_id = invoice_bought_id;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public boolean getIsDeleted() {return is_deleted;};

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", serialNumber='" + serial_number + '\'' +
                ", typeId=" + stb_type_id + '\'' +
                ", employee_id=" + employee_id + '\'' +
                ", invoice_sold_id=" + invoice_sold_id + '\'' +
                ", invoice_bought_id=" + invoice_bought_id + '\'' +
                ", is_deleted=" + is_deleted + '\'' +
                '}';
    }
}
