/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataBase.DataSource;
import Entity.Evenement;
import java.sql.Connection;
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
 * @author ASUS-PC
 */
public class ServiceMail {
    
     Connection conn= DataSource.getInstance().getConnection();
    private Statement ste;

    public ServiceMail() {
         try {
            ste = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     public String selectEmail(int id) throws SQLException{
        String req="select email from participer where idEvenement=?";
        String emm = null;
            PreparedStatement ste= conn.prepareStatement(req);
ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
       
        try {
            while(rs.next()){
               emm=rs.getString("email");
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emm;
     }
}
