package com.example.projecttest2.Models;

        import java.util.Arrays;
        import java.util.List;

public class InstructorUser {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String mobileNumber;
    private String address;
    private String specialization;
    private String degree;
    List<String> courses = Arrays.asList("Course 1", "Course 2", "Course 3");





    public InstructorUser(String email, String firstName, String lastName, String password , String mobileNumber , String address, String specialization, String degree, List<String> courses) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.mobileNumber=mobileNumber;
        this.address=address;
        this.specialization = specialization;
        this.degree = degree;
        this.courses = courses;
    }


    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public  String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public  String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public  String getMobileNumber() {
        return mobileNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public  String getAddress() {
        return address;
    }
    public  String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public  String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "InstructorUser{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                ", specialization='" + specialization + '\'' +
                ", degree='" + degree + '\'' +
                ", courses=" + courses +
                '}';
    }
}


