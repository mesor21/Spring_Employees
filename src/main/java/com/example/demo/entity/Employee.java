package com.example.demo.entity;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private String codeOfDepartment;
    private int wage;
    private boolean status;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String dateOfBirth, String phoneNumber, String codeOfDepartment, int wage, boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.codeOfDepartment = codeOfDepartment;
        this.wage = wage;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCodeOfDepartment() {
        return codeOfDepartment;
    }

    public void setCodeOfDepartment(String codeOfDepartment) {
        this.codeOfDepartment = codeOfDepartment;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
