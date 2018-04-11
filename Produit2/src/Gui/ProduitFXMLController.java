/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.mysql.jdbc.Connection;
import Entity.Commande;
import Entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceCommande;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class ProduitFXMLController implements Initializable {
    private List<Produit> listC;
    private ObservableList<Produit> observablelistProduit = FXCollections.observableArrayList();
    @FXML
    private Button btnAdd;
    @FXML
    private TableColumn<Produit,String> TableViewColumnNomProduit;
    @FXML
    private TableColumn<Produit,String> TableViewColumnPrixProduit;
    @FXML
    private TableColumn<Produit,String> TableViewColumnQuantiteProduit;
    @FXML
    private TableColumn<Produit,String> TableViewColumnDescriptionProduit;
    @FXML
    private Button bntAfficher;
    @FXML
    private TableView<Produit> ListeProduit;
    private final ServiceCommande ServiceCommande = new ServiceCommande();

    /**
     * Initializes the controller class.
     */
        Connection conn;
    Statement statement;
    ResultSet rs;
    @FXML
    private Button btnSuppimer;
    @FXML
    private Button btnPanier;
    @FXML
    private Button btnRetour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnAdd.setVisible(false);
        btnSuppimer.setVisible(false);
      
      
       
    
    }
    
    public void selecionarItemTableViewClientes(Produit p) throws SQLException
 {
    // System.out.println(Commande.toString());
          if (p != null) {
           ServiceCommande sc = new ServiceCommande();
           boolean t =sc.findproduit(p);
           if (t){
           btnAdd.setVisible(true);
           btnSuppimer.setVisible(false);
           }
           else btnAdd.setVisible(false);
           btnSuppimer.setVisible(true);
           
        } else {
              System.out.println("test");
        }
         
         
 }

 

    @FXML
    private void Afficher(ActionEvent event) {
                 TableViewColumnNomProduit.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));
           TableViewColumnPrixProduit.setCellValueFactory(new PropertyValueFactory<>("Prix"));
              TableViewColumnDescriptionProduit.setCellValueFactory(new PropertyValueFactory<>("Description"));
             TableViewColumnQuantiteProduit.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        
         
        
        listC = ServiceCommande.afficherproduit();
        
        observablelistProduit = FXCollections.observableArrayList(listC);
        ListeProduit.setItems(observablelistProduit);
      //  produit produit = new produit();
 
              ListeProduit.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                     try {
                         selecionarItemTableViewClientes(newValue);
                     } catch (SQLException ex) {
                         Logger.getLogger(ProduitFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 });
               
    }
       @FXML
    private void Add(ActionEvent event) throws SQLException  {
                     Produit Produit=ListeProduit.getSelectionModel().getSelectedItem();
              //          System.out.println(Produit.toString());
if (Produit.getQuantite()==0)
{
       
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("MAFAMESH MENHA A5R KA3BA TESHRET ");
            alert.show();
}
             
          

        
    else
    
        {
            ServiceCommande sc = new ServiceCommande ();
             sc.insertCommande(Produit);
             btnSuppimer.setVisible(true);
             btnAdd.setVisible(false);
    }   
this.Afficher(event); 

    }
    
  /*     public void Ajouterpanier () throws SQLException

{
//String req="insert into Commande (id_Produit) values (?)";

//PreparedStatement prs=conn.prepareStatement(req);

/*int idp;
        idp = prs.setInt(1,p.getId_Produit());
*/
//prs.executeUpdate();


/*int idp = ListeProduit.getSelectionModel().getSelectedItem().getId_Produit();

 Commande commande;
        commande = new Commande(idp);
         ServiceCommande sc= new ServiceCommande();
         sc.insertCommande(commande);


}*/

    @FXML
    private void supprimer(ActionEvent event) {
        
         Produit produit=ListeProduit.getSelectionModel().getSelectedItem();
        if(produit != null){
            ServiceCommande Sc=new ServiceCommande();
            Sc.supprimer(produit);
           btnAdd.setVisible(true);
           
            btnSuppimer.setVisible(false);
    }
    

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
    private void Retour(ActionEvent event) throws IOException {
               Stage stage = (Stage) btnRetour.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PofilClientFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }
}
