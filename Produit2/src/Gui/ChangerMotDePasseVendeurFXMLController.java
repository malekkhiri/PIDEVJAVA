/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import service.ServiceGestionProfil;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class ChangerMotDePasseVendeurFXMLController implements Initializable {

    @FXML
    private Label NouveauMotdepasse;
    @FXML
    private PasswordField AncienMotDePasse;
    @FXML
    private PasswordField NouveauMotDePasse;
    @FXML
    private PasswordField insererencore;
    @FXML
    private Button btnChanger;
    @FXML
    private Button btnQuitter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Changer(ActionEvent event) throws SQLException, IOException {
               ServiceGestionProfil sg = new ServiceGestionProfil();
       User us = new User();
       us=sg.recupererUtilisateur(LoginFXMLController.userconnecte.getUsername());
        if((us.getPassword().equals(AncienMotDePasse.getText()))&&(NouveauMotDePasse.getText().equals(insererencore.getText())))
        {
            
                us.setPassword(NouveauMotDePasse.getText());
                sg.Changermdp(us);
                       Stage stage = (Stage) btnChanger.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PofilClientFXML.fxml")));
            window.setScene(scene);  
             window.show();
             System.out.print("1");
            }
            else if (!NouveauMotDePasse.getText().equals(insererencore.getText())){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Reinserer le nouveau mot de passe");
            alert.show();
            System.out.print("2");
            }
        
        else if (!us.getPassword().equals(AncienMotDePasse.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("mot de passe incorrecte");
            alert.show();
            System.out.print("3");
        }
    }

    @FXML
    private void Quitter(ActionEvent event) throws IOException {
                      Stage stage = (Stage) btnQuitter.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PofilClientFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }
    
}
