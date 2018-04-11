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
public class User {
    private int id ;
    
    private String password;
    private String Roles;
private String username;
private String email;
    private int numTel;
    private String Ville;
    private Date dateNaissance;
    private String produitPreferee;
    private String Status;
    private int activation;
    private int ban;
    private int codeConfirmation;
     private String nom;
    private String prenom;
    private boolean status_login;

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getProduitPreferee() {
        return produitPreferee;
    }

    public void setProduitPreferee(String produitPreferee) {
        this.produitPreferee = produitPreferee;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getActivation() {
        return activation;
    }

    public void setActivation(int activation) {
        this.activation = activation;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public int getCodeConfirmation() {
        return codeConfirmation;
    }

    public void setCodeConfirmation(int codeConfirmation) {
        this.codeConfirmation = codeConfirmation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

   
     public User(int id, String nom, String prenom, String email, String username, String password, String Roles, int numTel, String Ville, Date dateNaissance, String produitPreferee, String Status, int activation, int ban, int codeConfirmation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.password = password;
        this.Roles = Roles;
        this.numTel = numTel;
        this.Ville = Ville;
        this.dateNaissance = dateNaissance;
        this.produitPreferee = produitPreferee;
        this.Status = Status;
        this.activation = activation;
        this.ban = ban;
        this.codeConfirmation = codeConfirmation;
    }
    
    public User(int id, String password, String Roles, String username, String email) {
        this.id = id;
        this.password = password;
        this.Roles = Roles;
        this.username = username;
       
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int id, String password, String Roles, String username) {
        this.id = id;
        this.password = password;
        this.Roles = Roles;
        this.username = username;
        
       
        
    }

   
    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String Roles) {
        this.Roles = Roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String Roles) {
        this.username = username;
        this.password = password;
        this.Roles = Roles;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }
     public boolean isStatus_login() {
        return status_login;
    }

    public void setStatus_login(boolean status_login) {
        this.status_login = status_login;
    }
    
}
