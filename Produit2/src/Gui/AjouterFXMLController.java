/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Commande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class AjouterFXMLController implements Initializable {

    @FXML
    private TextField id_utilisateur;
    @FXML
    private TextField id_produit;
    @FXML
    private Button Ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //@FXML
    /*private void Ajouter(ActionEvent event) {
        Commande commande= new Commande(Integer.parseInt(id_utilisateur.getText() ),Integer.parseInt(id_produit.getText()));
         ServiceCommande sc= new ServiceCommande();
         sc.insertCommande(commande);
    }
    */

    @FXML
    private void Ajouter(ActionEvent event) {
    }
    

    }