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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceGestionProfil;
import Gui.LoginFXMLController;
import static Gui.LoginFXMLController.userconnecte;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class PofilClientFXMLController implements Initializable {

    @FXML
    private ComboBox<String> comboVille;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnEffacer;
    @FXML
    private Button btnDeconnecter;
    @FXML
    private Button btnCatalogue;
    @FXML
    private Button btnEvenement;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldNumTel;
    @FXML
    private ComboBox<String> comboProduitPrefere;
    @FXML
    private Button btnPanier;
    @FXML
    private Button btnupgrade;
    @FXML
    private Button btnchangermdp;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceGestionProfil sg = new ServiceGestionProfil();
       User us = new User();
        try {
            us=sg.recupererUtilisateur(userconnecte.getUsername());
            System.out.print(userconnecte.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(PofilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textFieldUsername.setText(us.getUsername());
        textFieldNumTel.setText(String.valueOf(us.getNumTel()));
        comboVille.setValue(us.getVille());
        comboProduitPrefere.setValue(us.getProduitPreferee());
        
                ArrayList<String> ListeVilles = new ArrayList<>();
        ArrayList<String> ListeProduitPrefere = new ArrayList<>();
        ListeVilles.add("Ariana");
        ListeVilles.add("Beja");
        ListeVilles.add("Ben Arous");
        ListeVilles.add("Bizerte");
        ListeVilles.add("Gabes");
        ListeVilles.add("Gafsa");
        ListeVilles.add("Jendouba");
        ListeVilles.add("Kairouan");
        ListeVilles.add("Kasserine");
        ListeVilles.add("Kebili");
        ListeVilles.add("La Manouba");
        ListeVilles.add("Le Kef");
        ListeVilles.add("Mahdia");
        ListeVilles.add("Medenine");
        ListeVilles.add("Monastir");
        ListeVilles.add("Nabeul");
        ListeVilles.add("Sfax");
        ListeVilles.add("Sidi Bouzid");
        ListeVilles.add("Siliana");
        ListeVilles.add("Sousse");
        ListeVilles.add("Tataouine");
        ListeVilles.add("Tozeur");
        ListeVilles.add("Tunis");
        ListeVilles.add("Zaghouan");
        ObservableList<String> ComboData = FXCollections.observableArrayList(ListeVilles);
        for(String s : ComboData){
            comboVille.getItems().add(s);
        }
        
        ListeProduitPrefere.add("Meuble");
        ListeProduitPrefere.add("Accessoires");
        ListeProduitPrefere.add("Vetement");
        ObservableList<String> ComboData1 = FXCollections.observableArrayList(ListeProduitPrefere);
        for(String s : ComboData1){
            comboProduitPrefere.getItems().add(s);
        }
        // TODO
         btnEffacer.setVisible(false);
       btnModifier.setVisible(false);
        textFieldUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            btnModifier.setVisible(true);
            btnEffacer.setVisible(true);
        });
         textFieldNumTel.textProperty().addListener((observable, oldValue, newValue) -> {
            btnModifier.setVisible(true);
            btnEffacer.setVisible(true);
        });
         comboVille.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
           btnModifier.setVisible(true);
           btnEffacer.setVisible(true);
    }
    ); 
              comboProduitPrefere.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
           btnModifier.setVisible(true);
           btnEffacer.setVisible(true);
    }
    );
    }    

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        
      
      ServiceGestionProfil sg = new ServiceGestionProfil();
      if
        (sg.finduser(textFieldUsername.getText()))
      {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nom Existant ");
            alert.show();
          
      }
      else if((textFieldNumTel.getText().length()==0)||(textFieldNumTel.getText().length()>8))
      {
                           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Numero invalide");
            alert.show();
      }
      else{
          
       User us = new User();
        try {
            us=sg.recupererUtilisateur(userconnecte.getUsername());
          
        } catch (SQLException ex) {
            Logger.getLogger(PofilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
          us.setUsername(textFieldUsername.getText());
            us.setNumTel(Integer.parseInt(textFieldNumTel.getText()));
            us.setVille(comboVille.getValue());
            us.setProduitPreferee(comboProduitPrefere.getValue());
       sg.ModifierClient(us);
       btnModifier.setVisible(false);
       btnEffacer.setVisible(false);
       System.out.print(us.getVille());
    }
    }
    @FXML
    private void Effacer(ActionEvent event) {
            ServiceGestionProfil sg = new ServiceGestionProfil();
       User us = new User();
        try {
            us=sg.recupererUtilisateur(userconnecte.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(PofilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textFieldUsername.setText(us.getUsername());
        textFieldNumTel.setText(String.valueOf(us.getNumTel()));
        comboVille.setValue(us.getVille());
        comboProduitPrefere.setValue(us.getProduitPreferee());
         btnModifier.setVisible(false);
       btnEffacer.setVisible(false);
    }

    @FXML
    private void Deconnecter(ActionEvent event) throws IOException {
                                       Stage stage = (Stage) btnDeconnecter.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LoginFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }


    @FXML
    private void Catalogue(ActionEvent event) throws IOException {
               Stage stage = (Stage) btnCatalogue.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProduitFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }


    @FXML
    private void Evenement(ActionEvent event) {
    }

    @FXML
    private void Reclamation(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
               ServiceGestionProfil sg = new ServiceGestionProfil();
       User us = new User();
        try {
            us=sg.recupererUtilisateur(userconnecte.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(PofilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(us.getId());
        //us.setId(us.getId());
        sg.SupprimerCompte(us);
        
            Stage stage = (Stage) btnsupprimer.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ChoixCompteFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void Panier(ActionEvent event) throws IOException {
               Stage stage = (Stage) btnPanier.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PanierFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void upgrade(ActionEvent event) throws IOException {
              ServiceGestionProfil sg = new ServiceGestionProfil();
       User us = new User();
        try {
            us=sg.recupererUtilisateur(userconnecte.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(PofilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sg.ChangerRole(us);
           Stage stage = (Stage) btnPanier.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfilVendeurFXML.fxml")));
            window.setScene(scene);  
             window.show();
        
    }

    @FXML
    private void changermdp(ActionEvent event) throws IOException {
             Stage stage = (Stage) btnPanier.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ChangerMotDePasseClientFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }
    
}
