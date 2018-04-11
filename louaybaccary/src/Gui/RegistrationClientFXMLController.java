/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ConfirmationPdfFXMLController.sendEmailWithAttachments;
import static Gui.LoginFXMLController.userconnecte;
import static Gui.PanierFXMLController.commandelocale;
import static Gui.RegistrationClientFXMLController.support;
import Entity.User;
import static java.awt.SystemColor.control;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.validator.routines.EmailValidator;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import service.ServiceGestionProfil;

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class RegistrationClientFXMLController implements Initializable {

    @FXML
    private ComboBox<String> comboVille;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Button Confirmer;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldTel;
    @FXML
    private DatePicker textFieldDatenaissaince;
    @FXML
    private ComboBox<String> comboProduitPrefere;
    @FXML
    private Button btnRetour;
    
    static ValidationSupport support = new ValidationSupport();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> ListeVilles = new ArrayList<>();
        ArrayList<String> ListeProduitPrefere = new ArrayList<>();
        ListeVilles.add("Ariana");
        ListeVilles.add("Beja");
        ListeVilles.add("Ben Arous");
        ListeVilles.add("Bizerte");
        ListeVilles.add("Gabes");
        ListeVilles.add("Gafsa");
        ListeVilles.add("Jendouba");
        ListeVilles.add("Kairouan");
        ListeVilles.add("Kasserine");
        ListeVilles.add("Kebili");
        ListeVilles.add("La Manouba");
        ListeVilles.add("Le Kef");
        ListeVilles.add("Mahdia");
        ListeVilles.add("Medenine");
        ListeVilles.add("Monastir");
        ListeVilles.add("Nabeul");
        ListeVilles.add("Sfax");
        ListeVilles.add("Sidi Bouzid");
        ListeVilles.add("Siliana");
        ListeVilles.add("Sousse");
        ListeVilles.add("Tataouine");
        ListeVilles.add("Tozeur");
        ListeVilles.add("Tunis");
        ListeVilles.add("Zaghouan");
        ObservableList<String> ComboData = FXCollections.observableArrayList(ListeVilles);
        for(String s : ComboData){
            comboVille.getItems().add(s);
        }
        
        ListeProduitPrefere.add("Meuble");
        ListeProduitPrefere.add("Accessoires");
        ListeProduitPrefere.add("Vetement");
        ObservableList<String> ComboData1 = FXCollections.observableArrayList(ListeProduitPrefere);
        for(String s : ComboData1){
            comboProduitPrefere.getItems().add(s);
        }
        
                       ////////////////////////////////////////////
        
               Validator<String> villeValidator = new Validator<String>()
        
               {  @Override
            public ValidationResult apply( Control control, String value )
        
         {
          boolean condition = false;
          try {
              condition = value != null
                      ? !isValidville(value)
                      : value == null;
//                      System.out.println( condition );
//       
//
//        return ValidationResult.fromMessageIf( control, "Nom Valide", Severity.ERROR, condition );
          } catch (SQLException ex) {
              Logger.getLogger(RegistrationClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
          System.out.println( textFieldNom.getText().length() );
       

        return ValidationResult.fromMessageIf( control, "Entrer une ville", Severity.ERROR, condition );
      }
        };
        
      
          support.registerValidator( comboVille, false, villeValidator );
           
           ////////////
        ////////////////////////////////////////////
                        //////////////////////////////////////////
        
               Validator<String> prodValidator = new Validator<String>()
        
               {  @Override
            public ValidationResult apply( Control control, String value )
        
         {
          boolean condition = false;
          try {
              condition = value != null
                      ? !isValidville(value)
                      : value == null;
//                      System.out.println( condition );
//       
//
//        return ValidationResult.fromMessageIf( control, "Nom Valide", Severity.ERROR, condition );
          } catch (SQLException ex) {
              Logger.getLogger(RegistrationClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
          System.out.println( textFieldNom.getText().length() );
       

        return ValidationResult.fromMessageIf( control, "Entrer une ville", Severity.ERROR, condition );
      }
        };
        
      
          support.registerValidator( comboProduitPrefere, false, prodValidator );
           
           ////////////
        //////////////////////////////////////////
        
                               ////////////////////////////////////////////
        
               Validator<String> mdpValidator = new Validator<String>()
        
               { 
                   @Override
            public ValidationResult apply( Control control, String value )
        
         {
          boolean condition = false;
          try {
              condition = value != null
                      ? !isValidmdp(value)
                      : value == null;
//                      System.out.println( condition );
//       
//
//        return ValidationResult.fromMessageIf( control, "Nom Valide", Severity.ERROR, condition );
          } catch (SQLException ex) {
              Logger.getLogger(RegistrationClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
//          System.out.println( comboProduitPrefere.getValue().length() );
       

        return ValidationResult.fromMessageIf( control, "Entrer un mot de passe", Severity.ERROR, condition );
      }
        };
        
      
          support.registerValidator( textFieldPassword, false, mdpValidator );
           
           ////////////
        ////////////////////////////////////////////
               ////////////////////////////////////////////
        
               Validator<String> telValidator = new Validator<String>()
        
               {  @Override
            public ValidationResult apply( Control control, String value )
        
         {
          boolean condition = false;
          try {
              condition = value != null
                      ? !isValidtel(value)
                      : value == null;
//                      System.out.println( condition );
//       
//
//        return ValidationResult.fromMessageIf( control, "Nom Valide", Severity.ERROR, condition );
          } catch (SQLException ex) {
              Logger.getLogger(RegistrationClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
          System.out.println( textFieldNom.getText().length() );
       

        return ValidationResult.fromMessageIf( control, "Num tel invalide", Severity.ERROR, condition );
      }
        };
        
      
          support.registerValidator( textFieldTel, false, telValidator );
           
           ////////////
        ////////////////////////////////////////////
        
               Validator<String> nomValidator = new Validator<String>()
        
               {  @Override
            public ValidationResult apply( Control control, String value )
        
         {
          boolean condition = false;
          try {
              condition = value != null
                      ? !isValidnom(value)
                      : value == null;
//                      System.out.println( condition );
//       
//
//        return ValidationResult.fromMessageIf( control, "Nom Valide", Severity.ERROR, condition );
          } catch (SQLException ex) {
              Logger.getLogger(RegistrationClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
          System.out.println( textFieldNom.getText().length() );
       

        return ValidationResult.fromMessageIf( control, "Nom Valide", Severity.ERROR, condition );
      }
        };
        
      
          support.registerValidator( textFieldNom, false, nomValidator );
           
           ////////////
                 ////////////////////////////////////////////
        
               Validator<String> PrenomValidator = new Validator<String>()
        
               {  @Override
            public ValidationResult apply( Control control, String value )
        
         {
          boolean condition = false;
          try {
              condition = value != null
                      ? !isValidPrenom(value)
                      : value == null;
//                      System.out.println( condition );
//       
//
//        return ValidationResult.fromMessageIf( control, "Nom Valide", Severity.ERROR, condition );
          } catch (SQLException ex) {
              Logger.getLogger(RegistrationClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
          System.out.println( textFieldNom.getText().length() );
       

        return ValidationResult.fromMessageIf( control, "Prenom Inalide", Severity.ERROR, condition );
      }
        };
        
      
          support.registerValidator( textFieldPrenom, false, PrenomValidator );
           
           ////////////
        Validator<String> mailvalidator = new Validator<String>()
    {
      @Override
      public ValidationResult apply( Control control, String value )
      {
        boolean condition =
            value != null
                ? !isValidMail(value)
                : value == null;

        System.out.println( condition );
       

        return ValidationResult.fromMessageIf( control, "Email incorrect", Severity.ERROR, condition );
      }

      

         
    };
        
        support.registerValidator( textFieldEmail, false, mailvalidator );
        
       
     /////////////////////////////
     
           Validator<String> usernameValidator = new Validator<String>()
    {
      @Override
      public ValidationResult apply( Control control, String value )
      {
        boolean condition = false;
          try {
              condition = value != null
                      ? !isValidusername(value)
                      : value == null;
//                      System.out.println( condition );
//       
//
//        return ValidationResult.fromMessageIf( control, "Username Existant", Severity.ERROR, condition );
          } catch (SQLException ex) {
              Logger.getLogger(RegistrationClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }

        System.out.println( condition );
       

        return ValidationResult.fromMessageIf( control, "Username Existant", Severity.ERROR, condition );
      }

      

         
    };
        
        support.registerValidator( textFieldUsername, false, usernameValidator );
     
     /////////////////////////
    }
          //////////////////////////////////////////////
    
      
     public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        //msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
      /*  if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }*/
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    

  @FXML
    private void Confirmer(ActionEvent event) throws IOException, SQLException {
       ServiceGestionProfil sg = new ServiceGestionProfil(); 

        /////////////////////////////
  
        //////////////////////////////
    
        ////////////////////////////////////////
    
          
              
        // System.out.print((textFieldDatenaissaince.getValue().getYear()-(Calendar.YEAR)));
         
            if (    (this.isValidMail(textFieldEmail.getText()) &&
                (this.isValidPrenom(textFieldNom.getText()))&&
                (this.isValidmdp(textFieldPassword.getText()))&&
                (this.isValidnom(textFieldNom.getText()))&&
                (this.isValidprod(comboProduitPrefere.getValue()))&&
                (this.isValidtel(textFieldTel.getText()))&&
                (this.isValidusername(textFieldUsername.getText()))&&
                (this.isValidville(comboVille.getValue())))
                
                
                )
                
        {
                    LoginFXMLController.userconnecte = new User(textFieldUsername.getText());
      //  User user = new User();
        LoginFXMLController.userconnecte.setNom(textFieldNom.getText());
        LoginFXMLController.userconnecte.setPrenom(textFieldPrenom.getText());
        LoginFXMLController.userconnecte.setUsername(textFieldUsername.getText());
        LoginFXMLController.userconnecte.setEmail(textFieldEmail.getText());
        LoginFXMLController.userconnecte.setPassword(textFieldPassword.getText());
        LoginFXMLController.userconnecte.setNumTel(Integer.parseInt(textFieldTel.getText()));
        LoginFXMLController.userconnecte.setVille(comboVille.getValue());
        LoginFXMLController.userconnecte.setDateNaissance(Date.valueOf(textFieldDatenaissaince.getValue()));
        LoginFXMLController.userconnecte.setProduitPreferee(comboProduitPrefere.getValue());
      
        LoginFXMLController.userconnecte.setActivation(0);
        
      
      
       String message = String.valueOf((textFieldUsername.getText().hashCode()));

       System.out.println(message);
        LoginFXMLController.userconnecte.setCodeConfirmation((userconnecte.getUsername().hashCode()));
        sg.creerCompteClient(LoginFXMLController.userconnecte);
      // sg.creerCompteClient(userconnecte);
        
              String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "alaa.barbou18@gmail.com";
        String password = "alaaesprit18alaaesprit18";
 
        // message info
        String mailTo = textFieldEmail.getText();
        String subject = "Code de Confirmation";
       
 
        // attachments
    /*    String[] attachFiles = new String[2];
        attachFiles[0] = "C:\\wamp64\\www\\Facture"+commandelocale.toString1()+".JPG";
        attachFiles[1]="C:\\Users\\DELL\\Documents\\NetBeansProjects\\PIDEV\\Facture"+" "+commandelocale.toString()+".pdf";
    
 */
        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////////
                 Stage stage = (Stage) Confirmer.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LoginFXML.fxml")));
            window.setScene(scene);  
             window.show();
        }
         else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Entrer des Coordonnéés valide ");
            alert.show();
                     }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
                 Stage stage = (Stage) btnRetour.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ChoixCompteFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }

 
     private boolean isValidMail(String email) {
	if (email == null || "".equals(email))
		return false;
	
	email = email.trim();
	
	EmailValidator ev = EmailValidator.getInstance();
	return ev.isValid(email);
	
}
         private boolean isValidusername(String username) throws SQLException {
             ServiceGestionProfil sg = new ServiceGestionProfil();
	if ((sg.finduser(textFieldUsername.getText())) || (textFieldUsername.getText().length()==0))
        {
		return false;
        }
        else {
            return true;
        }
         }
         private boolean isValidnom(String username) throws SQLException {
     
	if (((textFieldNom.getText().length()) > 20 )||(textFieldNom.getText().length())==0)
        {
		return false;
        }
        else {
            return true;
        }
	
}
                  private boolean isValidPrenom(String username) throws SQLException {
     
	if (((textFieldPrenom.getText().length()) > 20 )||(textFieldPrenom.getText().length())==0)
        {
		return false;
        }
        else {
            return true;
        }
	
}
                                    private boolean isValidtel(String username) throws SQLException {
     
	if (((textFieldTel.getText().length()) > 8 )||((textFieldTel.getText().length())==0))
        {
		return false;
        }
        else {
            return true;
        }
	
}
                                                                        private boolean isValidville(String username) throws SQLException {
     
	if (((comboVille.getValue().length()) == 0 ))
        {
		return false;
        }
        else {
            return true;
        }
	
}
    
                                                 private boolean isValidprod(String username) throws SQLException {
     
	if (((comboProduitPrefere.getValue().length()) == 0 ))
        {
		return false;
        }
        else {
            return true;
        }
	
}
                                                 
                                                               private boolean isValidmdp(String username) throws SQLException {
     
	if ((textFieldPrenom.getText().length())==0)
        {
		return false;
        }
        else {
            return true;
        }
	
}
                                                                                                                                                                private boolean isValidage() throws SQLException {
     
	if ((textFieldDatenaissaince.getValue().getYear()-2018)<18)
        {
                          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Age illegale ");
            alert.show();
            return false;
		
        }
       else if (textFieldDatenaissaince.getValue().equals("")){
                          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Age illegale ");
            alert.show();
            return false;
        }
        else {
            return true;
        }

} 
                                                                                                                                                                
}
