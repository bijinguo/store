package com.huawei.store.entity;

import java.io.Serializable;

public class Address extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8246992493366669963L;
    private Integer aid;
    private Integer uid;
    private String name;
    private String  provinceName;
    private Integer ProvinceCode;
    private String cityName;
    private Integer cityCode;

    private String areaName;
    private Integer areaCode;
    private Integer zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;



    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getProvinceCode() {
        return ProvinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        ProvinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", ProvinceCode=" + ProvinceCode +
                ", cityName='" + cityName + '\'' +
                ", cityCode=" + cityCode +
                ", areaName='" + areaName + '\'' +
                ", areaCode=" + areaCode +
                ", zip=" + zip +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
    //    CREATE TABLE t_address(
//            aid INT AUTO_INCREMENT COMMENT '地址id',
//            uid INT COMMENT '用户id',
//            name VARCHAR(50) COMMENT '收货人姓名',
//    province_name VARCHAR(20) COMMENT '省名称',
//    province_code INT COMMENT '省代码',
//    city_name VARCHAR(20) COMMENT '市名称',
//    city_code INT COMMENT '市代码',
//    area_name VARCHAR(50) COMMENT '区名称',
//    area_code INT COMMENT '区代码',
//    zip INT COMMENT '邮政编码',
//    address VARCHAR(50) COMMENT '详细地址',
//    phone VARCHAR(20) COMMENT '手机号',
//    tel VARCHAR(20) COMMENT '固话',
//    tag VARCHAR(20) COMMENT '地址类型',
//    is_default INT COMMENT '是否为默认地址,0-不是，1-是',
//    created_user VARCHAR(50) COMMENT '创建人',
//    created_time DATETIME COMMENT '创建时间',
//    modified_user VARCHAR(50) COMMENT '最后修改人',
//    modified_time DATETIME COMMENT '最后修改时间',
//    PRIMARY KEY (aid)
//	)DEFAULT CHARSET=utf8;

}
