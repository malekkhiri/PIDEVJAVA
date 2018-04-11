/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Evenement;
import Entity.User;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.Date;


 
/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class AjoutFXMLController implements Initializable {
 
//    @FXML
//    private TextField nom;
//    @FXML
//    private TextField desc;
//    @FXML
//    private TextField lieu;
//    private DatePicker date;
//    private DatePicker date1;
//    @FXML
//    private Button ajouter;
//    @FXML
//    private TextField nbr;
//    @FXML
//    private DatePicker debut;
//    private DatePicker fin;
//    @FXML
//    private DatePicker s;
//    @FXML
//    private Button supp;
//    @FXML
//    private Button modifier;
//    @FXML
//    private Button Consulter;

  
   
    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private TextField lieu;
    @FXML
    private TextField nbr;
     @FXML
    private TextField max;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker s;
    @FXML
    private Button buttonConfirmer;
    @FXML
    private Button buttonAnnuler;


     private Stage dialogStage;
    private boolean buttonConfirmerClicked = false;
    private Evenement evenement;
    
private int year;
private int month;
private int day;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nbr.setText(Integer.toString(0));
        
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

    public Evenement getEvenement() {
        return evenement;
    }
private User user;
  
    public void setEvenement(Evenement evenement) {
        
        this.evenement = evenement;
        this.nom.setText(evenement.getNom_Evenement());
        this.desc.setText(evenement.getDescription());
        this.lieu.setText(evenement.getDescription());
        this.debut.setValue(LocalDate.now());
        this.s.setValue(debut.getValue().plusDays(1));
        this.max.setText(String.valueOf(evenement.getNbParticipe()));

    }
        

  
 

   
//    
//          LocalDate ld = debut.getValue();
//        java.util.Date date_util= new java.util.Date();
//        Date d= new Date(date_util.getTime());
//        
//        LocalDate ld2 = s.getValue();
//        java.util.Date date_util2= new java.util.Date();
//        Date d2= new Date(date_util2.getTime());
    
 @FXML
    public void handleButtonConfirmer() {

        if (ControleDeSaisie()) {
            NewFXMain1 main=new NewFXMain1();
            user=main.getU();
          evenement.setNom_Evenement(nom.getText());
          evenement.setLieux(lieu.getText());
          evenement.setDescription(desc.getText());
          evenement.setDate_Debut(java.sql.Date.valueOf(debut.getValue()));
          evenement.setDate_Fin(java.sql.Date.valueOf(s.getValue()));
          evenement.setRoleuser(user.getRoles());
          evenement.setNbMax((Integer.valueOf(max.getText())));


            buttonConfirmerClicked = true;
            dialogStage.close();
    }}
                
 
    @FXML
    public void handleButtonAnnuler() {
        dialogStage.close();
    }
    private boolean ControleDeSaisie() {
        String errorMessage = "";

        if (nom.getText() == null || nom.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
        if (lieu.getText() == null || lieu.getText().length() == 0) {
            errorMessage += "Adresse invalide!\n";
        }
        if (desc.getText() == null || desc.getText().length() == 0) {
            errorMessage += "Description invalide!\n";
        }
        
        if (debut.getValue().toString().isEmpty() ) {
            errorMessage += "Date Debut invalide!\n";
        }
         if (s.getValue().toString().isEmpty()) {
            errorMessage += "Date Fin invalide!\n";
        }
         if(debut.getValue().isAfter(s.getValue())){
                errorMessage += "Date de debut doit etre avant date fin !\n";

         }
         if(debut.getValue().isBefore(LocalDate.now())){
                 errorMessage += "Date debut deja passée !\n";

         }
         if(s.getValue().isBefore(LocalDate.now()))
                errorMessage += "Date fin deja passée !\n";
           if (max.getText().isEmpty() ) {
            errorMessage += "nbr max de participant vide\n";
        }
             if(Integer.parseInt(((max)).getText())<
            Integer.parseInt(((nbr)).getText())){
            errorMessage += "nbr max de participant invalide!\n";
          }
         
        if (errorMessage.length() == 0) {
            return true;
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
//            alert.setHeaderText("Remplir toutes les champs");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }


   
}
    


