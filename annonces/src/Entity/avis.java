/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author HP
 */
public class avis {
    
    
    private int  id_produit;
    private int id;
    private int id_utilisateur;
    private int rating;

    public avis(int id_produit, int id_utilisateur, int rating) {
        this.id_produit = id_produit;
        this.id_utilisateur = id_utilisateur;
        this.rating = rating;
    }

    public avis(int id,int id_produit, int id_utilisateur, int rating) {
        this.id_produit = id_produit;
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.rating = rating;
    }

    public avis() {
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
