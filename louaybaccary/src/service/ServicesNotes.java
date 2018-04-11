/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataBase.DataSource;
import Entity.avis;
import Entity.notes;
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
 * @author Hosni
 */
public class ServicesNotes {
    
           Connection conn;
    Statement statement;
    ResultSet rs;
    
    public ServicesNotes(){
        conn=(Connection) DataSource.getInstance().getConnection();
    }
     public void insertRating(notes R)throws SQLException{
        String req="INSERT INTO"
                + " `notes`( `id_magasin`,`idUser`,`note`) "
                + "VALUES (?,?,?)";
      
        try{
            PreparedStatement ste= conn.prepareStatement(req);
      
            ste.setInt(1,  R.getId_magasin());
             ste.setInt(2,  R.getIdUser());
           
             ste.setInt(3, R.getNote());
             
            ste.executeUpdate();
             System.out.println("rating Créé");
        
    } catch (SQLException ex) {
            Logger.getLogger(ServicesNotes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur");
        }
    }
     
      public void updateProduit(notes R){
        List<notes> liste=new ArrayList<>();
        String req="UPDATE `notes` SET `note`=? Where id=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
             
            ste.setInt(1, R.getNote());
            ste.setInt(2, R.getId());
            
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesNotes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
      
      public boolean Exist(int idu , int idP) throws SQLException {
       boolean p = false ;
        
       
            String req = "Select *   from `notes` WHERE idUser='"+idu+"'and id_magasin='"+idP+"'";
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
      
       public notes selectavis(int idp,int idu) throws SQLException{
        String req="select * from notes where id_magasin=? and idUser=?;" ;
     notes n = new notes();
        try {
           PreparedStatement stm = conn.prepareStatement(req);
            
    stm.setInt(1,idp);
      stm.setInt(2,idu);
    ResultSet rs = stm.executeQuery();
    
            while(rs.next()){
               
                n.setId_magasin(rs.getInt("id_magasin"));
                n.setIdUser(rs.getInt("idUser"));
                n.setNote(rs.getInt("note"));
                n.setId(rs.getInt("id"));
            }   


            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
}
