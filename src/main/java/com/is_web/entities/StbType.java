package com.is_web.entities;

public class StbType implements Entity{
    Integer id = null;
    String name;
    Integer ram;
    String wifi_module;
    Integer cpu_cores;
    Integer cpu_frequency;
    Boolean is_deleted;

    public StbType() {}

    public StbType(String name, Integer ram, String wifi_module, Integer cpu_cores, Integer cpu_frequency) {
        this.name = name;
        this.ram = ram;
        this.wifi_module = wifi_module;
        this.cpu_cores = cpu_cores;
        this.cpu_frequency = cpu_frequency;
        this.is_deleted = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getWifi_module() {
        return wifi_module;
    }

    public void setWifi_module(String wifi_module) {
        this.wifi_module = wifi_module;
    }

    public Integer getCpu_cores() {
        return cpu_cores;
    }

    public void setCpu_cores(Integer cpu_cores) {
        this.cpu_cores = cpu_cores;
    }

    public Integer getCpu_frequency() {
        return cpu_frequency;
    }

    public void setCpu_frequency(Integer cpu_frequency) {
        this.cpu_frequency = cpu_frequency;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean getIsDeleted() {return is_deleted;};

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Stb_type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ram='" + ram + '\'' +
                ", wifi_module='" + wifi_module + '\'' +
                ", cpu_cores='" + cpu_cores + '\'' +
                ", cpu_frequency='" + cpu_frequency + '\'' +
                ", is_deleted=" + is_deleted + '\'' +
                '}';
    }
}
