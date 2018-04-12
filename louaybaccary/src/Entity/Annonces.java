/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author BlacK SouL
 */
public class Annonces {
      private int idVenteLibre,TelephoneVendeur,user_id;
    private String  titre,ville,region,description,image_name,datepublication;
    private Double prix;
    private Boolean approuver;


    public Annonces(int idVenteLibre, int TelephoneVendeur, int user_id, String titre, String ville, String region, String description, String image_name, String datepublication, Double prix, Boolean approuver) {
        this.idVenteLibre = idVenteLibre;
        this.TelephoneVendeur = TelephoneVendeur;
        this.user_id = user_id;
        this.titre = titre;
        this.ville = ville;
        this.region = region;
        this.description = description;
        this.image_name = image_name;
        this.datepublication = datepublication;
        this.prix = prix;
        this.approuver = approuver;
       
    }

    public int getIdVenteLibre() {
        return idVenteLibre;
    }

    public void setIdVenteLibre(int idVenteLibre) {
        this.idVenteLibre = idVenteLibre;
    }

    public int getTelephoneVendeur() {
        return TelephoneVendeur;
    }

    public void setTelephoneVendeur(int TelephoneVendeur) {
        this.TelephoneVendeur = TelephoneVendeur;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(String datepublication) {
        this.datepublication = datepublication;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Boolean getApprouver() {
        return approuver;
    }

    public void setApprouver(Boolean approuver) {
        this.approuver = approuver;
    }
}
