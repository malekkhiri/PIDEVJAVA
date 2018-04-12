/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataBase.DataSource;
import Entity.Magasin;
import Entity.Produit;
import Entity.User;
import Gui.NewFXMain1;

import java.io.FileNotFoundException;


/**
 *
 * @author HP
 */
public class GestionMagasin {
    
    Connection conn;
    Statement statement;
    ResultSet rs;
    
    public  GestionMagasin(){
        conn=(Connection) DataSource.getInstance().getConnection();
    }
   
    
    public void AjoutMagasin(Magasin magasin){

        String req="INSERT INTO"
                + " `Magasin`(`nom_magasin`,`adresse_magasin`,`region`,`ville`,`Validated`,`prop_magasin`) "
                + "VALUES (?,?,?,?,?,?)";
        
        try {
            PreparedStatement statement= conn.prepareStatement(req);
            statement.setString(1, magasin.getNom_magasin());
            statement.setString(2, magasin.getAdresse_magasin());
            statement.setString(3, magasin.getRegion());
            statement.setString(4, magasin.getVille());
             statement.setInt(5,magasin.getValidated() );

             statement.setInt(6,magasin.getProp_magasin());
            statement.executeUpdate();
                    System.out.println("Magasin crée!!!!");

        } catch (SQLException ex) {
            Logger.getLogger(GestionMagasin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public int findById(int id) throws SQLException{
       Magasin m = new Magasin() ;
       m.setId_magasin(0);
        String req="select * from magasin where id_magasin="+id;
        statement =conn.createStatement();
        rs=statement.executeQuery(req);
        while(rs.next()){
            m.setId_magasin(rs.getInt("id_magasin"));
        }
        return m.getId_magasin();
   }
    
    public List<Magasin> AfficherMagasin(){
        String req="select * from magasin";
        List<Magasin> liste= new ArrayList<>();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while(rs.next()){
                Magasin m=new Magasin((rs.getInt("id_magasin")),rs.getInt("Prop_magasin"),(rs.getString("Nom_magasin")),(rs.getString("Adresse_magasin"))
                        ,(rs.getString("Region")),(rs.getString("Ville")),(rs.getInt("Validated")));
                NewFXMain1 main=new NewFXMain1();
                

if(m.getValidated()==1){
                liste.add(m);
            }}
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionMagasin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    public void ModifierMagasin(Magasin magasin){
        List<Magasin> liste=new ArrayList<>();
        String req="UPDATE `magasin` SET `Nom_magasin`=?,`Adresse_magasin`=?,`Region`=?,`Ville`=? WHERE id_magasin=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
             
            ste.setString(1, magasin.getNom_magasin());
            ste.setString(2, magasin.getAdresse_magasin());
            ste.setString(3, magasin.getRegion());
               ste.setString(4, magasin.getVille());
               ste.setInt(5,magasin.getId_magasin());
           
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionMagasin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public void SupprimerMagasin(Magasin magasin){
        List<Produit> liste=new ArrayList<>();
        String req="DELETE FROM `magasin` WHERE id_magasin=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
            ste.setInt(1, magasin.getId_magasin());
        
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionMagasin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public String recupusername(int id) throws SQLException {
        String m = null;
        try {
            String req = "Select `username`   from `user` u join `magasin` m on m.username = u.id"
                    + " where id_magasin=" + id;

            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while (rs.next()) {
                m = rs.getString("username");
                System.out.println(m);
            }

            return m;
        } catch (SQLException ex) {
            System.out.println(ex);
            return "erreeeeeur pseudo";
        }
    }
      
     public void ModifierMagasinV(Magasin magasin){
        List<Produit> liste=new ArrayList<>();
        String req="UPDATE `magasin` SET `Validated`=? WHERE id_magasin=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
             
            ste.setInt(1, magasin.getValidated());
            
               ste.setInt(2,magasin.getId_magasin());
           
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionMagasin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Magasin> AfficherMagasinV(){
        String req="select * from magasin";
        List<Magasin> liste= new ArrayList<>();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while(rs.next()){
                Magasin m=new Magasin((rs.getInt("id_magasin")),rs.getInt("Prop_magasin"),(rs.getString("Nom_magasin")),(rs.getString("Adresse_magasin"))
                        ,(rs.getString("Region")),(rs.getString("Ville")),(rs.getInt("Validated")));

if(m.getValidated() == 0){
                liste.add(m);
}
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    
     public Magasin AfficherMagasinDetail(int id) throws SQLException{
        String req="select * from magasin where id_magasin=?;" ;
     Magasin m = new Magasin();
        try {
           PreparedStatement stm = conn.prepareStatement(req);
            
    stm.setString(1, String.valueOf(id));
    ResultSet rs = stm.executeQuery();
    
            while(rs.next()){
               
                m.setId_magasin(rs.getInt("id_magasin"));
                m.setNom_magasin(rs.getString("Nom_magasin"));
                m.setProp_magasin(rs.getInt("Prop_magasin"));
                m.setAdresse_magasin(rs.getString("Adresse_magasin"));
                m.setRegion(rs.getString("Region"));
                m.setVille(rs.getString("Ville"));
            }   


            
        } catch (SQLException ex) {
            Logger.getLogger(GestionMagasin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
     
    
    public List<Magasin> AfficherMagasinUse(int idu){
        String req="select * from magasin where Prop_magasin='"+idu+"'";
        List<Magasin> liste= new ArrayList<>();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while(rs.next()){
                 Magasin m=new Magasin((rs.getInt("id_magasin")),rs.getInt("Prop_magasin"),(rs.getString("Nom_magasin")),(rs.getString("Adresse_magasin"))
                        ,(rs.getString("Region")),(rs.getString("Ville")),(rs.getInt("Validated")));
                NewFXMain1 main=new NewFXMain1();
                

if(m.getValidated()==1){
                liste.add(m);
            }}
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionMagasin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
}
