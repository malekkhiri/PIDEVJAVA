/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DataBase.DataSource;
import Entity.Commentaire;
import Entity.Produit;
import Entity.Promotion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceCommentaire;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class GProduitController implements Initializable {

    @FXML
    private TableView<Produit> TableViewP;

    @FXML
    private TableColumn<Produit, String> NomP;

    @FXML
    private TableColumn<Produit,String > PrixP;

    @FXML
    private TableColumn<Produit, String> QP;

    @FXML
    private Label LPNom;

    @FXML
    private Label LPPrix;

    @FXML
    private Label LPQ;

    @FXML
    private Label LPDes;

    @FXML
    private Button ajout;

    @FXML
    private Button modif;

    @FXML
    private Button supp;
     @FXML
    private AnchorPane pane;
        @FXML
    private Label lidp;
        public static int idp;
          @FXML
    private Button supp1;

    public static int getIdp() {
        return idp;
    }

    public static void setIdp(int idp) {
        GProduitController.idp = idp;
    }
    private List<Produit> listProduit;
    private ObservableList<Produit> observablelistproduit;
    
      @FXML
    private TableView<Commentaire> TableViewCom;

    @FXML
    private TableColumn<Commentaire,String> UserCol;

    @FXML
    private TableColumn<Commentaire,String> CommCol;

    @FXML
    private Label usernameV;

    @FXML
    private Label CommentaireV;
  private List<Commentaire> listCommentaire;
    private ObservableList<Commentaire> observablelistcommentaire;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListeProduit();
TableViewP.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->selectAction(newValue));
 ListeCommentaire();
TableViewCom.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->selectcommentAction(newValue));
   
    
    }    
    
    @FXML
    private void ListeProduit(){
    
        NomP.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));
        PrixP.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        QP.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        ServiceProduit Sp=new ServiceProduit();
        listProduit=Sp.selectProduit();
      
        
        observablelistproduit=FXCollections.observableArrayList(listProduit);
        TableViewP.setItems(observablelistproduit);
        
        
    }
 @FXML
 private void selectAction(Produit produit){
     LPNom.setText(produit.getNom_Produit());
     LPPrix.setText(String.valueOf(produit.getPrix()));
     LPQ.setText(String.valueOf(produit.getQuantite()));
     LPDes.setText(String.valueOf(produit.getDescription()));
     lidp.setText(String.valueOf(produit.getId_Produit()));
 
     
     
     
 
 }
 @FXML
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
  @FXML
 private void handleAjoutBtn() throws IOException{
Produit produit=new Produit();
     boolean btnClicked=ajoutAction(produit);
     if(btnClicked){
   
ListeProduit(); 
     } }
 
 @FXML
 private boolean ajoutPromoAction(Promotion promotion) throws IOException{
   FXMLLoader loader =new FXMLLoader();
  loader.setLocation(PromoController.class.getResource("Promo.fxml"));
  AnchorPane pane=(AnchorPane) loader.load();
       Stage dialogStage=new Stage();
  dialogStage.setTitle("Ajouter Une Promotion");
     Scene scene = new Scene(pane);
     dialogStage.setScene(scene);
     dialogStage.setResizable(false);
          PromoController controller=loader.getController();
     controller.setDialogStage(dialogStage);
     controller.setPromo(promotion);
   
     
 dialogStage.showAndWait();
 return controller.isBtnClicked();

 }
  @FXML
 private void handleAjoutPromoBtn() throws IOException{
      Produit produit=TableViewP.getSelectionModel().getSelectedItem();
NewFXMain1 main = new NewFXMain1();
main.p=produit;
Promotion promotion=new Promotion();
     boolean btnClicked=ajoutPromoAction(promotion);
     if(btnClicked){
   
ListeProduit(); 
     } }
 
     
      @FXML

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
 
     @FXML
    
     
     private void handleModifierBtn() throws IOException{
     
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
 
     private void handleSuppBtn() throws IOException{
     Produit produit=TableViewP.getSelectionModel().getSelectedItem();
    
           if (produit!=null)
     {ServiceProduit Sc = new ServiceProduit();
         Sc.supprimerProduit(produit);
         ListeProduit();
     }

     }
      @FXML
       Produit idproduit() throws IOException{
           Produit p =null ;
     Produit produit=TableViewP.getSelectionModel().getSelectedItem();
     p=produit;
     return p;
     
 
      }
       
       
       
        @FXML

  private boolean CoomAction(Commentaire commentaire) throws IOException{
   FXMLLoader loader =new FXMLLoader();
  loader.setLocation(AjoutCommentController.class.getResource("AjoutComment.fxml"));
  AnchorPane pane=(AnchorPane) loader.load();
       Stage dialogStage=new Stage();
  dialogStage.setTitle("Ajouter un commentaire");
     Scene scene = new Scene(pane);
     dialogStage.setScene(scene);
     dialogStage.setResizable(false);
          AjoutCommentController controller=loader.getController();
     controller.setDialogStage(dialogStage);
     controller.setComment(commentaire);
   
     
 dialogStage.showAndWait();
 return controller.isBtnClicked();

 }
 
     @FXML
    
     
     private void handleCommentBtn() throws IOException{
     
      Produit produit=TableViewP.getSelectionModel().getSelectedItem();
NewFXMain1 main = new NewFXMain1();
main.p=produit;
Commentaire commentaire=new Commentaire();
     boolean btnClicked=CoomAction(commentaire);
     if(btnClicked){
   
ListeCommentaire(); 
     } }
     
  
     
     
      @FXML
    private void ListeCommentaire(){
     
        Produit produit=TableViewP.getSelectionModel().getSelectedItem();
     NewFXMain1 main=new NewFXMain1();
     main.p=produit;
     
     if(produit!=null){
        UserCol.setCellValueFactory(new PropertyValueFactory<>("commentator_id"));
        CommCol.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
       
       ServiceCommentaire sc=new ServiceCommentaire();
       
           
          
        listCommentaire=sc.selectCommentaireP(produit.getId_Produit());
       
        
        observablelistcommentaire=FXCollections.observableArrayList(listCommentaire);
       TableViewCom.setItems( observablelistcommentaire);
        
     }
    }
 @FXML
 private void selectcommentAction(Commentaire commentaire){
     usernameV.setText(String.valueOf(commentaire.getCommentator_id()));
     CommentaireV.setText(commentaire.getContenu());
     
 
 }
      @FXML
 
     private void handleSuppCBtn() throws IOException{
   Commentaire commentaire=TableViewCom.getSelectionModel().getSelectedItem();
    
           if (commentaire!=null)
     {NewFXMain1 main=new NewFXMain1();
         
         ServiceCommentaire Sc = new ServiceCommentaire();
     
         Sc.supprimerCommentaire(commentaire);
         }
         ListeCommentaire();
     }
}
