/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.LoginFXMLController.userconnecte;
import com.itextpdf.text.BadElementException;
import Entity.Commande;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class ConfirmationVendeurFXMLController implements Initializable {
     private List<Commande> listC;
    @FXML
    private Button btnRetour;
    @FXML
    private TableView<Commande> tableViewCommande;
    @FXML
    private TableColumn<Commande, String> NomProduit;
    @FXML
    private TableColumn<Commande, String> QuantiteStock;
    @FXML
    private TableColumn<Commande, String> Quantite;
    @FXML
    private TableColumn<Commande, String> Prix;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnRefuser;
    private TableColumn<Commande,String> NomClient;
    @FXML
    private Button btnafficher;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TableColumn<?, ?> Statut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableViewCommande.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
       btnAnnuler.setVisible(false);
        ServiceCommande sc =new ServiceCommande();
         listC = sc.afficherVendeur();

        ObservableList data = FXCollections.observableArrayList(listC);
        tableViewCommande.setItems(data);
        
       //    idProduit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
         //  idutilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
          NomProduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getNom_Produit()) );
                }
            });
          Prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getPrix()) );
                }
            });
       //   TableViewColumnPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
          QuantiteStock.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getQuantite()) );
                }
            });
         /* TableViewColumnQuantite.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getQuantite()) );
                }
            });*/
        Quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
         Statut.setCellValueFactory(new PropertyValueFactory<>("Statut"));
    }    
       @FXML
    private void Afficher(ActionEvent event) {
        ServiceCommande sc =new ServiceCommande();
         listC = sc.afficherVendeur();

        ObservableList data = FXCollections.observableArrayList(listC);
        tableViewCommande.setItems(data);
        
       //    idProduit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
         //  idutilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
          NomProduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getNom_Produit()) );
                }
            });
          Prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getPrix()) );
                }
            });
       //   TableViewColumnPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
          QuantiteStock.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getQuantite()) );
                }
            });
         /* TableViewColumnQuantite.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getQuantite()) );
                }
            });*/
        Quantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
         Statut.setCellValueFactory(new PropertyValueFactory<>("Statut"));
       
    }
   public void selecionarItemTableViewClientes(Commande Commande)
 {
    // System.out.println(Commande.toString());
          if (Commande != null) {
               ServiceCommande sc = new ServiceCommande();
                                      if (!sc.isstatut(Commande))
                 {
                btnAnnuler.setVisible(true);
        System.out.print("ok");
                    }
                    else {
                        btnAnnuler.setVisible(false);
                      
           System.out.print("ok111");
 }
          }
 }
    @FXML
    private void Retour(ActionEvent event) throws IOException {
             Stage stage = (Stage) btnRetour.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfilVendeurFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void Valider(ActionEvent event) throws SQLException, BadElementException, IOException {
           Commande commande=tableViewCommande.getSelectionModel().getSelectedItem();
          // System.out.println(commande.getId_user().getId());
        if(commande != null){
            ServiceCommande Sc=new ServiceCommande();
            Sc.validervendeur(commande);
            this.Afficher(event);
    }
    }

    @FXML
    private void Refuser(ActionEvent event) {
           Commande commande=tableViewCommande.getSelectionModel().getSelectedItem();
        if(commande != null){
            ServiceCommande Sc=new ServiceCommande();
            Sc.refuservendeur(commande);
            this.Afficher(event);
    }
    }

    @FXML
    private void Annuler(ActionEvent event) throws SQLException {
              Commande commande=tableViewCommande.getSelectionModel().getSelectedItem();
        if(commande != null){
            ServiceCommande Sc=new ServiceCommande();
            Sc.AnnulerCommande(commande);
            Sc.supprimerCommande(commande);
            this.Afficher(event);
    }
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
    
    
    @FXML
    private void Evenement (MouseEvent event) throws IOException
    {
        
        
      
           NewFXMain1 main= new NewFXMain1();
           try{
           if(main.u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")){
                
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("Vendeur.fxml"));
                 Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
      
           }
              
     
           else if (main.u.getRoles().equals("a:1:{i:0;s:16:\"ROLE_UTILISATEUR\";}")){
              
                             Parent home_page_parent = FXMLLoader.load(getClass().getResource("GestionEvenementnterfaceController.fxml"));
  Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
           }                  
           else{
               System.out.println("erreur");
           }
             
       
    
    } catch (IOException ex) {
           
               }    
    }
    
    
    
    @FXML
    private void Panier (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("PanierFXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
}


@FXML
    private void Profil (MouseEvent event) throws IOException
    {
        
        
      
           NewFXMain1 main= new NewFXMain1();
           try{
           if(main.u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")){
                
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("ProfilVendeurFXML.fxml"));
                 Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
      
           }
              
     
           else if (main.u.getRoles().equals("a:1:{i:0;s:16:\"ROLE_UTILISATEUR\";}")){
              
                             Parent home_page_parent = FXMLLoader.load(getClass().getResource("PofilClientFXML.fxml"));
  Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
           }                  
           else{
               System.out.println("erreur");
           }
             
       
    
    } catch (IOException ex) {
           
               }    
    }
    

  @FXML
    private void Reclamation (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("ReclamationRS.fxml"));
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
