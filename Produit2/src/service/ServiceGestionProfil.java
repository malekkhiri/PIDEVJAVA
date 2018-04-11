/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataBase.DataSource;
import com.mysql.jdbc.Connection;
import Entity.Commande;
import Entity.User;
import Gui.NewFXMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Louay Baccary
 */
public class ServiceGestionProfil {
    
      Connection conn;
    Statement statement;
    ResultSet rs;
    
    public ServiceGestionProfil(){
        conn=(Connection) DataSource.getInstance().getConnection();
    }
    
         public User Login(String username, String password) {
         
        User u = new User();
        try {
            int row = 0;
            String req = "select * from user.user where username='" + username + "' and password='" + password + "'";
            PreparedStatement stmt = conn.prepareStatement(req);
            ResultSet res= stmt.executeQuery();
            while (res.next()) {
                row = res.getRow();
                u.setId(res.getInt("id"));
                u.setUsername(res.getString("username"));
                u.setEmail(res.getString("email"));
                u.setRoles(res.getString("role"));
                u.setPassword(res.getString("password"));
              
            }
            if (row == 1) {
                u.setStatus_login(true);
            } else {
                u.setStatus_login(false);
            }
        } catch (SQLException ex) {
           
        }
      
        return u;
    }
          public boolean Compte(String user,String password) throws SQLException
        {
            String req = "Select * From user.user where username='"+user+"' And password='"+password+"'";
         PreparedStatement stmt = conn.prepareStatement(req);
            ResultSet res= stmt.executeQuery();
         if(rs.next())
         {
             return true;
         
         }
         
    return false;
}
          public void creerCompteClient(User user) throws SQLException
          {   //  User user = new User();
              String req = "INSERT INTO user (Nom,Prenom,NumTel,Ville,DateNaissance,ProduitPref,username,"
                      + "email,password,roles,activation,codeConfirmation) Values (?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
              PreparedStatement stmt = conn.prepareStatement(req);
             stmt.setString(1,user.getNom());
             stmt.setString(2,user.getPrenom());
             stmt.setInt(3,user.getNumTel());
             stmt.setString(4,user.getVille());
             stmt.setDate(5,user.getDateNaissance());
             stmt.setString(6,user.getProduitPreferee());
             stmt.setString(7, user.getUsername());
             stmt.setString(8,user.getEmail());
             stmt.setString(9,user.getPassword());
             stmt.setString(10,"	\n" +
"a:1:{i:0;s:16:\"ROLE_UTILISATEUR\";}");
             stmt.setInt(11, user.getActivation());
             stmt.setInt(12,user.getCodeConfirmation());
             
            stmt.executeUpdate();
             } catch (SQLException ex) {
            Logger.getLogger(ServiceGestionProfil.class.getName()).log(Level.SEVERE, null, ex);
        }
          }          
public void creerCompteVendeur(User user) throws SQLException
          {   //  User user = new User();
              String req = "INSERT INTO user (Nom,Prenom,NumTel,Ville,DateNaissance,ProduitPref,username,"
                      + "email,password,roles) Values (?,?,?,?,?,?,?,?,?,?)";
                try {
              PreparedStatement stmt = conn.prepareStatement(req);
             stmt.setString(1,user.getNom());
             stmt.setString(2,user.getPrenom());
             stmt.setInt(3,user.getNumTel());
             stmt.setString(4,user.getVille());
             stmt.setDate(5,user.getDateNaissance());
             stmt.setString(6,user.getProduitPreferee());
             stmt.setString(7, user.getUsername());
             stmt.setString(8,user.getEmail());
             stmt.setString(9,user.getPassword());
             stmt.setString(10,"a:1:{i:0;s:12:\"ROLE_VENDEUR\";}");
            stmt.executeUpdate();
             } catch (SQLException ex) {
            Logger.getLogger(ServiceGestionProfil.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          }
          public boolean finduser(String user) throws SQLException
          {
               String req="Select username from user where username = ?";
                try {
             
            PreparedStatement ste= conn.prepareStatement(req);
            ste.setString(1,user);
            ResultSet res= ste.executeQuery();
           
              
        while(res.next()){
        if ( res.getString("username").equals(user)){
            return true;
            
        } else {
            return false;
        }
        }
          }catch (SQLException ex) {
            Logger.getLogger(ServiceGestionProfil.class.getName()).log(Level.SEVERE, null, ex);
}
          return false;
          }
          public  User recupererUtilisateur (String username) throws SQLException{
        User us = new User();
       
         
        String req="SELECT * FROM user Where username= '"+username+ "'";
        PreparedStatement ste= conn.prepareStatement(req);
         rs = ste.executeQuery(req);
         
		
        while(rs.next()){
             us= new User();
             us.setId(rs.getInt("id"));
             us.setUsername(rs.getString("username"));
             us.setNumTel(rs.getInt("NumTel"));
             us.setPassword(rs.getString("password"));
             us.setVille(rs.getString("Ville"));
             us.setProduitPreferee(rs.getString("ProduitPref"));
             us.setRoles(rs.getString("roles"));
             us.setEmail(rs.getString("email"));
             us.setActivation(rs.getInt("activation"));
          ;
          
             
      
        
        }
        return us ;
    }
          public boolean ModifierClient(User User) throws SQLException{
              String req = "UPDATE user set `username`=?, `NumTel`=?, `Ville` = ? ,`ProduitPref` = ? Where username=?";
                      try {
        PreparedStatement ste= conn.prepareStatement(req);
                ste.setString(1,User.getUsername());
                ste.setString(2,String.valueOf(User.getNumTel()));
                ste.setString(3, User.getVille());
                ste.setString(4,User.getProduitPreferee());
                ste.setString(5, User.getUsername());
                 ste.execute();
                  return true;
                     
                      }catch (SQLException ex) {
            Logger.getLogger(ServiceGestionProfil.class.getName()).log(Level.SEVERE, null, ex);
           
            return false;
        }
          }
          
        public void Changermdp(User User) throws SQLException{
             String req = "UPDATE user set password=? where username = ?";
                             try {
        PreparedStatement ste= conn.prepareStatement(req);
        ste.setString(1, User.getPassword());
        ste.setString(2, User.getUsername());
                 ste.execute();
        }
                                           catch (SQLException ex) {
            Logger.getLogger(ServiceGestionProfil.class.getName()).log(Level.SEVERE, null, ex);

}
        }
        public void ChangerRole(User User){
            String req ="Update user set roles = ? where username = ? ";
                                   try {
        PreparedStatement ste= conn.prepareStatement(req);
        ste.setString(1, "a:1:{i:0;s:12:\"ROLE_VENDEUR\";}");
         ste.setString(2, User.getUsername());
                      ste.execute();
        }
                                           catch (SQLException ex) {
            Logger.getLogger(ServiceGestionProfil.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        public void SupprimerCompte(User User){
            String req ="Delete From user Where id=?";
                                            try {
        PreparedStatement ste= conn.prepareStatement(req);
         ste.setInt(1, User.getId());
                      ste.executeUpdate();
        }
                                           catch (SQLException ex) {
            Logger.getLogger(ServiceGestionProfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
  public void activer(User User){
          String req ="Update user set activation = ? where id = ? ";
                                   try {
        PreparedStatement ste= conn.prepareStatement(req);
        ste.setInt(1, 1);
         ste.setInt(2, User.getId());
                      ste.execute();
        }
                                           catch (SQLException ex) {
            Logger.getLogger(ServiceGestionProfil.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
}
