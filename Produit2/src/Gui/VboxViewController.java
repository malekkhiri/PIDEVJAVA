/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Produit;
import java.io.File;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author HP
 */

public class VboxViewController implements Initializable {
    @FXML
    private AnchorPane vbox;

    @FXML
    private Label nomP;

    @FXML
    private Label PrixP;

    @FXML
    private Label DescP;
    @FXML
    private Label id;
        @FXML
      
    private javafx.scene.image.ImageView  imageP;

    public ImageView getImageP() {
        return imageP;
    }

    public void setImageP(ImageView imageP) {
        this.imageP = imageP;
    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setContent(Produit p) {
        
      this.nomP.setText(p.getNom_Produit());
      this.PrixP.setText(String.valueOf(p.getPrix()));
      this.DescP.setText(p.getDescription());
      this.id.setText(String.valueOf(p.getId_Produit()));
       
      
   
        File file = new File("C:/wamp64/www/Final/PIDEV/web/bundles/uploads/brochures/"+p.getBrochure());

        Image i = new Image(file.toURI().toString(),147,174,false,false) {};
this.imageP.setImage(i);
        System.out.println(file);

    }
    
    @FXML
    void afficherDetaille(MouseEvent event) throws SQLException {
        service.ServiceProduit sc=new ServiceProduit();
        String i =id.getText();
        System.out.println(i);
        NewFXMain1 main = new NewFXMain1();
        main.setP(sc.selectProduitDet(Integer.parseInt(i)));
        Stage s=new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("SingleProduct.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        s.initStyle(StageStyle.UNDECORATED);
//        s.setWidth(410);
//        s.setHeight(700 );
        s.setScene(new Scene(root));
        s.show();
    }

     
    
}
