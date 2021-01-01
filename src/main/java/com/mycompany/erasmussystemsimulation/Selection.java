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
public class Selection {
    
    private ArrayList<String> country;
    private ArrayList<String> university;
    private ArrayList<String> department;
    private boolean success;

    public Selection(ArrayList<String> country, ArrayList<String> university, ArrayList<String> department, boolean success) {
        this.country = country;
        this.university = university;
        this.department = department;
        this.success = success;
    }

    public void setCountry(ArrayList<String> country) {
        this.country = country;
    }

    public void setUniversity(ArrayList<String> university) {
        this.university = university;
    }

    public void setDepartment(ArrayList<String> department) {
        this.department = department;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public ArrayList<String> getUniversity() {
        return university;
    }

    public ArrayList<String> getDepartment() {
        return department;
    }

    public boolean isSuccess() {
        return success;
    }

  
    
}
