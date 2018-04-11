/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Magasin;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.GestionMagasin;

/**
 * FXML Controller class
 *
 * @author Hosni
 */
public class MagasinVboController implements Initializable {

    @FXML
    private AnchorPane vbox;
    @FXML
    private Label id;
    @FXML
    private Label nomP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void setContent(Magasin m) {
        
      this.nomP.setText(m.getNom_magasin());
    
      this.id.setText(String.valueOf(m.getId_magasin()));
       
      
   
        

    }
    
    @FXML
    void afficherDetaille(MouseEvent event) throws SQLException {
        service.GestionMagasin gm=new GestionMagasin();
        String i =id.getText();
        System.out.println(i);
        NewFXMain1 main = new NewFXMain1();
        main.setM(gm.AfficherMagasinDetail(Integer.parseInt(i)));
        Stage s=new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MagasinDetaille.fxml"));
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
