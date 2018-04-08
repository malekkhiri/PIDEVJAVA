/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Commentaire;
import Entity.Produit;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.beans.EventHandler;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ServiceProduit;


/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {
    

    
  

  @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger Hamburger;

    @FXML
    private JFXDrawer Drawer;
        @FXML
    private VBox produitliste;
        private Produit produit;
//  @FXML
//    private Button ProduitG;
//      @FXML
//    private void produitaction(EventHandler even) throws IOException{
//        AnchorPane p= FXMLLoader.load(getClass().getResource("AjoutDocument.fxml"));
//     anchorPane.getChildren().setAll(p);
//        
//    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            try { 
             service.ServiceProduit Sc = new ServiceProduit();
        List<Produit> le = Sc.selectProduit();
        for (Produit p : le) {
            
           
                
                try {
                    FXMLLoader x = new FXMLLoader(getClass().getResource("VboxView.fxml"));
                    Node pane = x.load();
                    VboxViewController c = x.getController();
                    c.setContent(p);
                    
                    produitliste.getChildren().add(pane);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
                
                VBox box=FXMLLoader.load(getClass().getResource("FXML2Document.fxml"));
                Drawer.setSidePane(box);
                HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(Hamburger);
                burgerTask2.setRate(1);
                Hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e)->{
                    burgerTask2.setRate(burgerTask2.getRate() * 1);
                    burgerTask2.play();
                    
                    if(Drawer.isShown()){
                        Drawer.close();
                    } else{
                        Drawer.open();
                    }
                    
                    
                });
                
        }    
            } catch (IOException ex) {
                     Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
  }
    
    
  
}

