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
public class notes {
    
    private int id_magasin;
    private int idUser;
    private int note;
    private int id ;

    public notes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public notes(int id_magasin, int idUser, int note) {
        this.id_magasin = id_magasin;
        this.idUser = idUser;
        this.note = note;
    }

    public notes(int id_magasin, int idUser, int note, int id) {
        this.id_magasin = id_magasin;
        this.idUser = idUser;
        this.note = note;
        this.id = id;
    }

    public int getId_magasin() {
        return id_magasin;
    }

    public void setId_magasin(int id_magasin) {
        this.id_magasin = id_magasin;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
}
