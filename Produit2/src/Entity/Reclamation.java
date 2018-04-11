/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Malek
 */
public class Reclamation {
    private int emetteur;
    private String username;
    private int idReclamation;  
    private String Contenu;
    private int NomDestinataire;
    private int quantite;
    private int remboursement;
    
    public Reclamation() {
    }

    public Reclamation( int idReclamation,int emetteur, String Contenu) {
        this.emetteur = emetteur;
        this.idReclamation = idReclamation;
        this.Contenu = Contenu;
    }

    public Reclamation(int idReclamation) 
    {
        this.idReclamation = idReclamation;
    }

    public Reclamation(String username, String Contenu) {
        this.username = username;
        this.Contenu = Contenu;
    }

    public Reclamation(int emetteur, String Contenu) {
        this.emetteur = emetteur;
        this.Contenu = Contenu;
    }

    public Reclamation(String Contenu) {
        this.Contenu = Contenu;
    }
//
//    public Reclamation(int idReclamation, String Contenu) {
//        this.idReclamation = idReclamation;
//        this.Contenu = Contenu;
//    }

 

    public Reclamation(int emetteur, String Contenu, int NomDestinataire) {
        this.emetteur = emetteur;
        this.Contenu = Contenu;
        this.NomDestinataire = NomDestinataire;
    }

    public Reclamation(String Contenu, int NomDestinataire) {
        this.Contenu = Contenu;
        this.NomDestinataire = NomDestinataire;
    }

    

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", emetteur=" + emetteur + ", NomDestinataire=" + NomDestinataire + ", Contenu=" + Contenu + '}';
    }

    public Reclamation(int idReclamation, int emetteur, int NomDestinataire, String Contenu)
    {
        this.idReclamation = idReclamation;
        this.emetteur = emetteur;
        this.NomDestinataire = NomDestinataire;
        this.Contenu = Contenu;
    }
    
    public int getIdReclamation() {
        return idReclamation;
    }

    public int getRemboursement() {
        return remboursement;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public Reclamation(int idReclamation,int emetteur, String Contenu, int remboursement) {
        this.idReclamation = idReclamation;
        this.emetteur = emetteur;      
        this.Contenu = Contenu;
        this.remboursement = remboursement;
    }
    
     public Reclamation(int idReclamation,int emetteur, String Contenu, int remboursement,int NomDestinataire) {
        this.idReclamation = idReclamation;
        this.emetteur = emetteur;      
        this.Contenu = Contenu;
        this.remboursement = remboursement;
        this.NomDestinataire=NomDestinataire;
    }

    public void setRemboursement(int remboursement) {
        this.remboursement = remboursement;
    }


    public int getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(int emetteur) {
        this.emetteur = emetteur;
    }

    public int getNomDestinataire() {
        return NomDestinataire;
    }

    public void setNomDestinataire(int NomDestinataire) {
        this.NomDestinataire = NomDestinataire;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }
    
}
