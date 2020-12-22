

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.erasmussystemsimulation;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author khagani
 */
public class DB_query {
    private static Connection con;
    private static Statement  stmt;
    private static ResultSet  rs;   
    private static final String url="jdbc:postgresql://localhost:5432/Erasmus_DB";
    private static final String user="postgres";
    private static final String password="Eldeyme01";
    


    public  static void qosul() throws SQLException{
        try {
            con = DriverManager.getConnection(url,user,password);
            System.out.print("Connected\n");
        }catch (SQLException e){
            System.err.print(e);     
        }
    }

   public static Student login(String studentid){
        Student Student = null;
        double puan;
        try {
            stmt=con.createStatement();
            rs = stmt.executeQuery("Select * from ogrenciler where ogrenci_numarasi='"+studentid+"'");
            if(rs.next()){
                Student =new Student(rs.getString("ogrenci_numarasi"),rs.getString("ad"),rs.getString("soyad"),rs.getString("Password"),rs.getDouble("puan"));
         
            }
        }catch(SQLException e){
            System.err.println(e);
        
        }

        return Student;
    }
    public static ArrayList<String> getOgrenciler(){
        ArrayList<String> names=new ArrayList<>();
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery("select ogrenci_numarasi from ogrenciler ");
            while(rs.next()){
                names.add(rs.getString("ogrenci_numarasi"));
            }
            names.remove("Admin");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return names;
    }
    public static void register(Student student){
        int status=0;
        try{
            stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO ogrenciler(ad, soyad, ogrenci_numarasi, \"Password\") VALUES ('"+student.getName()+"','"+student.getSurname()+"','"+student.getStudentNumber()+"', '"+student.getPassword()+"')");
            JOptionPane.showMessageDialog(null, "Kayit olundu");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, student.getStudentNumber()+" numarali ogrenci bulunmaktadir");
            System.out.println(e);
        }
    }
    public static Consultant getConsultant(Student student){
        Consultant consultant= null;
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery("Select * from danisman where \"Id\" in(Select danisman_id from ogrenciler where ogrenci_numarasi ='"+student.getStudentNumber()+"')");
            if(rs.next()){
                consultant=new Consultant(rs.getString("ad"), rs.getInt("Id"));
                JOptionPane.showMessageDialog(null, consultant.getName());
            }
            else{
                JOptionPane.showMessageDialog(null, "Size daha Danisman atanmadi");
            }
        }catch(SQLException e){
            System.out.println(e);
        
        }
        return consultant;
    }
    public static Country getcountry(){
        Country country =null;
        ArrayList<String> name=new ArrayList<>();
        ArrayList<Integer> id =new ArrayList<>();

        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery("Select * from olke");
            while(rs.next()){
                name.add(rs.getString("ad"));
                id.add(rs.getInt("Id"));
                
            }
            country=new Country(name,id);
            System.out.println(id.size()+" "+name.size());
            System.out.println(id.get(2)+" "+name.get(2));
 
            
                    
            }catch(SQLException e){
            System.out.println(e);
        }
        return country;
    }
    public static University getuniversity(Country country){
        University University=null;
        ArrayList<String> uni_name=new ArrayList<>();
        ArrayList<Integer> id_array =new ArrayList<>();
        try{
           stmt=con.createStatement();
           rs=stmt.executeQuery("Select * from uni where olke_id="+country.getId().get(0));
           while(rs.next()){
               uni_name.add(rs.getString("ad"));
               id_array.add(rs.getInt("Id"));
           }
           if(uni_name.size()!=0){
               University=new University(uni_name, country, id_array);
           }
           
       }catch(SQLException e){
           System.out.println(e);
       }
       return University;
    }
    public static Department getdepartment(University university){
        Department department = null;
        try{
            stmt=con.createStatement();
                rs=stmt.executeQuery("Select * from bolumler where uni_id="+university.getId().get(0));
                ArrayList<String> name=new ArrayList<>();
                ArrayList<Integer> quota = new ArrayList<>();
                ArrayList<Integer>emptyQuota = new ArrayList<>();
                ArrayList<Integer>id= new ArrayList<>();
                        
                while(rs.next()){
                    name.add(rs.getString("bolum_adi"));
                    quota.add(rs.getInt("kontenjan"));
                    emptyQuota.add(rs.getInt("dolu_kontenjan"));
                    id.add(rs.getInt("Id"));
                }
                if(name.size()!=0)
                    department = new Department(name, university, quota, emptyQuota, id);

            
        }catch(SQLException e){
            System.out.println(e);
        }
        return department;
    }
    public static void addCountry(Country country){
        System.out.println(country.getName().get(0));
        int  status =0;
        try{
            stmt=con.createStatement();
            System.out.println("1");
            status =stmt.executeUpdate("INSERT INTO public.olke (ad) VALUES ('"+country.getName().get(0)+"')");
            System.out.println("2");
            if(status!=0){
                JOptionPane.showMessageDialog(null,"Country added");
            }
            else{
                JOptionPane.showConfirmDialog(null, "Country not added");
            }
                
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    public static void addUniversity(University university){
        int status=0;
        try{
            stmt=con.createStatement();
            status=stmt.executeUpdate("Insert into public.uni(ad,olke_id) Values ('"+university.getName().get(0)+"',"+university.getCountry().getId().get(0)+")");

            if(status !=0){
                JOptionPane.showMessageDialog(null, "University added");
            }
            else{
                JOptionPane.showMessageDialog(null, "University exsist");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
      
    }
    public static void addDepartment(Department department){
        int status=0;
        try{
            stmt=con.createStatement();
            status=stmt.executeUpdate("INSERT INTO public.bolumler(bolum_adi, uni_id, kontenjan, dolu_kontenjan)VALUES ('"+department.getName().get(0)+"',"+department.getUniversity().getId().get(0)+","+department.getQuota().get(0)+",0)");
            if(status !=0){
                JOptionPane.showMessageDialog(null, "Department added");
            }
            else{
                JOptionPane.showMessageDialog(null, "Department exsist");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static void setPuan(String studentno,int puan){
        int status=0;
        try{
            stmt=con.createStatement();
            status=stmt.executeUpdate("UPDATE public.ogrenciler SET puan="+puan+" WHERE ogrenci_numarasi='"+studentno+"'");
            if(status!=0){
                JOptionPane.showMessageDialog(null, studentno+" numarali ogrenciye puan eklendi");
            }
            else{
                JOptionPane.showMessageDialog(null, "puan eklenemedi");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
     }
    public static void setSecim(Student student, Department department,int index){
        int status=0;
        try{
            stmt=con.createStatement();
            status=stmt.executeUpdate("INSERT INTO public.secim(ogrenci_numarasi, bolum_id, status) VALUES ('"+student.getStudentNumber()+"',"+department.getId().get(index)+",0)");
            if(status!=0){
                JOptionPane.showMessageDialog(null, "Seciminiz eklendi");
            }
            else{
                JOptionPane.showMessageDialog(null, "secmiminiz eklenemedi");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
   public static void deleteSecim(Student student, Department department,int index){
       int status=0;
       try{
           stmt=con.createStatement();
           status=stmt.executeUpdate("DELETE FROM public.secim WHERE bolum_id="+department.getId().get(index));
           if(status!=0){
               JOptionPane.showMessageDialog(null, "secdiyiniz ders silindi");
           }
           else {
               JOptionPane.showMessageDialog(null, "Ders silenemedi");
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
   }
}
