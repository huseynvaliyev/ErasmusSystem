/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.erasmussystemsimulation;

import java.util.ArrayList;

/**
 *
 * @author huseynvaliyev
 */
public class Student {
    
    private String studentNumber;
    private String name;
    private String surname;
    private String password;
    private double examScore;
    private Consultant consultant;
    private ArrayList<Selection> selection;

    public Student(String studentNumber, String name, String surname, String password, double examScore){
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.examScore=examScore;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getExamScore() {
        return examScore;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public ArrayList<Selection> getSelection() {
        return selection;
    }

    public void setSelection(ArrayList<Selection> selection) {
        this.selection = selection;
    }
   
   
    

    
}
