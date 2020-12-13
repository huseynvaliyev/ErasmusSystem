/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.erasmussystemsimulation;

/**
 *
 * @author huseynvaliyev
 */
public class Selection {
    
    private University university;
    private Department department;
    private boolean success;

    public Selection(University university, Department department) {
        this.university = university;
        this.department = department;
        this.success = false;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    
    
}
