/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.LoginFXMLController.userconnecte;
import static Gui.PanierFXMLController.commandelocale;
import com.itextpdf.text.BadElementException;
import Entity.Commande;
import Entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import service.ServiceCommande;
import service.ServiceGestionProfil;
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

/**
 * FXML Controller class
 *
 * @author Louay Baccary
 */
public class ConfirmationPdfFXMLController implements Initializable {

    @FXML
    private TextField textfieldMail;
    @FXML
    private Button btnFacture;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceGestionProfil sg = new ServiceGestionProfil();
           User us = new User();
        
        try {
            us=sg.recupererUtilisateur(userconnecte.getUsername());
            
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmationPdfFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textfieldMail.setText(us.getEmail());
        
    }    

     public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
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
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    @FXML
    private void facture(ActionEvent event) throws BadElementException, IOException, AddressException, MessagingException {
        ServiceCommande sc = new ServiceCommande();
           sc.pdf(commandelocale);

            // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "alaa.barbou18@gmail.com";
        String password = "alaaesprit18alaaesprit18";
 
        // message info
        String mailTo = textfieldMail.getText();
        String subject = "Votre Facture";
        String message = "Cette Facture est valide pendant 48h.";
 
        // attachments
        String[] attachFiles = new String[2];
        attachFiles[0] = "C:\\wamp64\\www\\Facture"+commandelocale.toString1()+".JPG";
        attachFiles[1]="C:\\Users\\HP\\Documents\\NetBeansProjects\\Produit2\\PIDEVJAVA\\Produit2\\Facture"+" "+commandelocale.toString()+".pdf";
    
 
        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }

    }
          
}

