/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.LoginFXMLController.userconnecte;
import com.mysql.jdbc.Connection;
import Entity.Commande;
import Entity.User;
import Entity.Produit;
import Entity.Reclamation;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ServiceCommande;
import service.ServiceGestionProfil;
import service.ServiceReclamation;
import services.SendMail;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class PanierFXMLController implements Initializable {
    private List<Commande> listC;
    private ObservableList<Commande> observablelistCommande = FXCollections.observableArrayList();
    @FXML
    private TableView<Commande> ListeCommande;
    @FXML
    private Button BtnSupprimer;
    @FXML
    private Button btnPayer;
    @FXML
    private Button btnVider;
    @FXML
    private Button btnDeconnecter;
    @FXML
    private Button btnPdf;
    @FXML
    private Button btnRetour;
    private TableColumn<Commande,String> idProduit;
    @FXML
    private Button btnAfficher;
    public int test;
    public Produit ptest;
    
    /**
     * Initializes the controller class.
     */
    private ServiceCommande ServiceCommande = new ServiceCommande();
    private TableColumn<Commande,String> idutilisateur;
    @FXML
    private TableColumn<Commande,String> TableViewColumnNomProduit;
    @FXML
    private TableColumn<Commande,String> TableViewColumnPrix;
    @FXML
    private TableColumn<Commande,String> TableViewColumnDescription;
    @FXML
    private TableColumn<Commande,String> TableViewColumnQuantite;
    @FXML
    private Label ModifNom;
    @FXML
    private Label ModifQuantiteStock;
    @FXML
    private Label ModifQuantite;
    @FXML
    private Label ModifPrix;
    @FXML
    private Spinner  NewQuantite;
    @FXML
    private Button Appliquer;
    @FXML
    private Button btnCatalogue;
    @FXML
    private TableColumn<Commande,String> TableViewColumnStatut;
    public static Commande commandelocale;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListeCommande.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
        Appliquer.setVisible(false);
        ////////////////////////////
         listC = ServiceCommande.afficher();

        ObservableList data = FXCollections.observableArrayList(listC);
        ListeCommande.setItems(data);
        
       //    idProduit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
         //  idutilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
          TableViewColumnNomProduit.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getNom_Produit()) );
                }
            });
          TableViewColumnPrix.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getPrix()) );
                }
            });
       //   TableViewColumnPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
          TableViewColumnDescription.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getDescription()) );
                }
            });
         /* TableViewColumnQuantite.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getQuantite()) );
                }
            });*/
        TableViewColumnQuantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
         TableViewColumnStatut.setCellValueFactory(new PropertyValueFactory<>("Statut"));
        btnPdf.setVisible(false);
      
    }    
     Connection conn;
    Statement statement;
    ResultSet rs;
  

    @FXML
    private void Payer(ActionEvent event){
        Commande Commande = new Commande();
      
      Commande commande=ListeCommande.getSelectionModel().getSelectedItem();
        if(commande != null){
            ServiceCommande.payment(commande);
           
            
        }
    }

    @FXML
    private void Vider(ActionEvent event) {
            Commande Commande = new Commande();
        listC = ServiceCommande.afficher();
        
        for (Commande C : listC){
            
         
            ServiceCommande.ViderCommande(C);
        }
    }

    @FXML
    private void Deconnecter(ActionEvent event) {
    }
    
    @FXML
    private void Pdf(ActionEvent event) throws IOException {
        commandelocale=ListeCommande.getSelectionModel().getSelectedItem();
            ServiceGestionProfil sg = new ServiceGestionProfil();
           User us = new User();
        
        try {
            us=sg.recupererUtilisateur(userconnecte.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmationPdfFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(userconnecte.getEmail());
           Stage stage = (Stage) btnCatalogue.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ConfirmationPdfFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
            Stage stage = (Stage) btnCatalogue.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProduitFXML.fxml")));
            window.setScene(scene);  
             window.show();
        
    }

    @FXML
    private void Afficher(ActionEvent event) {
         listC = ServiceCommande.afficher();

        ObservableList data = FXCollections.observableArrayList(listC);
        ListeCommande.setItems(data);
          TableViewColumnNomProduit.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getNom_Produit()) );
                }
            });
          TableViewColumnPrix.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getPrix()) );
                }
            });
       //   TableViewColumnPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
          TableViewColumnDescription.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getDescription()) );
                }
            });
         /* TableViewColumnQuantite.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getQuantite()) );
                }
            });*/
        TableViewColumnQuantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        TableViewColumnStatut.setCellValueFactory(new PropertyValueFactory<>("Statut"));
    }
    
    @FXML
    private void supprimer(ActionEvent event) {
        Commande commande=ListeCommande.getSelectionModel().getSelectedItem();
        if(commande != null){
            ServiceCommande Sc=new ServiceCommande();
            Sc.supprimerCommande(commande);
           
            
        }
     this.Afficher(event);
    }
    
 public void selecionarItemTableViewClientes(Commande Commande)
 {
    // System.out.println(Commande.toString());
          if (Commande != null) {
               ServiceCommande sc = new ServiceCommande();
                                      if (sc.isstatut(Commande))
                 {
                btnPdf.setVisible(true);
        
                    }
                    else {
                        btnPdf.setVisible(false);
                      
                         }
            ModifNom.setText(Commande.getId_produit().getNom_Produit());
            ModifQuantiteStock.setText(String.valueOf(Commande.getId_produit().getQuantite()));
            ModifQuantite.setText(String.valueOf(Commande.getQuantite()));
            //NewQuantite
            SpinnerValueFactory<Integer> NewQuantiteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Commande.getId_produit().getQuantite(), Commande.getQuantite());
            this.NewQuantite.setValueFactory(NewQuantiteFactory);
            NewQuantite.setEditable(true);
            ModifPrix.setText(String.valueOf(Commande.getId_produit().getPrix()*Commande.getQuantite()));
            test = Commande.getId();
            ptest = Commande.getId_produit();
           
          
        } else {
            ModifNom.setText("");
            ModifQuantiteStock.setText("");
            ModifQuantite.setText("");
            ModifPrix.setText("");
        }
          NewQuantite.valueProperty().addListener((obs,old,newv)->{
          Appliquer.setVisible(true);
          });
         
 }

    private void Modifier() throws IOException, SQLException{
        Commande Commande = ListeCommande.getSelectionModel().getSelectedItem();
        if (Commande!=null){
            boolean btnConfirmerClicked=showModifQuantiteFXMLdialog(Commande);
            if (btnConfirmerClicked)
            {   
                if (Commande.getId_produit().getQuantite()<Commande.getQuantite())
{
       
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Meklin ednya wmesa7rin bele2ra ");
            alert.show();
}
                else{
                ServiceCommande sc = new ServiceCommande();
                sc.ModifierQuantite(Commande);
                //////////////////////////////
                      listC = ServiceCommande.afficher();

        ObservableList data = FXCollections.observableArrayList(listC);
        ListeCommande.setItems(data);
        
       //    idProduit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
         //  idutilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
          TableViewColumnNomProduit.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getNom_Produit()) );
                }
            });
          TableViewColumnPrix.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getPrix() ) );
                }
            });
       //   TableViewColumnPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
          TableViewColumnDescription.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getId_produit().getDescription()) );
                }
            });
         /* TableViewColumnQuantite.setCellValueFactory(new Callback<CellDataFeatures<Commande,String>,ObservableValue<String>>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
                    return new SimpleStringProperty( String.valueOf(param.getValue().getQuantite()) );
                }
            });*/
        TableViewColumnQuantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
                
            }
        }
        else
        {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Inserer une ");
            alert.show();
    }
    }
    }

    private boolean showModifQuantiteFXMLdialog(Commande Commande) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ModifQuantiteFXMLController.class.getResource("/GUI/ModifQuantiteFXML.fxml"));
      //  loader.setLocation(ModifQuantiteFXML.class.getResource("/javafxmvc/view/FXMLAnchorPaneCadastrosClientesDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier Quantite");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        ModifQuantiteFXMLController controller = loader.getController();
        controller.setDialogstage(dialogStage);
        controller.setCommande(Commande);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isBtnConfirmerClicked();
    }
      @FXML
    public void Appliquer(ActionEvent event) throws SQLException
    {   Commande Commande = new Commande();
        Commande.setId(test);
        Commande.setId_produit(ptest);
        ServiceCommande sc = new ServiceCommande();
      /*  if (((int) NewQuantite1.getValue())> (Commande.getQuantite())) {
        
          */
      if ((int) NewQuantite.getValue()<=Commande.getId_produit().getQuantite())
      {
      Commande.setQuantite((int) NewQuantite.getValue());
          sc.ModifierQuantite(Commande);
           /* Commande.getId_produit().setQuantite(   (Commande.getId_produit().getQuantite())
                                                                -
                                                (    ((int) NewQuantite1.getValue())));
            */
            //sc.ModifierQuantite(Commande);
           //  btnConfirmerClicked = true;
        /*dialogstage.close();/*
        } else if  ((int) NewQuantite1.getValue()< Commande.getQuantite()){
            Commande.setQuantite((int) NewQuantite1.getValue());
           
            Commande.getId_produit().setQuantite((Commande.getId_produit().getQuantite())+ (Commande.getId_produit().getQuantite())-((int) NewQuantite1.getValue()));
            
            sc.ModifierQuantite(Commande);
             btnConfirmerClicked = true;
        dialogstage.close();
        }
        else sc.ModifierQuantite(Commande);
       Commande.setQuantite(Integer.valueOf(NewQuantite.getText()));
        btnConfirmerClicked = true;
        dialogstage.close();*/
        Appliquer.setVisible(false);
       this.Afficher(event);
        System.out.print(Commande.getQuantite());
      }
      else {
          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Stock vide ");
            alert.show();
      }
    }

    @FXML
    private void Catalogue(ActionEvent event) throws IOException {
           Stage stage = (Stage) btnCatalogue.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProduitFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }
    
    
    
    @FXML
    private void handleButtonAjouter() throws IOException {
        Reclamation r = new Reclamation();
        Commande c = ListeCommande.getSelectionModel().getSelectedItem();
        User us;
        Produit p;
        Test2 test = new Test2();
        test.commande = c;
        System.out.println(c.getId_produit());
        ServiceReclamation sr = new ServiceReclamation();
        p=sr.selectp(c.getId_produit().getId_Produit());
        test.setP(p);
        System.out.println(c.getId_produit().getId_Produit());
          System.out.println(p.getId_Produit());
            System.out.println(p.getId_utilisateur());
        us = sr.selectu(p.getId_utilisateur());
        test.nom = us.getUsername();
        System.out.println(us.getUsername());
        System.out.println(us.getEmail());
        System.out.println(test.nom);
        boolean buttonConfirmerClicked = showGestionCRUDReclamation(r);
        if (buttonConfirmerClicked) {
            sr.insertReclamation(r);
            SendMail sm = new SendMail(us.getEmail(), "Bonjour", "Vous avez reçu une réclamation veuillez consulter vos réclamations");
        }
    }

    public boolean showGestionCRUDReclamation(Reclamation reclamation) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionCRUDReclamationController.class.getResource("GestionCRUDReclamation.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion ReclamationS");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        GestionCRUDReclamationController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setReclamation(reclamation);
        dialogStage.showAndWait();
        return controller.isButtonConfirmerClicked();
    }
}
