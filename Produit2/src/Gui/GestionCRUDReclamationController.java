/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class GestionCRUDReclamationController implements Initializable {

    @FXML
    private Label labelReclamationNomDestinataire;
    @FXML
    private Label labelReclamationContenu;
    @FXML
    private TextField textFieldReclamationContenu;
    @FXML
    private TextField textFieldReclamationNomDestinataire;
    @FXML
    private Button buttonConfirmer;
    @FXML
    private Button buttonAnnuler;

    private Stage dialogStage;
    private boolean buttonConfirmerClicked = false;
    private Reclamation reclamation;
    @FXML
    private TextField NomU;

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
     * @return the reclamation
     */
    public Reclamation getReclamation() {
        return reclamation;
    }

    /**
     * @param reclamation the reclamation to set
     */
    public void setReclamation(Reclamation reclamation) {
        Test2 test = new Test2();
        this.reclamation = reclamation;
        this.textFieldReclamationNomDestinataire.setText(String.valueOf(test.p.getId_utilisateur()));
        this.NomU.setText(test.getNom());
        this.textFieldReclamationContenu.setText(reclamation.getContenu());
    }

    @FXML
    private void handleButtonConfirmer() {
        if (ControleDeSaisie()) {
            Gui.NewFXMain1 main = new Gui.NewFXMain1();
            reclamation.setEmetteur(main.u.getId());
            reclamation.setNomDestinataire(Integer.parseInt(textFieldReclamationNomDestinataire.getText()));
            reclamation.setContenu(textFieldReclamationContenu.getText());
            buttonConfirmerClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleButtonAnnuler() {
        dialogStage.close();
    }

    private boolean ControleDeSaisie() {
        String errorMessage = "";

        if (textFieldReclamationNomDestinataire.getText() == null || textFieldReclamationNomDestinataire.getText().length() == 0) {
            errorMessage += "Nom Destinataire invalide!\n";
        }
        if (textFieldReclamationContenu.getText() == null || textFieldReclamationContenu.getText().length() == 0) {
            errorMessage += "Contenu invalide!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplir toutes les champs");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
