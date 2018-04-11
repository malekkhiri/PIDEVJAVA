/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataBase.DataSource;
import Entity.Produit;
import Entity.Reclamation;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Malek
 */
public class ServiceReclamation {

    Connection con;
    Statement statement;
    ResultSet rs;

    public ServiceReclamation() {
        con = (Connection) DataSource.getInstance().getConnection();
    }

    public void insertReclamation(Reclamation reclamation) {

        String req = "INSERT INTO"
                + " `reclamation`(`Contenu`,`NomDestinataire`,`emetteur`) "
                + "VALUES (?,?,?)";

        try {
            PreparedStatement ste = con.prepareStatement(req);
            ste.setString(1, reclamation.getContenu());
            ste.setInt(2, reclamation.getNomDestinataire());
            ste.setInt(3, reclamation.getEmetteur());

            ste.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);;
        }
    }

    public List<Reclamation> insertReclamation2(Reclamation reclamation) {
        List<Reclamation> liste = new ArrayList<>();
        String user = "select C.quantite from commande C inner join user U on (C.id_utilisateur=U.id)";

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(user);
            while (rs.next()) {
                Reclamation r = new Reclamation(rs.getInt("quantite"));
                liste.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);;
        }
        return liste;
    }

    public List<Reclamation> selectReclamation() {
        String req = "select * from reclamation ";
        List<Reclamation> liste = new ArrayList<>();
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(req);
            while (rs.next()) {
                Reclamation r = new Reclamation(rs.getInt("idReclamation"), rs.getInt("Emetteur"), rs.getString("Contenu"), rs.getInt("remboursement"));

                liste.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e);;
        }
        return liste;
    }
    
    public List<Reclamation> selectReclamation1(int idU) {
        String req = "select * from reclamation WHERE NomDestinataire='"+idU+"'";
        List<Reclamation> liste = new ArrayList<>();
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(req);
            while (rs.next()) {
                Reclamation r = new Reclamation(rs.getInt("idReclamation"), rs.getInt("Emetteur"), rs.getString("Contenu"), rs.getInt("remboursement"), rs.getInt("NomDestinataire"));

                liste.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e);;
        }
        return liste;
    }

    public void updateReclamation(Reclamation reclamation) {
        List<Reclamation> liste = new ArrayList<>();
        String req = "UPDATE `reclamation` SET `Contenu`=? , `NomDestinataire`=? WHERE idReclamation=?";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            //ste.setInt(1, reclamation.getEmetteur());
            ste.setString(1, reclamation.getContenu());
            ste.setInt(2, reclamation.getNomDestinataire());
            ste.setInt(3, reclamation.getIdReclamation());
            ste.executeUpdate();
            System.out.print(ste);
            System.out.print("nom dest" + reclamation.getNomDestinataire());
            System.out.print("id" + reclamation.getIdReclamation());

        } catch (SQLException e) {
            System.out.println(e);;
        }
    }

    public void updateReclamation1(Reclamation reclamation) {
        List<Reclamation> liste = new ArrayList<>();
        String req = "UPDATE `reclamation` SET `remboursement`=? WHERE idReclamation=?";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, reclamation.getRemboursement());
            ste.setInt(2, reclamation.getIdReclamation());
            System.out.println(reclamation.getIdReclamation());
            ste.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void supprimerReclamation(Reclamation reclamation) {
        String req = "DELETE FROM `reclamation` WHERE idReclamation=?";
        try {
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, reclamation.getIdReclamation());
            ste.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);;
        }
    }
    
 public Produit selectp(int idU) {
        String req = "select * FROM Produit p INNER JOIN Commande C on P.id_Produit=C.id_Produit WHERE  C.id_Produit='" + idU + "'";
        Produit p=new Produit();
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(req);
            while (rs.next()) {

                p.setId_utilisateur(rs.getInt("id_utilisateur"));
                p.setNom_Produit(rs.getString("Nom_Produit"));
            }

        } catch (SQLException e) {
            System.out.println(e);;
        }
        return p;
    }
    public User selectu(int idU) {
        String req = "select * FROM user U INNER JOIN produit P on P.id_utilisateur=u.id WHERE  P.id_utilisateur='" + idU + "'";
        User u = new User();
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(req);
            while (rs.next()) {

                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println(e);;
        }
        return u;
    }

    public User selectu2(int idU) {
        String req = "select * FROM user U INNER JOIN reclamation R on R.emetteur=u.id WHERE  R.emetteur='"+idU+"'";
        User u = new User();
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(req);
            while (rs.next()) {

                u.setUsername(rs.getString("username"));
                //u.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println(e);;
        }
        return u;
    }
}
