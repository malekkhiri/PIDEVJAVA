/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DataBase.DataSource;
import Entity.Magasin;
import service.GestionMagasin;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceCommentaire;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author Hosni
 */
public class GestionMagasinInterfaceController implements Initializable {

    @FXML
    private TableView<Magasin> tableViewMagasin;
    @FXML
    private TableColumn<Magasin,String> tableColumnNom;
    @FXML
    private TableColumn<Magasin,String> tableColumnAdresse;
    @FXML
    private TableColumn<Magasin,String> tableColumnRegion;
    @FXML
    private TableColumn<Magasin,String> tableColumnVille;
    @FXML
    private Label labelMagasinNom;
    @FXML
    private Label labelMagasinAdresse;
    @FXML
    private Label labelMagasinRegion;
    @FXML
    private Label labelMagasinVille;
    @FXML
    private Button buttonAjouter;
    @FXML
    private Button buttonModifier;
    @FXML
    private Button buttonSupprimer;
    
    private List<Magasin> listMagasin;
    private ObservableList<Magasin> observableListMagasin;
    
    private final  Connection con = DataSource.getInstance().getConnection();
    private final GestionMagasin gm = new GestionMagasin();
    @FXML
     
    private TextField searchbox;
    @FXML
private TableColumn<Magasin, Integer> tableColumnId;
    @FXML
    private TableColumn<?, ?> tableColumnTelephone;
    @FXML
    private Label labelMagasinTelephone;
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherTableViewMagasin() ;
        tableViewMagasin.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> selectionnerItemTableViewMagasin(newValue));
}    
    
     public void AfficherTableViewMagasin() {
        tableColumnNom.setCellValueFactory(new PropertyValueFactory<>("Nom_magasin"));
        tableColumnAdresse.setCellValueFactory(new PropertyValueFactory<>("Adresse_magasin"));
        tableColumnRegion.setCellValueFactory(new PropertyValueFactory<>("Region"));
        tableColumnVille.setCellValueFactory(new PropertyValueFactory<>("Ville"));
        tableColumnTelephone.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id_magasin"));
        NewFXMain1 main=new NewFXMain1();
        listMagasin = gm.AfficherMagasinUse(main.u.getId());
        observableListMagasin = FXCollections.observableArrayList(listMagasin);
        tableViewMagasin.setItems(observableListMagasin);
    }
     
      public void selectionnerItemTableViewMagasin(Magasin magasin){
        if (magasin != null) {
            labelMagasinNom.setText(String.valueOf(magasin.getNom_magasin()));
            labelMagasinAdresse.setText(magasin.getAdresse_magasin());
            labelMagasinRegion.setText(magasin.getRegion());
            labelMagasinVille.setText(magasin.getVille());
            labelMagasinTelephone.setText(magasin.getTelephone());
            
        } else {
            labelMagasinNom.setText("");
            labelMagasinAdresse.setText("");
            labelMagasinRegion.setText("");
            labelMagasinVille.setText("");
            labelMagasinTelephone.setText("");
        }
        }
      
      
      @FXML
    public void handleButtonAjouter() throws IOException {
        Magasin magasin = new Magasin();
        boolean buttonConfirmerClicked = showGestionMagasinManipulation(magasin);
        if (buttonConfirmerClicked) {
            AfficherTableViewMagasin();
        }}
             
    

    @FXML
    public void handleButtonModifier() throws IOException {
        Magasin magasin = tableViewMagasin.getSelectionModel().getSelectedItem();
        System.out.println(magasin.getId_magasin() + " id ");
        if (magasin != null) {
            boolean buttonConfirmerClicked = showGestionMagasinModif(magasin);
            if (buttonConfirmerClicked) {
                GestionMagasin gm=new GestionMagasin();
                gm.ModifierMagasin(magasin);
                AfficherTableViewMagasin();
                System.err.println(magasin.getNom_magasin());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choisir une magasin");
            alert.show();
        }
    }
  
    @FXML
    public void handleButtonSupprimer() throws IOException {
        Magasin magasin = tableViewMagasin.getSelectionModel().getSelectedItem();
        service.ServiceCommentaire sc=new ServiceCommentaire();
        if (magasin != null) {
            gm.SupprimerMagasin(magasin);
          
                    
            
            
            AfficherTableViewMagasin();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choisir une magasin");
            alert.show();
        }
    }
 public boolean showGestionMagasinManipulation(Magasin magasin) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionMagasinInterfaceController.class.getResource("GestionMagasinManipulation.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Magasin");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        GestionMagasinManipulationController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMagasin(magasin);
        dialogStage.showAndWait();

        return controller.isButtonConfirmerClicked();

    }
 
 public boolean showGestionMagasinModif(Magasin magasin) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionMagasinManipulationController.class.getResource("GestionMagasinModif.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Magasin");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        GestionMagasinManipulationController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMagasin(magasin);

        dialogStage.show();

        return controller.isButtonConfirmerClicked();

    }
 
      
 @FXML
    private void searchRecord(KeyEvent event) {
        
        FilteredList<Magasin> filterData=new FilteredList<>(observableListMagasin,p->true);
        searchbox.textProperty().addListener((observable, oldValue, newValue)->{
            filterData.setPredicate((t) -> {
                
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String typedText=newValue.toLowerCase();
              if (t.getNom_magasin().toLowerCase().indexOf(typedText)!=-1) {
                   return true;
              }
               if (t.getAdresse_magasin().toLowerCase().indexOf(typedText)!=-1) {
                   return true;
              }
                if (t.getRegion().toLowerCase().indexOf(typedText)!=-1) {
                   return true;
                   
              }
                  if (t.getVille().toString().indexOf(typedText)!=-1) {
                   return true;
                   
              }
                  
                  if (t.getTelephone().toString().indexOf(typedText)!=-1) {
                   return true;
                   
              }
                   
              return false;
            }
            );
            SortedList<Magasin>sortedList=new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewMagasin.comparatorProperty());
            tableViewMagasin.setItems(sortedList);
        });
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
