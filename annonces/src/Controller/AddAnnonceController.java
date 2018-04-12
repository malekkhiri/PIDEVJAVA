/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.User;
import Gui.NewFXMain1;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.ConnextionSingleton;
 
public class AddAnnonceController implements Initializable {
    @FXML
    private JFXButton SaveBtn;
     Connection con = ConnextionSingleton.getInstance().getConnection();
              ResultSet rs = null;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXTextField region;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXButton cancelBtn;
     @FXML
    private Button upfile;
     private String images;
     private int iduser ;
     private User U ;
    @FXML
    private JFXDatePicker datepub;
    @FXML
    private JFXButton returnbtn;
    @FXML
    private WebView webView;
    private WebEngine webEngine ;
    @FXML
    private JFXButton getLocationBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NewFXMain1 main=new NewFXMain1();
  
     U=main.getU();
     iduser = U.getId();
        this.webEngine = this.webView.getEngine(); 
            final String urlGoogleMaps = ("http://localhost/up/maptest.html");
            this.webEngine.load(urlGoogleMaps);
        // TODO
    }    

    @FXML
    private void SaveAnnoce(ActionEvent event) throws ParseException {
            String title = titre.getText();
        
            String villes = ville.getText();
            String descriptions = description.getText();
            String price = prix.getText();
            String regions = region.getText();  
            String tel = telephone.getText();
            String date = datepub.getValue().toString();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateajout = format.parse(date);
            Date today = new Date();
           
String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(today);
Date todays = format.parse(modifiedDate); 
            System.out.println(dateajout);
            if(!isNumeric(price) || price.startsWith("-")){
                Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("");
alert.setContentText("Ooops, Prix Incorrecte!");

alert.showAndWait();  

                 System.out.println("");
            }else if(dateajout.compareTo(todays) < 0){
                           Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error");
alert.setHeaderText("");
alert.setContentText("Ooops, Date Incorrecte!");

alert.showAndWait();  
                System.out.println("probleme date"); 
            }else
                    {
                try {
                Statement stmt = con.createStatement();
                     String sql = ("INSERT INTO `annonces` (`titre`, `ville`, `region`, `description`, `TelephoneVendeur`, `image_name`, `prix`, `user_id`, `datepublication`) VALUES (?,?,?,?,?,?,?,?,?);");
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, villes);
            pstmt.setString(3, regions);
            pstmt.setString(4, descriptions);
                        pstmt.setInt(5, Integer.valueOf(tel));

            pstmt.setString(6, images);
            pstmt.setDouble(7, Double.valueOf(price));
             pstmt.setInt(8, Integer.valueOf(iduser));
             pstmt.setString(9, String.valueOf(date));
            pstmt.executeUpdate();
                                     Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Bien");
alert.setHeaderText("");
alert.setContentText("Annonce est ajouté avec succée!");

alert.showAndWait();  
            

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
            }
        
    }
public static boolean isNumeric(String str)  
{  
  try  
  {  
    double d = Double.parseDouble(str);  
  }  
  catch(NumberFormatException nfe)  
  {  
    return false;  
  }  
  return true;  
}
    @FXML
    private void ViderChamps(ActionEvent event) {
                titre.setText("");
                telephone.setText("");
                region.setText("");
                ville.setText("");
                description.setText("");
                prix.setText("");
    }

    @FXML
    private void UploadImg(ActionEvent event)throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
            File source = new File(selectedFile.getAbsolutePath());
         images = selectedFile.getName();
        File dest = new File("C:\\Users\\oussama\\Desktop\\java\\Produit2\\PIDEVJAVA\\Produit2\\src\\Images\\"+selectedFile.getName());
        long start = System.nanoTime();
        start = System.nanoTime();
        copyFileUsingJava7Files(source, dest);
        }else{
            System.out.println("File not valid");
        }
    }
    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
	    Files.copy(source.toPath(), dest.toPath());
	}

    @FXML
    private void back(ActionEvent event) throws IOException {
          ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Annonces.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void getLocation(ActionEvent event) {
String reg = this.webEngine.getTitle().substring(0,this.webEngine.getTitle().indexOf(" ||| "));
 String vil = this.webEngine.getTitle().substring(this.webEngine.getTitle().indexOf(" ||| ")+5);
         region.setText(reg);    
        ville.setText(vil);

    }
}
