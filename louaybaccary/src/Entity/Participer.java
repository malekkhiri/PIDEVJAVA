/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author ASUS-PC
 */
public class Participer {
     private int idEvenement;  
    private String email;

    public Participer(int idEvenement, String email) {
        this.idEvenement = idEvenement;
        this.email = email;
    }

    public Participer() {
    }



    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}
