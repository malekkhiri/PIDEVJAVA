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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.GestionMagasin;

/**
 * FXML Controller class
 *
 * @author Hosni
 */
public class ValiderMagasinController implements Initializable {

    @FXML
    private ImageView imageViewP;
    @FXML
    private HBox ev;
    @FXML
    private TableView<Magasin> TableViewM;
    private TableColumn<?, ?> PropMagasin;
    @FXML
    private Label NomMagasin;
    @FXML
    private Label AdresseM;
    @FXML
    private Button ValiderM;
    @FXML
    private Button SupprimerM;
    @FXML
    private Label RegionM;
    @FXML
    private Label VilleM;
      private List<Magasin> listMagasin;
    private ObservableList<Magasin> observablelistmagasin;
    @FXML
    private TableColumn<?, ?> PropMagasinC;
    @FXML
    private TableColumn<?, ?> NomMagasinC;
    @FXML
    private TableColumn<?, ?> AdresseMC;
    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ListeMagasin();
            TableViewM.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->selectAction(newValue));
        } catch (SQLException ex) {
            Logger.getLogger(ValiderMagasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void ListeMagasin() throws SQLException{
        
     
        NomMagasinC.setCellValueFactory(new PropertyValueFactory<>("Nom_magasin"));
        PropMagasinC.setCellValueFactory(new PropertyValueFactory<>("Prop_Magasin"));
        AdresseMC.setCellValueFactory(new PropertyValueFactory<>("Adresse_magasin"));
         GestionMagasin gm=new GestionMagasin();
       
        listMagasin=gm.AfficherMagasinV();
        
TableViewM.setItems(FXCollections.observableArrayList(listMagasin));
    }
 
 private void selectAction(Magasin magasin){
    NomMagasin.setText(magasin.getNom_magasin());
     AdresseM.setText(String.valueOf(magasin.getAdresse_magasin()));
          RegionM.setText(String.valueOf(magasin.getRegion()));

               VilleM.setText(String.valueOf(magasin.getVille()));

     
     
 
 }

     
      

 
      
     
   @FXML
 
     private void handleSuppBtn() throws IOException, SQLException{
     Magasin magasin= (Magasin) TableViewM.getSelectionModel().getSelectedItem();
    
           if (magasin!=null)
     {GestionMagasin gm = new GestionMagasin();
         gm.SupprimerMagasin(magasin);
         ListeMagasin();
     }

     }
     
     @FXML
 
     private void handleValideBtn() throws IOException, SQLException{
     Magasin magasin=(Magasin) TableViewM.getSelectionModel().getSelectedItem();
    
           if (magasin!=null)
     {
        magasin.setValidated(1);
         GestionMagasin gm=new GestionMagasin();
        gm.ModifierMagasinV(magasin);
        ListeMagasin();
         System.out.println(magasin.getValidated());
     }

     }
     
     
     @FXML
    private void ClickProduit(MouseEvent event) throws IOException {

 try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }
}
