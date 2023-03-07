package com.cybersoft.osahaneat.dto;

import java.nio.file.Path;

public class FoodDto {
    private String name;
    private String desc;
    private double price;
    private String intruction;  // Tên file hình ảnh của menu lưu trên ổ cứng
    private int cate_res_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIntruction() {
        return intruction;
    }

    public void setIntruction(String intruction) {
        this.intruction = intruction;
    }

    public int getCate_res_id() {
        return cate_res_id;
    }

    public void setCate_res_id(int cate_res_id) {
        this.cate_res_id = cate_res_id;
    }
}
