/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Commande;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class ModifQuantiteFXMLController implements Initializable {

    private TextField NewQuantite;
    @FXML
    private Button btnConfirmer;
    @FXML
    private Button btnAnnuler;
    private Stage dialogstage;
    private boolean btnConfirmerClicked = false;
    private Commande Commande;
    @FXML
    public Spinner NewQuantite1;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }    

    /**
     * @return the dialogstage
     */
    public Stage getDialogstage() {
        return dialogstage;
    }

    /**
     * @param dialogstage the dialogstage to set
     */
    public void setDialogstage(Stage dialogstage) {
        this.dialogstage = dialogstage;
    }

    /**
     * @return the btnConfirmerClicked
     */
    public boolean isBtnConfirmerClicked() {
        return btnConfirmerClicked;
    }

    /**
     * @param btnConfirmerClicked the btnConfirmerClicked to set
     */
    public void setBtnConfirmerClicked(boolean btnConfirmerClicked) {
        this.btnConfirmerClicked = btnConfirmerClicked;
    }

    /**
     * @return the Commande
     */
    public Commande getCommande() {
        return Commande;
    }

    /**
     * @param Commande the Commande to set
     */
    public void setCommande(Commande Commande) {
        this.Commande = Commande;
      //  this.NewQuantite.setText(String.valueOf(Commande.getQuantite()));
         SpinnerValueFactory<Integer> NewQuantiteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Commande.getId_produit().getQuantite(), Commande.getQuantite());
       this.NewQuantite1.setValueFactory(NewQuantiteFactory);
    }
    @FXML
    public void Confirmer() throws SQLException
    {   
        ServiceCommande sc = new ServiceCommande();
      /*  if (((int) NewQuantite1.getValue())> (Commande.getQuantite())) {
          */  Commande.setQuantite((int) NewQuantite1.getValue());
           /* Commande.getId_produit().setQuantite(   (Commande.getId_produit().getQuantite())
                                                                -
                                                (    ((int) NewQuantite1.getValue())));
            */
            //sc.ModifierQuantite(Commande);
             btnConfirmerClicked = true;
        dialogstage.close();/*
        } else if  ((int) NewQuantite1.getValue()< Commande.getQuantite()){
            Commande.setQuantite((int) NewQuantite1.getValue());
            Commande.getId_produit().setQuantite((Commande.getId_produit().getQuantite())+ (Commande.getId_produit().getQuantite())-((int) NewQuantite1.getValue()));
            
            sc.ModifierQuantite(Commande);
             btnConfirmerClicked = true;
        dialogstage.close();
        }
        else sc.ModifierQuantite(Commande);
       Commande.setQuantite(Integer.valueOf(NewQuantite.getText()));
        btnConfirmerClicked = true;
        dialogstage.close();*/
    }
    @FXML
    public void Annuler() {
        dialogstage.close();
    }
}
