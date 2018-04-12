/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Produit;
import Entity.User;
import static Gui.NewFXMain1.main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private JFXTextField Description;

    @FXML
    private JFXTextField Name;

    @FXML
    private JFXTextField Prix;

    @FXML
    private JFXTextField Quantitee;

    @FXML
    private JFXButton btn;
    
    
    private Stage dialogStage;
    private boolean btnClicked=false;
    private Produit produit;
    private User U;
    private Label IDU;
    
        @FXML
    private Label nomImage;
       private  FileChooser fc = new FileChooser();
    private File f;

private int ide;
    @FXML
    private Label idp;
    public Label getIDU() {
        return IDU;
    }

    public void setIDU(Label IDU) {
        this.IDU = IDU;
    }

    

    public boolean isBtnClicked() {
        return btnClicked;
    }

    public void setBtnClicked(boolean btnClicked) {
        this.btnClicked = btnClicked;
    }
   

    public Produit getProduit() {
        return produit;
    }
 


    public void setProduit(Produit produit) {

        this.produit = produit;
//        this.Name.setText(produit.getNom_Produit());
//  this.Prix.setText(String.valueOf(produit.getPrix()));
//          this.Quantitee.setText(String.valueOf(produit.getQuantite()));
//          this.Description.setText(String.valueOf(produit.getDescription()));
//          

         
          
         
 

                
                      
                                
    }
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public JFXTextField getDescription() {
        return Description;
    }

    public void setDescription(JFXTextField Description) {
        this.Description = Description;
    }

    public JFXTextField getName() {
        return Name;
    }

    public void setName(JFXTextField Name) {
        this.Name = Name;
    }

    public JFXTextField getPrix() {
        return Prix;
    }

    public void setPrix(JFXTextField Prix) {
        this.Prix = Prix;
    }

    public JFXTextField getQuantitee() {
        return Quantitee;
    }

    public void setQuantitee(JFXTextField Quantitee) {
        this.Quantitee = Quantitee;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
        @FXML
    private ImageView imageV;
    

   
//  @FXML
//    private void image (ActionEvent event){
//        
//    
//            FileChooser fc =new FileChooser();
//            File selectedFile=fc.showOpenDialog(null);
//            if (selectedFile !=null)
//            {
//            image.setText(selectedFile.getAbsolutePath());
//            } 
//            else{
//                
//                System.out.println("false");
//            }
//    }
    
@FXML
    void uploadimage(ActionEvent event) {
        fc.setTitle("Choosir une image pour votre Produit ");
        File selectedFile = fc.showOpenDialog(null);
        f=selectedFile;
        if (selectedFile != null) {


            //the code to copy the selected file goes here//
            nomImage.setText(selectedFile.getName());
        
       String Path =f.getAbsolutePath();
            System.err.println(Path);
            File file = new File(Path);

        Image i = new Image(file.toURI().toString(),210,216,false,false) {};
this.imageV.setImage(i);

        } else{
            /*file1.setText("no file attached");*/
        }
          
    }
    
   
    
    
    @FXML
    private void AjouterProduit (ActionEvent event){
        

NewFXMain1 main=new NewFXMain1();
  
     U=main.getU();
     String uuu=UUID.randomUUID().toString();
     Path from = Paths.get(f.toURI());
        Path to = Paths.get("C:/wamp64/www/Final/PIDEV/web/bundles/uploads/brochures/"+uuu+".jpeg");
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        try {
            Files.copy(from, to, options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
     
      if(ControleSaisie()){

        Produit p=new Produit(Name.getText(),main.m.getId_magasin(),(float)Double.parseDouble(Prix.getText()),Integer.parseInt(Quantitee.getText()),Description.getText(),U.getId(),0,uuu+".jpeg");
        
       ServiceProduit sp=new ServiceProduit();                 
sp.insertProduiImaMagas(p);
        btnClicked=true;
        dialogStage.close();
   
    } 
    }
      
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
       
        
        // TODO
    }    
    private boolean ControleSaisie() {
        boolean valide = true;
//        if(Name.getText().equals(""))
//        {
//            valide=false;
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText("Champ obligatoire");
//            alert.setContentText("veuiller remplir tous les champs!");
//
//            alert.showAndWait();
//        } if(Prix.getText().equals(""))
//        {
//            valide=false;
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText("Champ obligatoire");
//            alert.setContentText("veuiller remplir tous les champs!");
//
//            alert.showAndWait();
//        } if(Description.getText().equals(""))
//        {
//            valide=false;
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText("Champ obligatoire");
//            alert.setContentText("veuiller remplir tous les champs!");
//
//            alert.showAndWait();
//        } if(Description.getText().equals(""))
//        {
//            valide=false;
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText("Champ obligatoire");
//            alert.setContentText("veuiller remplir tous les champs!");
//
//            alert.showAndWait();
//        } if(nomImage.getText().equals(""))
//        {
//            valide=false;
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText("Champ obligatoire");
//            alert.setContentText("veuiller remplir tous les champs!");
//
//            alert.showAndWait();
//        }
        if(nomImage.getText().equals("nom image")||Description.getText().equals("")||Description.getText().equals("")||Prix.getText().equals("")||Prix.getText().equals("0")||Name.getText().equals(""))
        {
            
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
