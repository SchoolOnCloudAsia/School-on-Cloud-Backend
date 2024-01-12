package com.example.accessingdatamysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity(name = "tb_example") // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(name = "DateTime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date DateTime;

  @Column(name = "UserID")
  private String UserID;

  @Column(name = "Password")
  private String Password;

  @Column(name = "V")
  private float V;

  @Column(name = "A")
  private float A;

  @Column(name = "K")
  private float K;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDateTime() {
    return DateTime;
  }

  public void setDateTime(Date DateTime) {
    this.DateTime = DateTime;
  }

  public String getUserID() {
    return UserID;
  }

  public void setUserID(String UserID) {
    this.UserID = UserID;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String Password) {
    this.Password = Password;
  }

  public float getV() {
    return V;
  }

  public void setV(float V) {
    this.V = V;
  }

  public float getA() {
    return A;
  }

  public void setA(float A) {
    this.A = A;
  }

  public float getK() {
    return K;
  }

  public void setK(float K) {
    this.K = K;
  }
}