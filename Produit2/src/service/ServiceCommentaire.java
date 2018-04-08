/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataBase.DataSource;
import Entity.Commentaire;
import Entity.Produit;
import Entity.User;
import Gui.NewFXMain1;
import com.mysql.jdbc.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ServiceCommentaire {
    
    
        Connection conn;
    Statement statement;
    ResultSet rs;
    
    public ServiceCommentaire(){
        conn=(Connection) DataSource.getInstance().getConnection();
    }
   
    
    public void insertCommentaire(Commentaire commentaire){

        String req="INSERT INTO"
                + " `commentaire`(`Commentator_id`,`commentedproduct`, `Contenu`,`creationDate`) "
                + "VALUES (?,?,?,?)";
        
        try {
            PreparedStatement ste= conn.prepareStatement(req);
            ste.setInt(2,commentaire.getCommentedproduct() );
            ste.setInt(1, commentaire.getCommentator_id());
            ste.setString(3,commentaire.getContenu() );
         
              DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            Date date =new Date();
            ste.setString(4,df.format(date));
         
             
             
              
//            fis = new FileInputStream(file);
//            ste.setBinaryStream(5,(InputStream)fis , (int)file.length());

             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur");
        }
    }
    
    public List<Commentaire> selectCommentaire(){
        
        String req="select * from `commentaire`";
        List<Commentaire> liste= new ArrayList<>();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while(rs.next()){
               Commentaire c=new Commentaire((rs.getInt("id")),(rs.getInt("commentedproduct")),rs.getInt("commentator_id"),(rs.getString("contenu")),rs.getDate("creationDate"));
           

                liste.add(c);
               
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    
    
    
    
    public void supprimerCommentaire(Commentaire commentaire){
        List<Commentaire> liste=new ArrayList<>();
        String req="DELETE FROM `commentaire` WHERE id=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
            ste.setInt(1, commentaire.getId());
        
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public List<Commentaire> selectCommentaireP(int id ){
        
        String req="select * from `commentaire` WHERE commentedproduct =?;";
        List<Commentaire> liste= new ArrayList<>();
        try {
           PreparedStatement stm = conn.prepareStatement(req);
            
    stm.setString(1, String.valueOf(id));
    ResultSet rs = stm.executeQuery();
            while(rs.next()){
               Commentaire c=new Commentaire((rs.getInt("id")),(rs.getInt("commentedproduct")),rs.getInt("commentator_id"),(rs.getString("contenu")),rs.getDate("creationDate"));
           

                liste.add(c);
               
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    public Commentaire selectCommDet(int id) throws SQLException{
        String req="select * from commentaire where id=?;" ;
    Commentaire c= new Commentaire();
        try {
           PreparedStatement stm = conn.prepareStatement(req);
            
    stm.setString(1, String.valueOf(id));
    ResultSet rs = stm.executeQuery();
    
            while(rs.next()){
               
                c.setId(rs.getInt("id"));
                c.setCommentator_id(rs.getInt("commentator_id"));
                c.setCommentedproduct(rs.getInt("commentedproduct"));
                c.setContenu(rs.getString("Contenu"));
                c.setCreationDate(rs.getDate("creationDate"));
            }   


            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    

          
    
                 public User selectCommentaireU(int idU , int idC){
        
        String req=" SELECT * FROM   user U INNER JOIN commentaire C on C.commentator_id=U.id WHERE U.id='"+idU+"'"+" and C.commentator_id='"+idC+"'" ;
         User user= new User();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
          
            while(rs.next()){

              user.setUsername(rs.getString("username"));
               
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
                
                
}
