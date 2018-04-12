/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Produit;
import Entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ValiderPController implements Initializable {

   
    @FXML
    private TableView<Produit> TableViewP;

    @FXML
    private TableColumn<Produit, String> NomP;

    @FXML
    private TableColumn<Produit,String > PrixP;


    @FXML
    private Label LPNom;

    @FXML
    private Label LPPrix;

    @FXML
    private Label LPQ;

    @FXML
    private Label LPDes;

     
    @FXML
    private ImageView imageViewP;
    private Label lidp;
        private int idp;
    private List<Produit> listProduit;
    private ObservableList<Produit> observablelistproduit;
    @FXML
    private HBox ev;
    @FXML
    private TableColumn<?, ?> pseudoV;
    @FXML
    private Button btnRetour2;
    @FXML
    private Button btnRetour1;
    @FXML
    private Label nvendeur;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ListeProduit();
            TableViewP.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{
                try {
                    selectAction(newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(ValiderPController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(ValiderPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void ListeProduit() throws SQLException{
        
     
        NomP.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));
        PrixP.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        pseudoV.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        ServiceProduit Sp=new ServiceProduit();
       
        listProduit=Sp.selectProduitNV();
        
        observablelistproduit=FXCollections.observableArrayList(listProduit);
        TableViewP.setItems(observablelistproduit);
        
    }
 
 private void selectAction(Produit produit) throws SQLException{
     LPNom.setText(produit.getNom_Produit());
     LPPrix.setText(String.valueOf(produit.getPrix()));
     LPQ.setText(String.valueOf(produit.getQuantite()));
     LPDes.setText(String.valueOf(produit.getDescription()));
     lidp.setText(String.valueOf(produit.getId_Produit()));
     ServiceProduit sc= new ServiceProduit();
     nvendeur.setText(String.valueOf(produit.getId_utilisateur()));
     
 
 }
 private boolean ajoutAction(Produit produit) throws IOException{
   FXMLLoader loader =new FXMLLoader();
  loader.setLocation(AjoutDocumentController.class.getResource("AjoutDocument.fxml"));
  AnchorPane pane=(AnchorPane) loader.load();
       Stage dialogStage=new Stage();
  dialogStage.setTitle("Ajouter Un Produit");
     Scene scene = new Scene(pane);
     dialogStage.setScene(scene);
     dialogStage.setResizable(false);
          AjoutDocumentController controller=loader.getController();
     controller.setDialogStage(dialogStage);
     controller.setProduit(produit);
   
     
 dialogStage.showAndWait();
 return controller.isBtnClicked();

 }
 private void handleAjoutBtn() throws IOException, SQLException{
Produit produit=new Produit();
     boolean btnClicked=ajoutAction(produit);
     if(btnClicked){
   
ListeProduit(); 
     } }
     
  private boolean modifAction(Produit produit) throws IOException{
   FXMLLoader loader =new FXMLLoader();
  loader.setLocation(AjoutDocumentController.class.getResource("ModifierDocument.fxml"));
  AnchorPane pane=(AnchorPane) loader.load();
       Stage dialogStage=new Stage();
  dialogStage.setTitle("Modifier Un Produit");
     Scene scene = new Scene(pane);
     dialogStage.setScene(scene);
     dialogStage.setResizable(false);
          ModifierDocumentController controller=loader.getController();
     controller.setDialogStage(dialogStage);
     controller.setProduit(produit);
   
     
 dialogStage.showAndWait();
 return controller.isBtnClicked();

 }
 
     private void handleModifierBtn() throws IOException, SQLException{
     
 Produit produit=TableViewP.getSelectionModel().getSelectedItem();

     if (produit!=null)
     {
              boolean btnClicked=modifAction(produit);         
        if(btnClicked){
            
            ServiceProduit sc=new ServiceProduit();
            sc.updateProduit(produit);
            System.out.println(produit.getNom_Produit());
        ListeProduit();
                   
        }
        
               
         
     }
 
     
     
     
 }
     
   @FXML
 
     private void handleSuppBtn() throws IOException, SQLException{
     Produit produit=TableViewP.getSelectionModel().getSelectedItem();
    
           if (produit!=null)
     {ServiceProduit Sc = new ServiceProduit();
         Sc.supprimerProduit(produit);
         ListeProduit();
     }

     }
     
     @FXML
 
     private void handleValideBtn() throws IOException, SQLException{
     Produit produit=TableViewP.getSelectionModel().getSelectedItem();
    
           if (produit!=null)
     {
        produit.setValidated(1);
        ServiceProduit Sc=new ServiceProduit();
        Sc.updatePV(produit);
         System.out.println(produit.getId_utilisateur());
        ListeProduit();
         System.out.println(produit.getValidated());
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
