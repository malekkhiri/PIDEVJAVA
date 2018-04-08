/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Commentaire {
    
    private int id;
   private int commentedproduct;
   private int commentator_id;
   private String Contenu;
   private Date CreationDate;
   

    public Commentaire(int commentedproduct, int commentator_id, String Contenu) {
        this.commentedproduct = commentedproduct;
        this.commentator_id = commentator_id;
        this.Contenu = Contenu;
        
    }

    public Commentaire(int commentedproduct, String Contenu,int commentator_id, Date CreationDate) {
        this.commentedproduct = commentedproduct;
        this.commentator_id = commentator_id;
        this.Contenu = Contenu;
        this.CreationDate = CreationDate;
    }

    public Commentaire(int id, int commentedproduct, int commentator_id, String Contenu, Date CreationDate) {
        this.id = id;
        this.commentedproduct = commentedproduct;
        this.commentator_id = commentator_id;
        this.Contenu = Contenu;
        this.CreationDate = CreationDate;
    }

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentedproduct() {
        return commentedproduct;
    }

    public void setCommentedproduct(int commentedproduct) {
        this.commentedproduct = commentedproduct;
    }

    public int getCommentator_id() {
        return commentator_id;
    }

    public void setCommentator_id(int commentator_id) {
        this.commentator_id = commentator_id;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date CreationDate) {
        this.CreationDate = CreationDate;
    }

   
  
    
}
