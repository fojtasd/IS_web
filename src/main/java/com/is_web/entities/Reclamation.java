package com.is_web.entities;

import java.time.LocalDate;

public class Reclamation implements Entity{
    Integer id = null;
    String serial_number;
    Boolean approved;
    LocalDate start_date;
    LocalDate end_date;
    String description;
    int products_id;
    int companies_id;
    boolean is_deleted;

    public Reclamation() {};

    public Reclamation(String serial_number, Boolean approved, LocalDate start_date, LocalDate end_date, String description, int products_id, int companies_id) {
        this.serial_number = serial_number;
        this.approved = approved;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.products_id = products_id;
        this.companies_id = companies_id;
        this.is_deleted = false;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getStart_date(){
        return start_date;
    }


    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProducts_id() {
        return products_id;
    }

    public void setProducts_id(int products_id) {
        this.products_id = products_id;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    public boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public boolean getIsDeleted() {return is_deleted;};

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", serial_number='" + serial_number + '\'' +
                ", approved=" + approved + '\'' +
                ", start_date=" + start_date + '\'' +
                ", end_date=" + end_date + '\'' +
                ", description=" + description + '\'' +
                ", product_id=" + products_id + '\'' +
                ", companies_id=" + companies_id + '\'' +
                '}';
    }
}
