package com.lq.mybatisgeneratordemo.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SaberUser implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "用户名称（邮箱格式）")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "性别：0 未知， 1男， 1 女")
    private Byte gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "用户头像图片")
    private String avatar;

    @ApiModelProperty(value = "最近一次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "最近一次登录IP地址")
    private String lastLoginIp;

    @ApiModelProperty(value = "用户真实姓名")
    private String realname;

    @ApiModelProperty(value = "用户手机号码")
    private String mobile;

    @ApiModelProperty(value = "用户QQ账号")
    private String qqNumber;

    @ApiModelProperty(value = "用户所在省份")
    private String province;

    @ApiModelProperty(value = "用户所在市区")
    private String city;

    @ApiModelProperty(value = "用户所在区县")
    private String country;

    @ApiModelProperty(value = "用户详细地址")
    private String addressDetail;

    @ApiModelProperty(value = "0 可用, 1 禁用, 2 注销")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Date addTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", avatar=").append(avatar);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", realname=").append(realname);
        sb.append(", mobile=").append(mobile);
        sb.append(", qqNumber=").append(qqNumber);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", country=").append(country);
        sb.append(", addressDetail=").append(addressDetail);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}