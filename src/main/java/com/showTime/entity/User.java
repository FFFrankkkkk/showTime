package com.showTime.entity;

import com.showTime.common.entity.IdEntity;
import com.showTime.common.tools.Sex;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
public class User {
  @Id
  private String account;//账号
  private String phone;//手机号
  private String mail;//邮箱号
  @Column(nullable = false)
  private String userName;
  @Column(nullable = false)
  private String password;
  private String salt;     //加盐加密
  private int    type;     //0或1，0为管理员，1为普通用户
  private String face;     //头像存放地址
  private String realName;
  private Sex sex;         //0或1，0为男，1为女
  private String idCard;
  private String idcardImg;//身份证图片存放地址
  @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
  private Timestamp registerTime;
  private int    state;    //状态，默认：0\未实名认证：0\已实名认证成功：1\实名认证未通过：2\被加入黑名单：3\注销：4
  @Column(columnDefinition = "int default 0")
  private int     amount;//账户余额
    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    List<Production> productionList=new ArrayList<Production>();

    public List<Production> getProductionList() {
        return productionList;
    }

    public String getAccount() {
        return account;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public int getAmount() {
        return amount;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public int getType() {
        return type;
    }

    public String getFace() {
        return face;
    }

    public String getRealName() {
        return realName;
    }

    public Sex getSex() {
        return sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getIdcardImg() {
        return idcardImg;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public int getState() {
        return state;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setIdcardImg(String idcardImg) {
        this.idcardImg = idcardImg;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setProductionList(List<Production> productionList) {
        this.productionList = productionList;
    }

    public void setState(int state) {
        this.state = state;
    }
    public void setSomeItemNull(){
         productionList=null;
         phone=null;
         mail=null;
         password=null;
         salt=null;
         realName=null;
         sex=null;
         idCard=null;
         idcardImg=null;
         registerTime=null;
    }
}
