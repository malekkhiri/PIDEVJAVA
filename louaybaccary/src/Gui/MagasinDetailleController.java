/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Produit;
import Entity.avis;
import Entity.notes;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import service.ServiceProduit;
import service.ServiceRating;
import service.ServicesNotes;

/**
 * FXML Controller class
 *
 * @author Hosni
 */
public class MagasinDetailleController implements Initializable {

    @FXML
    private Label NomMagasin;
    @FXML
    private Label AdresseMagasin;
    @FXML
    private Label RegionMagasin;
    @FXML
    private Label VilleMagasin;
    @FXML
    private Rating rating;
    @FXML
    private Label rateinteger;
    @FXML
    private Button btnRat;
    @FXML
    private Button btnVend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            NewFXMain1 main = new NewFXMain1();
            
             if (main.u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")) {
            btnVend.setVisible(true);
            
        }
        else{
            btnVend.setVisible(false);
        }
            rating.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                         
             if ((int)(long)Double.parseDouble(rateinteger.getText())!=0) {
            btnRat.setVisible(true);
            
        }
        else{
            btnRat.setVisible(false);
        }
             });
            Rating();
        
      this.NomMagasin.setText(main.m.getNom_magasin());
     this.AdresseMagasin.setText((main.m.getAdresse_magasin()));
      this.RegionMagasin.setText(main.m.getRegion());
            this.VilleMagasin.setText(main.m.getVille());

      
        // TODO
    }   
    
     public void Rating (){
         
         rating.ratingProperty().addListener(new ChangeListener<Number>(){
             @Override
             public void changed(ObservableValue<? extends Number> arg0, Number t, Number t1) {
                 rateinteger.setText(t1.toString());
             }
             
             
         });

         
     }
  

 @FXML
     public void Ratinginsert (ActionEvent event ) throws SQLException{
         ServicesNotes sr=new ServicesNotes();
         NewFXMain1 main = new NewFXMain1();
         
         
        int rate;
        boolean F=false;
        
        rate=(int)(long)Double.parseDouble(rateinteger.getText());
        System.out.println(rate);
        System.out.println(main.m.getId_magasin());
        System.out.println(main.u.getId());
       F= sr.Exist(main.u.getId(), main.m.getId_magasin());
      
         if(F==false){
               notes note=new notes(main.m.getId_magasin(),main.u.getId(),rate);
        
         System.out.println(F);
         sr.insertRating(note);    
         }
         else{
             notes n = new notes();
             n=sr.selectavis(main.m.getId_magasin(),main.u.getId());
             System.out.println(F);
             n.setNote(rate);
             sr.updateProduit(n);
         }
             
         
         
         
         
         
     
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
  @FXML
 private void handleAjoutBtn() throws IOException{
Produit produit=new Produit();
     boolean btnClicked=ajoutAction(produit);
     if(btnClicked){
   
     }}
 
  @FXML
    private void Click(ActionEvent event) throws IOException {

 try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument1.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }


    
}
