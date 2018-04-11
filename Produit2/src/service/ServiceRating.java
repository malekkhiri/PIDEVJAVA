/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataBase.DataSource;
import Entity.avis;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ServiceRating {
       Connection conn;
    Statement statement;
    ResultSet rs;
    
    public ServiceRating(){
        conn=(Connection) DataSource.getInstance().getConnection();
    }
     public void insertRating(avis R)throws SQLException{
        String req="INSERT INTO"
                + " `avis`( `id_produit`,`id_utilisateur`,`rating`) "
                + "VALUES (?,?,?)";
      
        try{
            PreparedStatement ste= conn.prepareStatement(req);
      
            ste.setInt(1,  R.getId_produit());
             ste.setInt(2,  R.getId_utilisateur());
           
             ste.setInt(3, R.getRating());
             
            ste.executeUpdate();
             System.out.println("rating Créé");
        
    } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur");
        }
    }
     
      public void updateProduit(avis R){
        List<avis> liste=new ArrayList<>();
        String req="UPDATE `avis` SET `rating`=? Where id=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
             
            ste.setInt(1, R.getRating());
            ste.setInt(2, R.getId());
            
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
      
      public boolean Exist(int idu , int idP) throws SQLException {
       boolean p = false ;
        
       
            String req = "Select *   from `avis` WHERE id_utilisateur='"+idu+"'and id_produit='"+idP+"'";
try{
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while (rs.next()) {
                p=true;
            }

            return p;
        } catch (SQLException ex) {
            System.out.println(ex);
          
        }return p;
    
}
      
       public avis selectavis(int idp,int idu) throws SQLException{
        String req="select * from avis where id_produit=? and id_utilisateur=?;" ;
     avis p = new avis();
        try {
           PreparedStatement stm = conn.prepareStatement(req);
            
    stm.setInt(1,idp);
      stm.setInt(2,idu);
    ResultSet rs = stm.executeQuery();
    
            while(rs.next()){
               
                p.setId_produit(rs.getInt("id_produit"));
                p.setId_utilisateur(rs.getInt("id_utilisateur"));
                p.setRating(rs.getInt("rating"));
                p.setId(rs.getInt("id"));
            }   


            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
      
      
}
      
     
      
      
      

