/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author HP
 */
public class User {
    private int id ;
    
    private String password;
    private String Roles;
private String username;
private int tel;

    public User(int id, String password, String Roles, String username, int tel) {
        this.id = id;
        this.password = password;
        this.Roles = Roles;
        this.username = username;
        this.tel = tel;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
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
    
    
}
