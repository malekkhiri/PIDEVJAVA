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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> choicebox;

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

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    
    
         ArrayList<String> combo = new ArrayList<>();
        
    
                combo.add("none");
        combo.add("70%");
        combo.add("60%");
        combo.add("50%");
        combo.add("40%");
        combo.add("30%");
        combo.add("20%");
        combo.add("10%");
        
     
        ObservableList<String> ComboData = FXCollections.observableArrayList(combo);
        for(String s : ComboData){
            choicebox.getItems().add(s);
        }
    
    } 
    
    
    @FXML
    public void handleButtonConfirmer() throws SQLException {

        if (ControleDeSaisie()) {
        NewFXMain1 main=new NewFXMain1();
        produit=main.getP();
        user= main.getU();
        if(choicebox.getValue().equals("70%")){
            
           Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0.7,produit.getId_Produit(),user.getId());
            
           ServicePromo sp=new ServicePromo();
           boolean F=sp.Exist(promotion.getId_Produit());
            System.out.println(F);
           if(F==false){
           sp.insertPromo(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }else{
               
               sp.updateProduit(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }
        }
       if(choicebox.getValue().equals("60%")){
            
           Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0.6,produit.getId_Produit(),user.getId());
            
           ServicePromo sp=new ServicePromo();
           boolean F=sp.Exist(promotion.getId_Produit());
            System.out.println(F);
           if(F==false){
           sp.insertPromo(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }else{
               
               sp.updateProduit(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }
        }if(choicebox.getValue().equals("50%")){
            
           Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0.5,produit.getId_Produit(),user.getId());
            
           ServicePromo sp=new ServicePromo();
           boolean F=sp.Exist(promotion.getId_Produit());
            System.out.println(F);
           if(F==false){
           sp.insertPromo(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }else{
               
               sp.updateProduit(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }
        }if(choicebox.getValue().equals("40%")){
            
           Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0.4,produit.getId_Produit(),user.getId());
            
           ServicePromo sp=new ServicePromo();
           boolean F=sp.Exist(promotion.getId_Produit());
            System.out.println(F);
           if(F==false){
           sp.insertPromo(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }else{
               
               sp.updateProduit(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }
        }if(choicebox.getValue().equals("30%")){
            
           Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0.3,produit.getId_Produit(),user.getId());
            
           ServicePromo sp=new ServicePromo();
           boolean F=sp.Exist(promotion.getId_Produit());
            System.out.println(F);
           if(F==false){
           sp.insertPromo(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }else{
               
               sp.updateProduit(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }
        }if(choicebox.getValue().equals("20%")){
            
           Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0.2,produit.getId_Produit(),user.getId());
            
           ServicePromo sp=new ServicePromo();
           boolean F=sp.Exist(promotion.getId_Produit());
            System.out.println(F);
           if(F==false){
           sp.insertPromo(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }else{
               
               sp.updateProduit(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }
        }
        if(choicebox.getValue().equals("10%")){
            
           Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0.1,produit.getId_Produit(),user.getId());
            
           ServicePromo sp=new ServicePromo();
           boolean F=sp.Exist(promotion.getId_Produit());
            System.out.println(F);
           if(F==false){
           sp.insertPromo(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }else{
               
               sp.updateProduit(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }
        }
        if(choicebox.getValue().equals("none")){
            
           Promotion promotion = new Promotion(java.sql.Date.valueOf(debut.getValue()),java.sql.Date.valueOf(s.getValue()),(float)0,produit.getId_Produit(),user.getId());
            
           ServicePromo sp=new ServicePromo();
           boolean F=sp.Exist(promotion.getId_Produit());
            System.out.println(F);
           if(F==false){
           sp.insertPromo(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }else{
               
               sp.updateProduit(promotion);
          btnClicked=true;
            dialogStage.close(); 
           }
        }
    }}
                
 
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
    

