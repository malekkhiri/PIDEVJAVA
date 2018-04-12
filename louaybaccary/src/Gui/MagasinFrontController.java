/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Magasin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.GestionMagasin;

/**
 * FXML Controller class
 *
 * @author Hosni
 */
public class MagasinFrontController implements Initializable {
private int  iduser;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox parent;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label produits;

    @FXML
    private Label evenements;
    @FXML
    private Label espace;
    @FXML
    private JFXButton btnVend;
    @FXML
    private JFXDrawer Drawer;
    @FXML
    private VBox produitliste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NewFXMain1 main = new NewFXMain1();
        iduser = main.u.getId();
          System.out.println(iduser);
         if (main.u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")) {
            btnVend.setVisible(true);
            
        }
        else{
            btnVend.setVisible(false);
        }
           
             service.GestionMagasin gm = new GestionMagasin();
        List<Magasin> le = gm.AfficherMagasin();
        for (Magasin m : le) {
            
           
                
                try {
                    FXMLLoader x = new FXMLLoader(getClass().getResource("MagasinVbo.fxml"));
                    Node pane = x.load();
                    MagasinVboController c = x.getController();
                    c.setContent(m);
                    
                    produitliste.getChildren().add(pane);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }}}

        // TODO
        


    

     @FXML
    private void ClickGMagasin(ActionEvent event) throws IOException {

 try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("GestionMagasinInterface.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }


     @FXML
    private void ClickMenu(MouseEvent event) throws IOException {

 try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }
}

