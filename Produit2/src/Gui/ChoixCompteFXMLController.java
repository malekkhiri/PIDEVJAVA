/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class ChoixCompteFXMLController implements Initializable {

    @FXML
    private Button btnClient;
    @FXML
    private Button btnVendeur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Client(ActionEvent event) throws IOException {
    /*     Parent Client = FXMLLoader.load(getClass().getResource("RegistrationClientFXML.fxml"));
      
      //   Stage primaryStage = new Stage();      
        Scene sceneClient = new Scene(Client, 600, 450);
        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
    //    primaryStage.setTitle("Souk El Mdina");
        primaryStage.setScene(sceneClient);
        primaryStage.show();*/
        Stage stage = (Stage) btnClient.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("RegistrationClientFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void Vendeur(ActionEvent event) throws IOException {
               Parent Vendeur = FXMLLoader.load(getClass().getResource("RegistrationVendeurFXML.fxml"));
          //  Stage primaryStage = new Stage();       
        Scene sceneVendeur = new Scene(Vendeur, 600, 450);
        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
       // primaryStage.setTitle("Souk El Mdina");
        primaryStage.setScene(sceneVendeur);
        primaryStage.show();
    }
    }
    


