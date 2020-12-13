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
public class University {
    
    private ArrayList<String> name;
    private Country country;
    private ArrayList<Integer> id;

    public University(ArrayList<String> name, Country country, ArrayList<Integer> id) {
        this.name = name;
        this.country = country;
        this.id = id;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

 
    
}
