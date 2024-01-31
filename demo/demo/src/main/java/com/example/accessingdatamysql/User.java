package com.example.accessingdatamysql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// The @Entity annotation indicates that this class is a JPA entity.
// The name attribute of @Entity is used to refer to the entity in queries.
@Entity(name = "tb_example")
public class User {

  // The @Id annotation specifies the primary key of the entity.
  // The @GeneratedValue annotation provides for the specification of generation strategies for the values of primary keys.
  @Id
  @Column(name = "UserID", length = 255)
  private String userID;

  // The @Column annotation is used to specify the mapped column for a persistent property or field.
  @Column(name = "Password", length = 255)
  private String Password;

  @Column(name = "A")
  private float A;

  @Column(name = "K")
  private float K;

  @Column(name = "V")
  private float V;

  // The @Temporal annotation must be specified for persistent fields or properties of type java.util.Date and java.util.Calendar.
  @Column(name = "DateTime")
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date DateTime;

  // Getters and setters for the fields.
  public String getUserID() {
    return userID;
  }

  public void setUserID(String UserID) {
    this.userID = UserID;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String Password) {
    this.Password = Password;
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

  public float getV() {
    return V;
  }

  public void setV(float V) {
    this.V = V;
  }

  public java.util.Date getDateTime() {
    return DateTime;
  }

  public void setDateTime(java.util.Date DateTime) {
    this.DateTime = DateTime;
  }
}