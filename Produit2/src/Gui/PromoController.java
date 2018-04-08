/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Produit;
import Entity.Promotion;
import Entity.User;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import service.ServicePromo;

/**
 * FXML Controller class
 *
 * @author HP
 */

public class PromoController implements Initializable {
private Stage dialogStage;
    private boolean btnClicked = false;
    private Promotion promo;
    private Produit produit;
    private User user ;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
private int year;
private int month;
private int day;
  @FXML
    private DatePicker debut;

    @FXML
    private DatePicker s;

    @FXML
    private Button buttonConfirmer;

    @FXML
    private ChoiceBox<String> choicebox;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnClicked() {
        return btnClicked;
    }

    public void setBtnClicked(boolean btnClicked) {
        this.btnClicked = btnClicked;
    }

   

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
        this.debut.setValue(LocalDate.now());
        this.s.setValue(debut.getValue().plusDays(1));
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public DatePicker getDebut() {
        return debut;
    }

    public void setDebut(DatePicker debut) {
        this.debut = debut;
    }

    public DatePicker getS() {
        return s;
    }

    public void setS(DatePicker s) {
        this.s = s;
    }

    public Button getButtonConfirmer() {
        return buttonConfirmer;
    }

    public void btnClicked(Button buttonConfirmer) {
        this.buttonConfirmer = buttonConfirmer;
    }

    public ChoiceBox<String> getChoicebox() {
        return choicebox;
    }

    public void setChoicebox(ChoiceBox<String> choicebox) {
        this.choicebox = choicebox;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    @FXML
    public void handleButtonConfirmer() throws SQLException {

        if (ControleDeSaisie()) {
        NewFXMain1 main=new NewFXMain1();
        produit=main.getP();
        user= main.getU();
        
          Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0.5,produit.getId_Produit(),user.getId());
            ServicePromo sp=new ServicePromo();
            sp.insertEvenement(promotion);
          btnClicked=true;
            dialogStage.close();
    }}
                
 
    @FXML
    public void handleButtonAnnuler() {
        dialogStage.close();
    }
    private boolean ControleDeSaisie() {
        String errorMessage = "";

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
        if (errorMessage.length() == 0) {
           
            
        
    }
         return true;
    }

    
}
    

