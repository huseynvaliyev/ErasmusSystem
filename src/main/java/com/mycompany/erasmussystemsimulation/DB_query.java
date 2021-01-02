

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
import java.util.Comparator;
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
    private static final String password="1110";
    


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
            if(e.getSQLState().equals("23505")){
                JOptionPane.showMessageDialog(null,student.getStudentNumber()+ "Numarali ogrenci daha onceden kayit oldu");
            }
            else {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
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
;
 
            
                    
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
           rs=stmt.executeQuery("Select * from public.getuniversity("+country.getId().get(0)+")");
           while(rs.next()){
               uni_name.add(rs.getString("uni_ad"));
               id_array.add(rs.getInt("id"));
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
                    quota.add(rs.getInt("bosh_kontenjan"));
                    emptyQuota.add(rs.getInt("dolu_kontenjan"));
                    id.add(rs.getInt("id"));
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
            status=stmt.executeUpdate("Insert into public.uni(uni_ad,olke_id) Values ('"+university.getName().get(0)+"',"+university.getCountry().getId().get(0)+")");

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
            status=stmt.executeUpdate("INSERT INTO public.bolumler(bolum_adi, uni_id, bosh_kontenjan, dolu_kontenjan)VALUES ('"+department.getName().get(0)+"',"+department.getUniversity().getId().get(0)+","+department.getQuota().get(0)+",0)");
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
   public static Selection getSecimler(Student student){
       Selection selection = null;
       ArrayList<String> country =new ArrayList<>();
       ArrayList<String> university=new ArrayList<>();
       ArrayList<String> department =new ArrayList<>();
       try{
           stmt=con.createStatement();
           rs=stmt.executeQuery("Select * from ogrenci_secimleri where ogrenci_numarasi='" +student.getStudentNumber()+"'");
           while(rs.next()){
               country.add(rs.getString("ad"));
               university.add(rs.getString("uni_ad"));
               department.add(rs.getString("bolum_adi"));
           }
           if(country.size()>0){
               selection=new Selection(country, university, department, true);
            }
           
       }catch(SQLException e){
           System.err.println(e);
       }
   return selection;    
   }

   
   public static void deleteCountry(String name){
       int status=0;
       try{
           stmt=con.createStatement();
           status=stmt.executeUpdate("DELETE FROM public.olke WHERE olke.ad='"+name+"'");
           if(status!=0){
               JOptionPane.showMessageDialog(null, "secdiyiniz ulke silindi");
           }
           else {
               JOptionPane.showMessageDialog(null, "ulke silenemedi");
           }
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
   }
   
   public static Student getStudentResult(Student student){
       int status =0;
       try{
           Consultant consultant =new Consultant(null,-1);
           student.setConsultant(consultant);
           stmt=con.createStatement();
           rs=stmt.executeQuery("SELECT d.ad ,bolum_adi,uni_ad\n" +
            "From ogrenciler o ,danisman d , secim s , bolumler b , uni u\n" +
            "where o.ogrenci_numarasi='"+student.getStudentNumber() +"'and o.danisman_id=d.Id and o.ogrenci_numarasi=s.ogrenci_numarasi \n" +
            "and status=1 and s.bolum_id=b.\"Id\" and b.uni_id = u.id");
           while(rs.next()){
            student.setAcceptedUni(rs.getString("uni_ad"));
            student.getConsultant().setName(rs.getString("ad"));
            student.setAcceptedDepart(rs.getString("bolum_adi"));    
       }
          
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
        return student;
   }
   
   public static ArrayList<Student> getAllResult(){

                      ArrayList<Student> students = new ArrayList<>();

       try{
           stmt=con.createStatement();
           rs=stmt.executeQuery("SELECT o.ogrenci_numarasi , d.ad , b.bolum_adi,u.uni_ad\n" +
            "From ogrenciler o ,danisman d , secim s , bolumler b , uni u \n" +
            "where  o.danisman_id=d.Id and o.ogrenci_numarasi=s.ogrenci_numarasi \n" +
            " and s.bolum_id=b.\"Id\" and b.uni_id = u.id\n" +
            "EXCEPT\n" +
            "SELECT   o.ogrenci_numarasi , d.ad , b.bolum_adi,u.uni_ad\n" +
            "From ogrenciler o ,danisman d , secim s , bolumler b , uni u \n" +
            "where  o.danisman_id=d.Id and o.ogrenci_numarasi=s.ogrenci_numarasi \n" +
            " and s.bolum_id=b.\"Id\" and b.uni_id = u.id and s.status=0");
           while(rs.next()){
                 Student student = new Student(null,null,null,null,0.0);
                Consultant consultant= new Consultant(null,-1);
                student.setConsultant(consultant);
               student.setStudentNumber(rs.getString("ogrenci_numarasi"));
               student.setAcceptedDepart(rs.getString("bolum_adi"));
               student.setAcceptedUni(rs.getString("uni_ad"));
               student.getConsultant().setName(rs.getString("ad"));
               students.add(student);  
           }
           
           
       }catch(SQLException e){
           System.err.println(e);
       }
       System.out.println(students.size());
   return students;    
   }
   
   public static void approvement(){
       try{
           stmt=con.createStatement();
           rs=stmt.executeQuery("SELECT approve()");
           if(rs.next()){
               if(rs.getInt("approve")==1){
                    JOptionPane.showMessageDialog(null, "Atama yapildi");
               }
               else{
                   JOptionPane.showMessageDialog(null, "Daha onceden atama yapildi");
               }
           }
   }
       catch(SQLException e){
           System.err.println(e);
       }
      
   
   }
   
}
