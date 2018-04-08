/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Commentaire;
import Entity.Produit;
import Entity.Promotion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ServiceCommentaire;
import service.ServiceProduit;
import service.ServicePromo;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SingleProductController implements Initializable {
@FXML
    private ImageView imageP;

    @FXML
    private Label nomP;
        @FXML
    private Label nv;

    @FXML
    private Label Prix;

   @FXML
    private TextArea Description;

    @FXML
    private Label PrixP;

    @FXML
    private VBox paneV;

    @FXML
    private Label susername;

    @FXML
    private TextArea comment;

    @FXML
    private Label username;
  
    private Produit p;
        @FXML
    private Label idCom;
        private Promotion promo;
        private double nvP;
        private String n;
        
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        try {
            // TODO

            listeCommentaire();
        } catch (SQLException ex) {
            Logger.getLogger(SingleProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FXMLLoader x = new FXMLLoader(getClass().getResource("comment.fxml"));
        Node pane = x.load();
        CommentController c = x.getController();
        if (c.isBtnclicked()){
            try {
                listeCommentaire();
            } catch (SQLException ex) {
                Logger.getLogger(SingleProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    } catch (IOException ex) {
        Logger.getLogger(SingleProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
  
    
}
    
    
    @FXML
    public void listeCommentaire() throws SQLException{
        
        
          NewFXMain1 main = new NewFXMain1();
        
      this.nomP.setText(main.p.getNom_Produit());
     this.Prix.setText((String.valueOf(main.p.getPrix())));
      this.Description.setText(main.p.getDescription());
      ServicePromo Scp= new ServicePromo();
      promo=Scp.selectPromoDet(main.p.getId_Produit());
      
      nvP=((int)Double.parseDouble(Prix.getText()))-(((int)Double.parseDouble(Prix.getText()))*promo.getPourcentage());
      
     this.PrixP.setText(String.valueOf(nvP)+" DT");
     
        if (String.valueOf(nvP).equals(String.valueOf(main.p.getPrix())))
        {
            Prix.setVisible(true);
            this.PrixP.setVisible(false);
           this.nv.setVisible(false);
           
            
        }
        else
        {
            this.PrixP.setVisible(true);
            Prix.setVisible(false);
            this.nv.setVisible(true);
        }
                
             service.ServiceCommentaire Sc = new ServiceCommentaire();
        List<Commentaire> le = Sc.selectCommentaireP(main.p.getId_Produit());
        for (Commentaire com : le) {
            
           
                
                try {
                    FXMLLoader x = new FXMLLoader(getClass().getResource("comment.fxml"));
                    Node pane = x.load();
                    CommentController c = x.getController();
                    c.setContent(com);
                    
                    paneV.getChildren().add(pane);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
    
    
    } 
         File file = new File("C:/wamp64/www/Final/PIDEV/web/bundles/uploads/brochures/"+main.p.getBrochure());

        Image i = new Image(file.toURI().toString(),200,272,false,false) {};
this.imageP.setImage(i);
        System.out.println(file);
        
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
     

Commentaire commentaire=new Commentaire();
     boolean btnClicked=CoomAction(commentaire);
     if(btnClicked){
   
  try {
                AjoutCommentController controller=new AjoutCommentController();

    commentaire=  controller.getCommenta();
                    FXMLLoader x = new FXMLLoader(getClass().getResource("comment.fxml"));
                    Node pane = x.load();
                    CommentController c = x.getController();
                    c.setContent(commentaire);
                    
                    paneV.getChildren().add(pane);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
     } }
     
  
     
     
     
     
}
    