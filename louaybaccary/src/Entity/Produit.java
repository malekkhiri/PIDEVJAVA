/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Image;
import java.io.FileInputStream;

/**
 *
 * @author HP
 */
public class Produit {

    private int id_Produit;
   
      private int id_Magasin;
    private String Nom_Produit;
    private float prix;
    private int Quantite;
    private String Description;
   private int id_utilisateur;
   private String brochure;

    public Produit(int id_Produit, int id_Magasin, String Nom_Produit, float prix, int Quantite, String Description,int Validated, String brochure) {
        this.id_Produit = id_Produit;
        this.id_Magasin = id_Magasin;
        this.Nom_Produit = Nom_Produit;
        this.validated=Validated;
        this.prix = prix;
        this.Quantite = Quantite;
        this.Description = Description;
        this.brochure = brochure;
    }
    

    public String getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
   private int validated=0;

    public int getValidated() {
        return validated;
    }

    public void setValidated(int validated) {
        this.validated = validated;
    }

    
   

   

    public Produit(String Nom_Produit, float prix, int Quantite, String Description ) {
        this.Nom_Produit = Nom_Produit;
        this.prix = prix;
        this.Quantite = Quantite;
        this.Description = Description;
        
       
    }

    public Produit() {
    }

  
    @Override
    public String toString() {
        return "Produit { nom du Produit =" + Nom_Produit + ", prix=" + prix + "Quantite = " +Quantite+ '}';
    }

    public Produit(int id_Produit, String Nom_Produit, float prix, int Quantite, String Description) {
        this.id_Produit = id_Produit;
        this.Nom_Produit = Nom_Produit;
        this.prix = prix;
        this.Quantite = Quantite;
        this.Description = Description;
        
    }
        public Produit(int id_Produit, String Nom_Produit, float prix, int Quantite, String Description,int id_utilisateur,int validated) {
        this.id_Produit = id_Produit;
        this.Nom_Produit = Nom_Produit;
        this.prix = prix;
        this.Quantite = Quantite;
        this.Description = Description;
        this.id_utilisateur=id_utilisateur;
        this.validated=validated;
        
    }

    public Produit(String Nom_Produit, float prix, int Quantite, String Description, int id_utilisateur,int validated) {
        this.Nom_Produit = Nom_Produit;
        this.prix = prix;
        this.Quantite = Quantite;
        this.Description = Description;
        this.id_utilisateur=id_utilisateur;
        this.validated=validated;
      
    }

  
public Produit(String Nom_Produit, float prix, int Quantite, String Description, int id_utilisateur,int validated,String brochure) {
        this.Nom_Produit = Nom_Produit;
        this.prix = prix;
        this.Quantite = Quantite;
        this.Description = Description;
        this.id_utilisateur=id_utilisateur;
        this.validated=validated;
        this.brochure=brochure;
      
    }
public Produit(String Nom_Produit,int id_Magasin, float prix, int Quantite, String Description, int id_utilisateur,int validated,String brochure) {
        this.Nom_Produit = Nom_Produit;
        this.id_Magasin=id_Magasin;
        this.prix = prix;
        this.Quantite = Quantite;
        this.Description = Description;
        this.id_utilisateur=id_utilisateur;
        this.validated=validated;
        this.brochure=brochure;
      
    }
   
public Produit(int id_Produit,String Nom_Produit, float prix, int Quantite, String Description,int validated,String brochure) {
    this.id_Produit=id_Produit;    
    this.Nom_Produit = Nom_Produit;
        this.prix = prix;
        this.Quantite = Quantite;
        this.Description = Description;
        this.id_utilisateur=id_utilisateur;
        this.validated=validated;
        this.brochure=brochure;
      
    }

   
    
    
    

    public int getId_Magasin() {
        return id_Magasin;
    }

    public void setId_Magasin(int id_Magasin) {
        this.id_Magasin = id_Magasin;
    }

  

    
    
    public int getId_Produit() {
        return id_Produit;
    }

    public void setId_Produit(int id_Produit) {
        this.id_Produit = id_Produit;
    }

    public String getNom_Produit() {
        return Nom_Produit;
    }

    public void setNom_Produit(String Nom_Produit) {
        this.Nom_Produit = Nom_Produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

}
