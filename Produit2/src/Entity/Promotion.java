/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class Promotion {
    
    private int id ;
        private Date Date_Fin;
           private Date Date_Debut;
           private float pourcentage ;
           private int id_Produit;
           private int id_utilisateur;

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Promotion(Date Date_Fin, Date Date_Debut, float pourcentage, int id_Produit, int id_utilisateur) {
        this.Date_Fin = Date_Fin;
        this.Date_Debut = Date_Debut;
        this.pourcentage = pourcentage;
        this.id_Produit = id_Produit;
        this.id_utilisateur = id_utilisateur;
    }

    public Promotion() {
    }

    public int getId_Produit() {
        return id_Produit;
    }

    public void setId_Produit(int id_Produit) {
        this.id_Produit = id_Produit;
    }

    public Date getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(Date Date_Fin) {
        this.Date_Fin = Date_Fin;
    }

    public Date getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(Date Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Promotion(Date Date_Fin, Date Date_Debut, float pourcentage) {
        this.Date_Fin = Date_Fin;
        this.Date_Debut = Date_Debut;
        this.pourcentage = pourcentage;
    }

    public Promotion(Date Date_Fin, Date Date_Debut, float pourcentage, int id_Produit) {
        this.Date_Fin = Date_Fin;
        this.Date_Debut = Date_Debut;
        this.pourcentage = pourcentage;
        this.id_Produit = id_Produit;
    }
    
    
           

    
}
