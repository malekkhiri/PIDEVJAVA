/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AcceuilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox parent;
    
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label produits;
    @FXML
    private Label veterinaires;
    @FXML
    private Label evenements;
    @FXML
    private ImageView vet;
    @FXML
    private Label vete;
    @FXML
    private ImageView pan;
    @FXML
    private Label pani;
    @FXML
    private ImageView enc;
    @FXML
    private Label ench;
    @FXML
    private ImageView sto;
    @FXML
    private Label stor;
    @FXML
    private ImageView ser;
    @FXML
    private Label serv;
    @FXML
    private ImageView eve;
    @FXML
    private Label even;
    @FXML
    private Label espace;
        @FXML
    private AnchorPane pane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void minimize(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    private void close(ActionEvent event) {
        System.exit(0);
    }

//    private void open_mails(MouseEvent event) throws IOException {
//        Parent fxml = FXMLLoader.load(getClass().getResource("/com/views/MailsUi.fxml"));
//        parent.getChildren().removeAll();
//        parent.getChildren().setAll(fxml);   
//    }

    private void RubriqueMagasin(MouseEvent event) throws IOException {

    }

    @FXML
    private void Menu(MouseEvent event) {
       
        MenuController menu = new MenuController();
        menu.GestionMenu(event);
              
    }
    
@FXML
    private void Produit (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }
    
    @FXML
    private void ventelib (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }
    
    @FXML
    private void Magasin (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("MagasinFront.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }}
    
}
