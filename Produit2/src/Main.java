
import java.sql.Connection;
import DataBase.DataSource;
import service.ServiceProduit;
import Entity.Produit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Main {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    DataSource ds = DataSource.getInstance();
    Connection connexion = ds.getConnection();
  ServiceProduit sg= new ServiceProduit();
//    Produit produit= new Produit("kabbout",10,05,"jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");


//    sg.updateProduit(produit,2);
//    sg.supprimerProduit(produit,6);
//    sg.updateGateau(gateau4, 4);
////sg.insertProduit(produit);
  sg.selectProduit().forEach(System.out::println);
    
    }
}
