/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hosni
 */
public class Magasin {
    private int id_magasin;
    private int prop_magasin; 
    private String nom_magasin;
    private String adresse_magasin;
    private String region;
    private String ville;
    private int validated=0;

   


    
    
    public Magasin(){
    }
    
    public Magasin(String nom_magasin, String adresse_magasin,String region, String ville){
        this.nom_magasin=nom_magasin;
        this.adresse_magasin=adresse_magasin;
        this.region=region;
        this.ville=ville;
    }
    
    
        public Magasin(String nom_magasin, String adresse_magasin,String region, String ville,int validated) {
        this.nom_magasin=nom_magasin;
        this.adresse_magasin=adresse_magasin;
        this.region=region;
        this.ville=ville;
        this.validated=validated;
        
    }

    public Magasin(String nom_magasin, String adresse_magasin,String region, String ville, int prop_magasin,int validated) {
        this.nom_magasin=nom_magasin;
        this.adresse_magasin=adresse_magasin;
        this.region=region;
        this.ville=ville;
        this.prop_magasin=prop_magasin;
        this.validated=validated;
      
    }

    public Magasin(int id_magasin, int prop_magasin, String nom_magasin, String adresse_magasin, String region, String ville,int validated) {
        this.id_magasin = id_magasin;
        this.prop_magasin = prop_magasin;
        this.nom_magasin = nom_magasin;
        this.adresse_magasin = adresse_magasin;
        this.region = region;
        this.ville = ville;
        this.validated=validated;
    }

    
    
    @Override
	public String toString() {
		return "Magasin{" + "id_magasin=" + id_magasin + ", nom_magasin=" + nom_magasin + ", adresse_magasin=" + adresse_magasin + ", region=" + getRegion() + ", ville=" + getVille() + "}";
	}

    /**
     * @return the id_magasin
     */
    public int getId_magasin() {
        return id_magasin;
    }

    /**
     * @param id_magasin the id_magasin to set
     */
    public void setId_magasin(int id_magasin) {
        this.id_magasin = id_magasin;
    }

    /**
     * @return the prop_magasin
     */
    public int getProp_magasin() {
        return prop_magasin;
    }

    /**
     * @param prop_magasin the prop_magasin to set
     */
    public void setProp_magasin(int prop_magasin) {
        this.prop_magasin = prop_magasin;
    }

    /**
     * @return the nom_magasin
     */
    public String getNom_magasin() {
        return nom_magasin;
    }

    /**
     * @param nom_magasin the nom_magasin to set
     */
    public void setNom_magasin(String nom_magasin) {
        this.nom_magasin = nom_magasin;
    }

    /**
     * @return the adresse_magasin
     */
    public String getAdresse_magasin() {
        return adresse_magasin;
    }

    /**
     * @param adresse_magasin the adresse_magasin to set
     */
    public void setAdresse_magasin(String adresse_magasin) {
        this.adresse_magasin = adresse_magasin;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    

    /**
     * @return the validated
     */
    public int getValidated() {
        return validated;
    }

    /**
     * @param validated the validated to set
     */
    public void setValidated(int validated) {
        this.validated = validated;
    }
    
}
