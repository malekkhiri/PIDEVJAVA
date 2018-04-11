/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.sql.Date;



/**
 *
 * @author ASUS-PC
 */
public class Evenement {
     
    private int id;  
    private Date Date_Debut;
    private Date Date_Fin;
    private String Nom_Evenement;
    private String Description;
    private String Lieux;
    private int nbParticipe;
    private String roleuser;
    private int nbMax;

    public Evenement(int id,Date Date_Debut, Date Date_Fin, String Nom_Evenement, String Description, String Lieux, int nbParticipe,int nbMax) {
        this.id=id;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.Nom_Evenement = Nom_Evenement;
        this.Description = Description;
        this.Lieux = Lieux;
        this.nbParticipe = nbParticipe;
        this.nbMax=nbMax;
    }

    public Evenement(int id, Date Date_Debut, Date Date_Fin, String Nom_Evenement, String Description, String Lieux, int nbParticipe, String roleuser, int nbMax) {
        this.id = id;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.Nom_Evenement = Nom_Evenement;
        this.Description = Description;
        this.Lieux = Lieux;
        this.nbParticipe = nbParticipe;
        this.roleuser = roleuser;
        this.nbMax = nbMax;
    }

    public Evenement(int id, Date Date_Debut, Date Date_Fin, String Nom_Evenement, String Description, String Lieux) {
        this.id = id;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.Nom_Evenement = Nom_Evenement;
        this.Description = Description;
        this.Lieux = Lieux;
    }

    public Evenement(int id,Date Date_Debut, Date Date_Fin, String Nom_Evenement, String Description, String Lieux, int nbParticipe, String roleuser) {
       this.id=id;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.Nom_Evenement = Nom_Evenement;
        this.Description = Description;
        this.Lieux = Lieux;
        this.nbParticipe = nbParticipe;
        this.roleuser = roleuser;
    }

//    public Evenement(int aInt, Date date, Date date0, String string, String string0, String string1, int aInt0, String string2, int aInt1) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

  

    public String getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(String roleuser) {
        this.roleuser = roleuser;
    }
    

    public Evenement() {
        
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(java.util.Date d, java.util.Date d2, String text, String text0, String text1, int i) {
    }


   


  

    public int getId() {
        return id;
    }

    public Date getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(Date Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public Date getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(Date Date_Fin) {
        this.Date_Fin = Date_Fin;
    }

    public String getNom_Evenement() {
        return Nom_Evenement;
    }

    public void setNom_Evenement(String Nom_Evenement) {
        this.Nom_Evenement = Nom_Evenement;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLieux() {
        return Lieux;
    }

    public void setLieux(String Lieux) {
        this.Lieux = Lieux;
    }

    public int getNbParticipe() {
        return nbParticipe;
    }

    public void setNbParticipe(int nbParticipe) {
        this.nbParticipe = nbParticipe;
    }

    public int getNbMax() {
        return nbMax;
    }

    public void setNbMax(int nbMax) {
        this.nbMax = nbMax;
    }

    @Override
    public String toString() {
        return "Evenement{" + "Date_Debut=" + Date_Debut + ", Date_Fin=" + Date_Fin + ", Nom_Evenement=" + Nom_Evenement + ", Description=" + Description + ", Lieux=" + Lieux + ", nbParticipe=" + nbParticipe + '}';
    }

  
  


}
