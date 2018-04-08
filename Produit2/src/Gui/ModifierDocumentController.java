/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Produit;
import Entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifierDocumentController implements Initializable {

 /**
     * Initializes the controller class.
     */
        @FXML
    private JFXTextField Description;

    @FXML
    private JFXTextField Name;

    @FXML
    private JFXTextField Prix;

    @FXML
    private JFXTextField Quantitee;

    @FXML
    private JFXButton btn;
    
    
    private Stage dialogStage;
    private boolean btnClicked=false;
    private Produit produit;
    private User U;
    private Label IDU;

private int ide;
    @FXML
    private Label idp;
    public Label getIDU() {
        return IDU;
    }

    public void setIDU(Label IDU) {
        this.IDU = IDU;
    }

    

    public boolean isBtnClicked() {
        return btnClicked;
    }

    public void setBtnClicked(boolean btnClicked) {
        this.btnClicked = btnClicked;
    }
   

    public Produit getProduit() {
        return produit;
    }
 


    public void setProduit(Produit produit) {

        this.produit = produit;
        this.Name.setText(produit.getNom_Produit());
  this.Prix.setText(String.valueOf(produit.getPrix()));
          this.Quantitee.setText(String.valueOf(produit.getQuantite()));
          this.Description.setText(String.valueOf(produit.getDescription()));
          this.idp.setText(String.valueOf(produit.getId_Produit()));
          
         
          
         
 

                
                      
                                
    }
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public JFXTextField getDescription() {
        return Description;
    }

    public void setDescription(JFXTextField Description) {
        this.Description = Description;
    }

    public JFXTextField getName() {
        return Name;
    }

    public void setName(JFXTextField Name) {
        this.Name = Name;
    }

    public JFXTextField getPrix() {
        return Prix;
    }

    public void setPrix(JFXTextField Prix) {
        this.Prix = Prix;
    }

    public JFXTextField getQuantitee() {
        return Quantitee;
    }

    public void setQuantitee(JFXTextField Quantitee) {
        this.Quantitee = Quantitee;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
@FXML
  private void ModifierProduit(ActionEvent event){
     

                produit.setNom_Produit(Name.getText());
                produit.setPrix((float)Double.parseDouble(Prix.getText()));
                produit.setQuantite(Integer.parseInt(Quantitee.getText()));
                produit.setDescription(Description.getText());
        btnClicked=true;
        dialogStage.close();
    }
    
    
}
