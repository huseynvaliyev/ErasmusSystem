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
public class Department {
    
    private ArrayList<String> name;
    private University university;
    private  ArrayList<Integer> quota;
    private ArrayList<Integer>  emptyQuota;
    private ArrayList<Integer>  id;

    public Department(ArrayList<String> name, University university, ArrayList<Integer> quota, ArrayList<Integer> emptyQuota, ArrayList<Integer> id) {
        this.name = name;
        this.university = university;
        this.quota = quota;
        this.emptyQuota = emptyQuota;
        this.id = id;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public ArrayList<Integer> getQuota() {
        return quota;
    }

    public void setQuota(ArrayList<Integer> quota) {
        this.quota = quota;
    }

    public ArrayList<Integer> getEmptyQuota() {
        return emptyQuota;
    }

    public void setEmptyQuota(ArrayList<Integer> emptyQuota) {
        this.emptyQuota = emptyQuota;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public void setId(ArrayList<Integer> id) {
        this.id = id;
    }


    
}
