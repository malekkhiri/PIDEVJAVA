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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.ConnextionSingleton;
 
public class EditAnnonceController implements Initializable {
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
    @FXML
    private JFXDatePicker datepub;
    @FXML
    private JFXButton returnbtn;
    @FXML
    private WebView webView;
    private WebEngine webEngine ;
    @FXML
    private JFXButton getLocationBtn;
    @FXML
    private JFXButton EditBtn;
     
      private Image image;
    @FXML
    private ImageView img;
    @FXML
    private Label idventelibre;
    private String imgname;
    private User U;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
NewFXMain1 main=new NewFXMain1();
  
     U=main.getU();
     iduser = U.getId();
        this.webEngine = this.webView.getEngine(); 
            final String urlGoogleMaps = ("http://localhost/up/maptest.html");
            this.webEngine.load(urlGoogleMaps);
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
    @FXML
    private void EditAnnoce(ActionEvent event) throws ParseException {
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
            idventelibre.getText();
            String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(today);
            Date todays = format.parse(modifiedDate); 
            
            if(!isNumeric(price) || price.startsWith("-")){
                                           Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error");
alert.setHeaderText("");
alert.setContentText("Ooops, Prix Incorrecte!");

alert.showAndWait();  
            }else if(dateajout.compareTo(todays) < 0){
                                          Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error");
alert.setHeaderText("");
alert.setContentText("Ooops, Date Incorrecte!");

alert.showAndWait();  
            }else
                    {
                try {
                Statement stmt = con.createStatement();
                     String sql = ("UPDATE `annonces` SET `titre` = (?), `ville` = (?),`region` = (?),`description` = (?),`TelephoneVendeur` = (?),`image_name` = (?),`prix` = (?),`datepublication` = (?) WHERE `annonces`.`idVenteLibre` = (?); ");
            PreparedStatement pstmt = con.prepareStatement(sql);
                    System.out.println("bgdjshg"+titre.getText()+titre.getText()+"image "+imgname);
            pstmt.setString(1, titre.getText());
            pstmt.setString(2, ville.getText());
            pstmt.setString(3, region.getText());
            pstmt.setString(4, description.getText());
            pstmt.setInt(5, Integer.valueOf(telephone.getText()));
            pstmt.setString(6, imgname);
            pstmt.setDouble(7, Double.valueOf(prix.getText()));
            pstmt.setString(8, String.valueOf(datepub.getValue()));
            pstmt.setInt(9, Integer.valueOf(idventelibre.getText()));
            pstmt.executeUpdate();
                                                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Bien");
alert.setHeaderText("");
alert.setContentText("Annonce est modifié avec succée!");

alert.showAndWait();  
                    System.out.println("jawek behi");
                    ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
            }
    }
    
     public String setTexte(int idVenteLibre,String titres ,String villes 
             ,String regions ,String descriptions ,String image_name ,Double prixs 
             ,String datepublications ,Boolean approuver ,int user_id ,int TelephoneVendeur ) throws SQLException{
         titre.setText(titres);
         idventelibre.setText(Integer.toString(idVenteLibre));
         description.setText(descriptions);
         telephone.setText(Integer.toString(TelephoneVendeur));
         LocalDate localDate = LocalDate.parse(datepublications);
         datepub.setValue(localDate);
         prix.setText(Double.toString(prixs));
         System.out.println("+fdsfs"+image_name);
         ville.setText(villes);   
         region.setText(regions);
imgname = image_name;
         image = new Image("/Images/"+image_name);
         img.setImage(image);
         
         System.out.println(idVenteLibre);
        return datepublications;
    }
}
