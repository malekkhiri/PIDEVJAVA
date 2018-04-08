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
import Entity.Produit;
import Entity.User;
import Gui.NewFXMain1;

import java.io.FileNotFoundException;


/**
 *
 * @author HP
 */
public class ServiceProduit {
    
    Connection conn;
    Statement statement;
    ResultSet rs;
    
    public ServiceProduit(){
        conn=(Connection) DataSource.getInstance().getConnection();
    }
   
    
    public void insertProduit(Produit Produit){

        String req="INSERT INTO"
                + " `produit`(`Nom_Produit`, `prix`, `Quantite`, `Description`,`id_utilisateur`,`Validated`) "
                + "VALUES (?,?,?,?,?,?)";
        
        try {
            PreparedStatement ste= conn.prepareStatement(req);
            ste.setString(1, Produit.getNom_Produit());
            ste.setFloat(2, Produit.getPrix());
            ste.setInt(3,Produit.getQuantite() );
             ste.setString(4,Produit.getDescription() );
             ste.setInt(6,Produit.getValidated() );

             ste.setInt(5,Produit.getId_utilisateur());

             
              
//            fis = new FileInputStream(file);
//            ste.setBinaryStream(5,(InputStream)fis , (int)file.length());

             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur");
        }
    }
    
    public List<Produit> selectProduit(){
        String req="select * from produit";
        List<Produit> liste= new ArrayList<>();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while(rs.next()){
                Produit p=new Produit((rs.getInt("id_Produit")),(rs.getString("Nom_Produit")),(rs.getFloat("Prix"))
                        ,(rs.getInt("Quantite")),(rs.getString("Description")),(rs.getInt("Validated")),(rs.getString("brochure")));
                NewFXMain1 main=new NewFXMain1();
                

if(p.getValidated()==1){
                liste.add(p);
            }}
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    public void updateProduit(Produit produit){
        List<Produit> liste=new ArrayList<>();
        String req="UPDATE `produit` SET `Nom_Produit`=?,`Prix`=?,`Quantite`=?,`Description`=? WHERE id_Produit=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
             
            ste.setString(1, produit.getNom_Produit());
            ste.setFloat(2, produit.getPrix());
            ste.setInt(3, produit.getQuantite());
               ste.setString(4, produit.getDescription());
               ste.setInt(5,produit.getId_Produit());
           
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public void supprimerProduit(Produit produit){
        List<Produit> liste=new ArrayList<>();
        String req="DELETE FROM `produit` WHERE id_Produit=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
            ste.setInt(1, produit.getId_Produit());
        
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public String recupusername(int id) throws SQLException {
        String p = null;
        try {
            String req = "Select `username`   from `user` u join `produit` e on e.username = u.id"
                    + " where id_Produit=" + id;

            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while (rs.next()) {
                p = rs.getString("username");
                System.out.println(p);
            }

            return p;
        } catch (SQLException ex) {
            System.out.println(ex);
            return "erreeeeeur pseudo";
        }
    }
      
     public void updatePV(Produit produit){
        List<Produit> liste=new ArrayList<>();
        String req="UPDATE `produit` SET `Validated`=? WHERE id_Produit=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
             
            ste.setInt(1, produit.getValidated());
            
               ste.setInt(2,produit.getId_Produit());
           
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Produit> selectProduitNV(){
        String req="select * from produit";
        List<Produit> liste= new ArrayList<>();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while(rs.next()){
                Produit p=new Produit((rs.getInt("id_Produit")),(rs.getString("Nom_Produit")),(rs.getFloat("Prix"))
                        ,(rs.getInt("Quantite")),(rs.getString("Description")),(rs.getInt("Validated")));

if(p.getValidated() == 0){
                liste.add(p);
}
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    
     public Produit selectProduitDet(int id) throws SQLException{
        String req="select * from produit where id_Produit=?;" ;
     Produit p = new Produit();
        try {
           PreparedStatement stm = conn.prepareStatement(req);
            
    stm.setString(1, String.valueOf(id));
    ResultSet rs = stm.executeQuery();
    
            while(rs.next()){
               
                p.setId_Produit(rs.getInt("id_Produit"));
                p.setNom_Produit(rs.getString("Nom_Produit"));
                p.setPrix(rs.getFloat("Prix"));
                p.setQuantite(rs.getInt("Quantite"));
                p.setDescription(rs.getString("Description"));
                p.setBrochure(rs.getString("brochure"));
            }   


            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
     
     public void insertProduiIma(Produit Produit){

        String req="INSERT INTO"
                + " `produit`(`Nom_Produit`, `prix`, `Quantite`, `Description`,`id_utilisateur`,`Validated`,`brochure`) "
                + "VALUES (?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ste= conn.prepareStatement(req);
            ste.setString(1, Produit.getNom_Produit());
            ste.setFloat(2, Produit.getPrix());
            ste.setInt(3,Produit.getQuantite() );
             ste.setString(4,Produit.getDescription() );
             ste.setInt(6,Produit.getValidated() );

             ste.setInt(5,Produit.getId_utilisateur());
             ste.setString(7,Produit.getBrochure());

             
              
//            fis = new FileInputStream(file);
//            ste.setBinaryStream(5,(InputStream)fis , (int)file.length());

             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur");
        }
    }
   
}
