/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Reclamation;
import service.ServiceReclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class GestionReclamationRSController implements Initializable {

    @FXML
    private Button buttonConfirmer;
    @FXML
    private Button buttonAnnuler;
    @FXML
    private CheckBox checkBoxRembourser;
    private Stage dialogStage;
    private boolean buttonConfirmerClicked = false;
    private Reclamation reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Button getButtonConfirmer() {
        return buttonConfirmer;
    }

    public void setButtonConfirmer(Button buttonConfirmer) {
        this.buttonConfirmer = buttonConfirmer;
    }

    public Button getButtonAnnuler() {
        return buttonAnnuler;
    }

    public void setButtonAnnuler(Button buttonAnnuler) {
        this.buttonAnnuler = buttonAnnuler;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmerClicked() {
        return buttonConfirmerClicked;
    }

    public void setButtonConfirmerClicked(boolean buttonConfirmerClicked) {
        this.buttonConfirmerClicked = buttonConfirmerClicked;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    

    
    @FXML
    private void handleButtonConfirmer() {
        
        ServiceReclamation sr = new ServiceReclamation();
            this.checkBoxRembourser.setText(String.valueOf(reclamation.getRemboursement()));
              buttonConfirmerClicked = true;
            if(checkBoxRembourser.isSelected()){
              
                reclamation.setRemboursement(1);
                reclamation.setIdReclamation(ReclamationRSController.idrec);
                sr.updateReclamation1(reclamation);
            }
            else{
                reclamation.setRemboursement(2);
                reclamation.setIdReclamation(ReclamationRSController.idrec);
                sr.updateReclamation1(reclamation);
            }
                        dialogStage.close();

    }

    @FXML
    private void handleButtonAnnuler() {
        dialogStage.close();
    }
    
     
}
