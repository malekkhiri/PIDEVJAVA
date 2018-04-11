/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Louay Baccary
 */
public class Commande {
    private int id;
     private Produit id_produit;
    private int id_utilisateur;
    private int Quantite;
    private String Statut;
    private User id_user;

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }
    

    public Commande(int id, Produit id_produit, int id_utilisateur, int Quantite, String Statut, User id_user) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_utilisateur = id_utilisateur;
        this.Quantite = Quantite;
        this.Statut = Statut;
        this.id_user = id_user;
    }

    public Commande(int id, Produit id_produit, int id_utilisateur, int Quantite, String Statut) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_utilisateur = id_utilisateur;
        this.Quantite = Quantite;
        this.Statut = Statut;
    }


   
   

    public Commande(Produit id_produit, int id_utilisateur, int Quantite) {
        this.id_produit = id_produit;
        this.id_utilisateur = id_utilisateur;
        this.Quantite = Quantite;
       
    }

  
    

    public Commande(int id, Produit id_produit, int id_utilisateur, int Quantite) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_utilisateur = id_utilisateur;
        this.Quantite = Quantite;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public Commande(int id, Produit id_produit, int id_utilisateur) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_utilisateur = id_utilisateur;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getId_produit() {
        return id_produit;
    }

    public void setId_produit(Produit id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Commande() {
    }
   
 

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String Statut) {
        this.Statut = Statut;
    }

  
           @Override

   public String toString() {

 

        return "Facture_Valide_SoukELMdina" +  id;
    }

public String toString1() {

 

        return ""+  id;
    }
    
}
