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
public class Wishlist {
    private int idwishlist;   
    private int iduser;
    private int idannonce;

    public Wishlist(int idwishlist, int iduser, int idannonce) {
        this.idwishlist = idwishlist;
        this.iduser = iduser;
        this.idannonce = idannonce;
    }

    public int getIdwishlist() {
        return idwishlist;
    }

    public void setIdwishlist(int idwishlist) {
        this.idwishlist = idwishlist;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdannonce() {
        return idannonce;
    }

    public void setIdannonce(int idannonce) {
        this.idannonce = idannonce;
    }

    
}
