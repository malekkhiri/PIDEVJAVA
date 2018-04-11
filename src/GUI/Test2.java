/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Malek
 */
public class Test2 extends Application {

    public static Commande commande = null;
    public static String nom;

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Test2.nom = nom;
    }

    public static Commande getCommande() {
        return commande;
    }

    public static void setCommande(Commande commande) {
        Test2.commande = commande;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationRS.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
