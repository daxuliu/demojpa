package com.example.demo.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "adoption")
public class Adoption   implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adid")
    private Integer adid;
    private String petname;
    private Integer sex;
    private String isvaccine;
    private String isneuter;
    public  String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Adoption{" +
                "adid=" + adid +
                ", petname='" + petname + '\'' +
                ", sex=" + sex +
                ", isvaccine='" + isvaccine + '\'' +
                ", isneuter='" + isneuter + '\'' +
                ", img='" + img + '\'' +
                ", isep='" + isep + '\'' +
                ", des='" + des + '\'' +
                ", city='" + city + '\'' +
                ", detailed='" + detailed + '\'' +
                ", kindid=" + kindid +
                ", petageid=" + petageid +
                ", userid=" + userid +
                '}';
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Adoption() {
    }
    private String isep;
    private String des;
    private String city;
    private String detailed;
    private Integer kindid;
    private Integer petageid;
    private Integer userid;

    public Integer getAdid() {
        return adid;
    }

    public void setAdid(Integer adid) {
        this.adid = adid;
    }



    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIsvaccine() {
        return isvaccine;
    }

    public void setIsvaccine(String isvaccine) {
        this.isvaccine = isvaccine;
    }

    public String getIsneuter() {
        return isneuter;
    }

    public void setIsneuter(String isneuter) {
        this.isneuter = isneuter;
    }

    public String getIsep() {
        return isep;
    }

    public void setIsep(String isep) {
        this.isep = isep;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public Integer getKindid() {
        return kindid;
    }

    public void setKindid(Integer kindid) {
        this.kindid = kindid;
    }

    public Integer getPetageid() {
        return petageid;
    }

    public void setPetageid(Integer petageid) {
        this.petageid = petageid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
