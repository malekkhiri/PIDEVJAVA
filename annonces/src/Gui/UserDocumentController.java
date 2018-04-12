/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

 

import Entity.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ServiceUser;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class UserDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField Nom;
private boolean btnclicked;
    @FXML
    private JFXPasswordField pass;
        @FXML
    private Button login;
        
    @FXML
    private Button facebook;

    @FXML
    private Label noU;
            @FXML
    private AnchorPane pane;
            private String loggeduser;
            private User Us;
            private Stage dialogStage;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
            
           private int iduser;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public User getUs() {
        return Us;
    }

    public void setUs(User Us) {
        this.Us = Us;
    }

    public String getLoggeduser() {
        return loggeduser;
    }

    public void setLoggeduser(String loggeduser) {
        this.loggeduser = loggeduser;
    }
          

    
    @FXML
    private void LoginAction (ActionEvent event) throws IOException, SQLException{
       
        if(ControleSaisie()){
        String  username=Nom.getText();
       String password=pass.getText();
       
User U =new User();
       U.setPassword(password);
       U.setUsername(username);
       
       
     
       ServiceUser su=new ServiceUser();
       NewFXMain1 main = new NewFXMain1();
       
           
    

       
        try
 {
     String userValide=su.authenticateUser(U);
            Us=su.recupererUtilisateur(username);
            main.setU(su.recupererUtilisateur(username));
          

 
        
     if(userValide.equals("Admin"))
 {

 System.out.println("Admin's Home");
 
   
iduser=Us.getId();
 System.out.println(Us.getId());
     System.out.println(main.u.getUsername());
 
     System.out.println(iduser);

     
//  AnchorPane p= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        pane.getChildren().setAll(p);
//         p.setMinHeight(849);
//        p.setMinWidth(561);


//FXMLLoader loader =new FXMLLoader();
//  loader.setLocation(FXMLDocumentController.class.getResource("FXMLDocument.fxml"));
//  AnchorPane pane=(AnchorPane) loader.load();
//       Stage dialogStage=new Stage();
//  dialogStage.setTitle("Souk el Mdina ");
//     Scene scene = new Scene(pane);
//     dialogStage.setScene(scene);
//     dialogStage.setResizable(false);
//        
//        this.dialogStage=dialogStage;
//        
//        dialogStage.show();
// 

 Stage stage = (Stage) login.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeAdmin.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
           
       
            stage2.setTitle("Souk El Madina ");
            stage2.setScene(new Scene(root2));

            stage2.show();

        }catch(IOException e) {
            e.printStackTrace();
        }

 }
     else if(userValide.equals("Utilisateur"))
 {

     System.out.println("User Home");
      System.out.println(Us.getId());
      
      
       
Stage stage = (Stage) login.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
           
       
            stage2.setTitle("Souk El Madina ");
            stage2.setScene(new Scene(root2));

            stage2.show();

        }catch(IOException e) {
            e.printStackTrace();
        }
     
 
 }
     else if(userValide.equals("Vendeur"))
 {
 System.out.println("Vendeur Home");
  System.out.println(Us.getId());
   

 Stage stage = (Stage) login.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
           
       
            stage2.setTitle("Souk El Madina ");
            stage2.setScene(new Scene(root2));

            stage2.show();

        }catch(IOException e) {
            e.printStackTrace();
        }
 }
     else
 {
 System.out.println("Error message = "+userValide);
    Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Login");
            alert.setContentText(" Invalid user");

            alert.showAndWait();
 }
     
 }
 catch (Exception e1)
 {
 e1.printStackTrace();
 }
}}  
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  
    
        // TODO
    }    
    
      private boolean ControleSaisie() {
        boolean valide = true;

if(pass.getText().equals("")||Nom.getText().equals(""))        {
            
              valide=false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champ obligatoire");
            alert.setContentText("veuiller remplir tous les champs!");

            alert.showAndWait();
        }
        return valide;
    }
}

