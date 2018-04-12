/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Annonces;
import Entity.User;
import Gui.NewFXMain1;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.ConnextionSingleton;

/**
 * FXML Controller class
 *
 * @author BlacK SouL
 */
public class ShowAnnonceController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private JFXButton favorisBtn;
    @FXML
    private JFXButton modifierBtn;
    @FXML
    private JFXButton supprimerBtn;
    @FXML
    private WebView webview;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label vendeur;
    @FXML
    private Label telephone;
    @FXML
    private Label prix;
    @FXML
    private Label datepublication;
    private String idann;
 private Image image;
    Connection con = ConnextionSingleton.getInstance().getConnection();
              ResultSet rs = null;
              ResultSet rss = null;
              ResultSet rsss = null;
              //private int userid = 1;
              private int userid;
              private User U;
    @FXML
    private Label idannonce;
    
private int idVenteLibre;
private String titres ;
private String villes ;
private String regions	 ;
private String descriptions ;
private String image_name ;
private Double prixs ;
private String datepublications ;
private Boolean approuver ;
private int user_id ;
private int TelephoneVendeur ;
    @FXML
    private Label labregion;
    @FXML
    private Label labville;
    @FXML
    private Label imagname;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
NewFXMain1 main=new NewFXMain1();
  
     U=main.getU();
     userid = U.getId();
    }    

    @FXML
    private void addToWishlist(ActionEvent event) throws SQLException {
        
     Statement stmtt = con.createStatement();
        System.out.println(userid);
         rs = stmtt.executeQuery("SELECT * FROM wishlist where idannonce = "+ Integer.valueOf(idannonce.getText())+" and iduser = "+userid);
              
                int i = 0;
         while(rs.next()){
            i++;
            }
         if (i==0) {
              Statement stmt = con.createStatement();
                     String sql = ("INSERT INTO `wishlist` (`iduser`, `idannonce`)"
                    + "VALUES (?,?)");
                     PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userid);  
            pstmt.setInt(2, Integer.valueOf(idannonce.getText()));
              pstmt.executeUpdate();
 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Bien");
alert.setHeaderText("");
alert.setContentText("Annonce est ajoutée au liste des favoris!");

alert.showAndWait();  
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error");
alert.setHeaderText("");
alert.setContentText("Cet Annonce est existe deja au liste des favoris!");

alert.showAndWait(); 
             System.out.println("already exist");
         }
         
    }

    @FXML
    private void Edit(ActionEvent event) throws IOException, SQLException {
titres = titre.getText();
descriptions = description.getText();
prixs  = Double.parseDouble(prix.getText());
datepublications = datepublication.getText();
TelephoneVendeur =Integer.parseInt(telephone.getText());
villes  = labville.getText();
regions = labregion.getText();
image_name = imagname.getText();
int idan= Integer.valueOf(idannonce.getText());
           FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Gui/EditAnnonce.fxml"));
                    loader.load();
                    EditAnnonceController display = loader.getController();
                    
                    display.setTexte( idan,titres ,villes ,regions ,descriptions ,image_name ,
                                        prixs ,datepublications , approuver , user_id , TelephoneVendeur );
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();  
    }

    @FXML
    private void Delete(ActionEvent event) {
         try {
                Statement stmt = con.createStatement();
                Statement stmtt = con.createStatement();
            int id_prod = Integer.valueOf(idannonce.getText());
            String sql = ("DELETE FROM `annonces` where idVenteLibre = "+id_prod);
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.executeUpdate();
             rs = stmt.executeQuery("SELECT * FROM wishlist where idannonce ="+id_prod);
            while (rs.next()) {
                int idusers = rs.getInt("iduser");
                rss = stmtt.executeQuery("SELECT * FROM user where id ="+idusers);
                while (rss.next()) {
                    String email = rss.getString("email");
                    try {
			String host = "smtp.gmail.com";
			String user = "soukelmedina.noreply@gmail.com";
			String pass = "aqwzsxedc12345";
			String to = email;
			String from = "soukelmedina.noreply@gmail.com";
			String subjects = "annonce deleted";
			String messageText = "lannonce a ete supprimer ou vendu";
			boolean sessionDebug = false;
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = {new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subjects);
			msg.setSentDate(new Date());
			msg.setText(messageText);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
                }
            }
            String sql2 = ("DELETE FROM `wishlist` where idannonce = (?)");
            PreparedStatement pstmtt = con.prepareStatement(sql2);
            pstmtt.setInt(1, id_prod);
            pstmtt.executeUpdate();
            
                                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Bien");
alert.setHeaderText("");
alert.setContentText("Annonce est supprimée avec succée!");

alert.showAndWait();
((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
   
     public String setTexte(int idVenteLibre,String titres ,String villes 
             ,String regions ,String descriptions ,String image_name ,Double prixs 
             ,String datepublications ,Boolean approuver ,int user_id ,int TelephoneVendeur ) throws SQLException{
         titre.setText(titres);
         description.setText(descriptions);
         telephone.setText(Integer.toString(TelephoneVendeur));
         datepublication.setText(datepublications); 
         labregion.setText(regions);
         labville.setText(villes);
         imagname.setText(image_name);
         prix.setText(Double.toString(prixs));
         Statement stmttt = con.createStatement();
         rsss = stmttt.executeQuery("SELECT * FROM user where id ="+user_id);
                while (rsss.next()) {
                    vendeur.setText(rsss.getString("username"));
                }
                if( user_id== userid){
                  modifierBtn.setVisible(true);
                  supprimerBtn.setVisible(true);
                   favorisBtn.setVisible(false);
                }
         
         System.out.println(image_name);
         image = new Image("/Images/"+image_name);
              img.setImage(image);
         idannonce.setText(Integer.toString(idVenteLibre));
         System.out.println(idVenteLibre);
        return datepublications;
    }
}
