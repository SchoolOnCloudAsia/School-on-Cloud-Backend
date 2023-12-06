package com.example.accessingdatamysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

  private String variableV;

  private String variableA;

  private String variableK;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getVariableV() {
    return variableV;
  }

  public void setVariableV(String variableV) {
    this.variableV = variableV;
  }

  public String getVariableA() {
    return variableA;
  }

  public void setVariableA(String variableA) {
    this.variableA = variableA;
  }

  public String getVariableK() {
    return variableK;
  }

  public void setVariableK(String variableK) {
    this.variableK = variableK;
  }
}