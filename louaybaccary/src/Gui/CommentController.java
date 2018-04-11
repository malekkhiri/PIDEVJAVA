/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Commentaire;
import Entity.Produit;
import Entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label username;

       @FXML
    private TextArea comment;
           @FXML
    private Label idCom;
       private Commentaire comm;
           @FXML
    private Button suppbtn;
           private User userr;
               @FXML
    private Label UsName;

    public boolean isBtnclicked() {
        return btnclicked;
    }

    public void setBtnclicked(boolean btnclicked) {
        this.btnclicked = btnclicked;
    }
               private boolean btnclicked=false;
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
    
        // TODO 
    }    
     public void setContent(Commentaire c) {
       NewFXMain1 main = new NewFXMain1(); 
      this.username.setText(String.valueOf(c.getCommentator_id()));
      this.comment.setText(String.valueOf(c.getContenu()));
      this.idCom.setText(String.valueOf(c.getId()));
     String us=username.getText();
          
          String u= String.valueOf(main.u.getId());
          
       
        if(u.equals(us))
        {
           suppbtn.setVisible(true);
        }
        else {
            suppbtn.setVisible(false);
        }
        ServiceCommentaire Sc=new ServiceCommentaire();
        userr=Sc.selectCommentaireU(Integer.parseInt(us),c.getCommentator_id());
         System.out.println(userr.getUsername());
         this.UsName.setText(userr.getUsername());
        
     }
    
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
    void SuppCom(ActionEvent event) throws SQLException {
           

        service.ServiceCommentaire sc=new ServiceCommentaire();
        String i =idCom.getText();
        System.out.println(i);
       comm= sc.selectCommDet(Integer.parseInt(i));
       sc.supprimerCommentaire(comm);

        setBtnclicked(true);
      
    }
    

}




