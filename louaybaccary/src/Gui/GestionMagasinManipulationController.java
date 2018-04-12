/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Magasin;
import Entity.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.GestionMagasin;

/**
 * FXML Controller class
 *
 * @author Hosni
 */
public class GestionMagasinManipulationController implements Initializable {

    @FXML
    private Label labelMagasinNom;
    @FXML
    private Label labelMagasinAdresse;
    @FXML
    private Label labelMagasinRegion;
    @FXML
    private Label labelMagasinVille;
    @FXML
    private TextField textFieldMagasinNom;
    @FXML
    private TextField textFieldMagasinAdresse;
    @FXML
    private TextField textFieldMagasinRegion;
    @FXML
    private TextField textFieldMagasinVille;
    @FXML
    private Button buttonConfirmer;
    @FXML
    private Button buttonAnnuler;
    
     private Stage dialogStage;
    private boolean buttonConfirmerClicked = false;
    private Magasin magasin;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * @return the buttonConfirmer
     */
    public Button getButtonConfirmer() {
        return buttonConfirmer;
    }

    /**
     * @param buttonConfirmer the buttonConfirmer to set
     */
    public void setButtonConfirmer(Button buttonConfirmer) {
        this.buttonConfirmer = buttonConfirmer;
    }

    /**
     * @return the buttonAnnuler
     */
    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    /**
     * @param buttonAnnuler the buttonAnnuler to set
     */
    public void setButtonAnnuler(Button buttonAnnuler) {
        this.buttonAnnuler = buttonAnnuler;
    }

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the buttonConfirmerClicked
     */
    public boolean isButtonConfirmerClicked() {
        return buttonConfirmerClicked;
    }

    /**
     * @param buttonConfirmerClicked the buttonConfirmerClicked to set
     */
    public void setButtonConfirmerClicked(boolean buttonConfirmerClicked) {
        this.buttonConfirmerClicked = buttonConfirmerClicked;
    }

    /**
     * @return the magasin
     */
    public Magasin getMagasin() {
        return magasin;
    }

    /**
     * @param magasin the magasin to set
     */
    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;

        this.textFieldMagasinNom.setText(magasin.getNom_magasin());
        this.textFieldMagasinAdresse.setText(magasin.getAdresse_magasin());
        this.textFieldMagasinRegion.setText(magasin.getRegion());
        this.textFieldMagasinVille.setText(magasin.getVille());

    }
    
   /* 
    @FXML
    public void handleButtonConfirmer() {

        if (ControleDeSaisie()) {

            NewFXMain1 main=new NewFXMain1();
            user=main.getU();
        magasin.setNom_magasin(textFieldMagasinNom.getText());
                   magasin.setAdresse_magasin(textFieldMagasinAdresse.getText());
          magasin.setRegion(textFieldMagasinRegion.getText());
         magasin.setVille(textFieldMagasinVille.getText());
//Magasin magasin = new Magasin(textFieldMagasinNom.getText(),textFieldMagasinAdresse.getText() , textFieldMagasinRegion.getText(),textFieldMagasinVille.getText(), user.getId(), 0);
 //service.GestionMagasin gm=new service.GestionMagasin();
// gm.AjouterMagasin(magasin);
        
            

            buttonConfirmerClicked = true;
            dialogStage.close();
    }}
    */
    @FXML
    public void handleButtonConfirmer(ActionEvent event) throws SQLException {
    NewFXMain1 main=new NewFXMain1();
    
  
     user=main.getU();
magasin.setNom_magasin(textFieldMagasinNom.getText());
magasin.setAdresse_magasin(textFieldMagasinAdresse.getText());
magasin.setRegion(textFieldMagasinRegion.getText());
magasin.setVille(textFieldMagasinVille.getText());


        
        GestionMagasin gm=new GestionMagasin();
        if(gm.findById(magasin.getId_magasin()) == 0){
            Magasin magasin = new Magasin(textFieldMagasinNom.getText(),textFieldMagasinAdresse.getText() , textFieldMagasinRegion.getText(),textFieldMagasinVille.getText(), user.getId(), 0);

            gm.AjoutMagasin(magasin);
            System.out.println("Gui.GestionMagasinManipulationController.handleButtonConfirmer()");
                    System.out.println("Gui.MessageMagasin.handleButtonConfirmer()");}


        else
        {
            System.out.println("c bon  ");
            gm.ModifierMagasin(magasin);
        }
            buttonConfirmerClicked = true;
            dialogStage.close();
    }
    
    @FXML
    public void handleButtonAnnuler() {
        dialogStage.close();
    }
    private boolean ControleDeSaisie() {
        String errorMessage = "";

        if (textFieldMagasinNom.getText() == null || textFieldMagasinNom.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
        if (textFieldMagasinAdresse.getText() == null || textFieldMagasinAdresse.getText().length() == 0) {
            errorMessage += "Adresse invalide!\n";
        }
        if (textFieldMagasinRegion.getText() == null || textFieldMagasinRegion.getText().length() == 0) {
            errorMessage += "Region invalide!\n";
        }
        if (textFieldMagasinVille.getText() == null || textFieldMagasinVille.getText().length() == 0) {
            errorMessage += "Ville invalide!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplir toutes les champs");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }


    
}
