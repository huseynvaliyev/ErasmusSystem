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
public class Country {
    
    private ArrayList<String>  name;
    private ArrayList<Integer>  id;

    public Country(ArrayList<String> name, ArrayList<Integer>  id) {
        this.name = name;
        this.id=id;
    }

    Country() {
    }

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

  

   
    
}
