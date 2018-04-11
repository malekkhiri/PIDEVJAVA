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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceGestionProfil;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label test;
    @FXML
    private Button btnCreerCompte;

    /**
     * Initializes the controller class.
     */
      public static User userconnecte ;
      
    @Override
  
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event)  throws SQLException, IOException {
        ServiceGestionProfil sg = new ServiceGestionProfil();
        NewFXMain1 main= new NewFXMain1();
     //   User User = new User();
        if(main.u.getUsername().equals(textFieldUser.getText())&&sg.finduser(textFieldUser.getText()))
           
            
                {  
                    userconnecte=sg.recupererUtilisateur(textFieldUser.getText());
                    System.out.print(userconnecte.getActivation());
                     //userconnecte = new User(textFieldUser.getText());
                        if (main.u.getActivation()==0&&userconnecte.getActivation()==0){
                             Stage stage = (Stage) btnLogin.getScene().getWindow();
                                        stage.close();
                                        Stage window = new Stage();
                                        window.centerOnScreen();
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ActivationCompteFXML.fxml")));
                                        window.setScene(scene);  
                                        window.show();
                    }
                    else
                    if (sg.recupererUtilisateur(textFieldUser.getText()).getPassword().equals(textFieldPassword.getText()))
                    {     
                        if (main.u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")||sg.recupererUtilisateur(textFieldUser.getText()).getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}"))
                                {        
                                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                                        stage.close();
                                        Stage window = new Stage();
                                        window.centerOnScreen();
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Acceuil.fxml")));
                                        window.setScene(scene);  
                                        window.show();
                                      
                                        
                                }
                   
                       else if (main.u.getRoles().equals("a:1:{i:0;s:16:\"ROLE_UTILISATEUR\";}")||sg.recupererUtilisateur(textFieldUser.getText()).getRoles().equals("a:1:{i:0;s:16:\"ROLE_UTILISATEUR\";}"))
                                {
                                        
                                  
                                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                                        stage.close();
                                        Stage window = new Stage();
                                        window.centerOnScreen();
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Acceuil.fxml")));
                                        window.setScene(scene);  
                                        window.show();
                                   
                    }
                         else if (main.u.getRoles().equals("a:2:{i:0;s:16:\"ROLE_UTILISATEUR\";i:1;s:10:\"ROLE_ADMIN\";}")&&sg.recupererUtilisateur(textFieldUser.getText()).getRoles().equals("a:2:{i:0;s:16:\"ROLE_UTILISATEUR\";i:1;s:10:\"ROLE_ADMIN\";}"))
                                {
                                        
                                  
                                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                                        stage.close();
                                        Stage window = new Stage();
                                        window.centerOnScreen();
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomeAdmin.fxml")));
                                        window.setScene(scene);  
                                        window.show();
                                   
                    }
                
                }
                else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password incorrecte ");
            alert.show();
                    }
   
    
    
}
        else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Username invalide ");
        alert.show();
        }
}
    

    @FXML
    private void CreerCompte(ActionEvent event) throws IOException {
                                   Stage stage = (Stage) btnCreerCompte.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ChoixCompteFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }
}
