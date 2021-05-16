package com.example.demo.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adopter")
public class Adopter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teid")
    private Integer teid;
    private String sex;
    private String age;
    private String status;
    private String title;
    private String marriage;
    private String city;
    private String income;
    private String de;
    private int userid;

    public Adopter() {
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "teId=" + teid +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", status='" + status + '\'' +
                ", experience='" +title + '\'' +
                ", marriage='" + marriage + '\'' +
                ", lodging='" + city + '\'' +
                ", income='" + income + '\'' +
                ", de='" + de + '\'' +

                '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public Integer getTeid() {
        return teid;
    }

    public void setTeid(Integer teid) {
        this.teid = teid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
