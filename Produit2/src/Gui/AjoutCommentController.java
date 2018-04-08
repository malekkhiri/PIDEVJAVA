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
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import service.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutCommentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea comm;

    @FXML
    private Button btn;
    
     private Stage dialogStage;
    private boolean btnClicked=false;
    private int idp;
    private User U;
    private Commentaire comment;
    private Produit produit;
 public static Commentaire commenta;

    public static Commentaire getCommenta() {
        return commenta;
    }

    public static void setCommenta(Commentaire commenta) {
        AjoutCommentController.commenta = commenta;
    }
    
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commentaire getComment() {
        return comment;
    }

    public void setComment(Commentaire comment) {
        this.comment = comment;
    }

    public TextArea getComm() {
        return comm;
    }

    public void setComm(TextArea comm) {
        this.comm = comm;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnClicked() {
        return btnClicked;
    }

    public void setBtnClicked(boolean btnClicked) {
        this.btnClicked = btnClicked;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public User getU() {
        return U;
    }

    public void setU(User U) {
        this.U = U;
    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     @FXML
    private void AjouterCommentaire (ActionEvent event) throws IOException{
        

NewFXMain1 main=new NewFXMain1();
  
     U=main.getU();
      produit=main.p;
  
         DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
         Date date = new Date();
   
       Commentaire c=new Commentaire(produit.getId_Produit(),comm.getText(),U.getId(),date);
       ServiceCommentaire sc=new ServiceCommentaire();  
       commenta=c;
     sc.insertCommentaire(c);  
        btnClicked=true;
        dialogStage.close();
   
    } 
    
    
}
