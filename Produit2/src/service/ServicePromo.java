/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataBase.DataSource;
import Entity.Promotion;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ServicePromo {
    
      Connection conn;
    Statement statement;
    ResultSet rs;
    
    public ServicePromo(){
        conn=(Connection) DataSource.getInstance().getConnection();
    }
    
    
     public void insertPromo(Promotion promo)throws SQLException{
        String req="INSERT INTO"
                + " `promotion`( `id_produit`,`date_debut`, `date_fin`, `Pourcentage`, `id_utilisateur`) "
                + "VALUES (?,?,?,?,?)";
      
        
            PreparedStatement ste= conn.prepareStatement(req);
      
            ste.setDate(2,  promo.getDate_Debut());
             ste.setDate(3,  promo.getDate_Fin());
           
             ste.setFloat(4, promo.getPourcentage());
             ste.setInt(1, promo.getId_Produit()); 
              ste.setInt(5, promo.getId_utilisateur()); 
            ste.executeUpdate();
             System.out.println("Produit créé!!!!");
        
   
    }
      public Promotion selectPromoDet(int id) throws SQLException{
        String req="select * from promotion where id_produit=?;" ;
    Promotion p = new Promotion();
        try {
           PreparedStatement stm = conn.prepareStatement(req);
            
    stm.setString(1, String.valueOf(id));
    ResultSet rs = stm.executeQuery();
    
            while(rs.next()){
               
                p.setId_Produit(rs.getInt("id_Produit"));
                p.setPourcentage(rs.getFloat("pourcentage"));
               
            }   


            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    
}
