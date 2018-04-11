/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.LoginFXMLController.userconnecte;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceGestionProfil;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class ActivationCompteFXMLController implements Initializable {

    @FXML
    private Button btnConfirmer;
    @FXML
    private Button btnQuitter;
    @FXML
    private TextField textfieldcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Confirmer(ActionEvent event) throws IOException, SQLException {
        userconnecte = LoginFXMLController.userconnecte;
        
        System.out.print(LoginFXMLController.userconnecte.getCodeConfirmation());
        if (String.valueOf(LoginFXMLController.userconnecte.getCodeConfirmation()).equals(textfieldcode.getText())){
            //userconnecte.setActivation(1);
            ServiceGestionProfil sc = new ServiceGestionProfil();
            sc.activer(LoginFXMLController.userconnecte);
                 Stage stage = (Stage) btnConfirmer.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserDocument.fxml")));
            window.setScene(scene);  
             window.show();
        }
        else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Code incorrecte ");
            alert.show();
        }
    }

    @FXML
    private void Quitter(ActionEvent event) {
    }
    
}
