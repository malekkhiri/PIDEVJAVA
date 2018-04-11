
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DataBase.DataSource;
import static Gui.LoginFXMLController.userconnecte;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Commande;
import com.mysql.jdbc.Connection;
import Entity.Produit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import com.itextpdf.text.Image;
import Entity.User;
import Gui.NewFXMain1;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Louay Baccary
 */
public class ServiceCommande {
    
      
    Connection conn;
    Statement statement;
    ResultSet rs;
   
    
    public ServiceCommande(){
        conn=(Connection) DataSource.getInstance().getConnection();
    }
    
    public void insertCommande(Produit Produit){
        String req="INSERT INTO `commande`(`id_utilisateur`, `id_Produit`, `Quantite`,`Statut`) VALUES (?,?,1,?)";
       // String req1="Update Produit set Quantite=? WHERE id_Produit=?";
        ServiceGestionProfil sg = new ServiceGestionProfil();
        try {
             //   PreparedStatement stmt = conn.prepareStatement(req);
           // ResultSet res= stmt.executeQuery();
      
            //Commande Commande = new Commande(res.getInt("id"),this.findCategorie(res.getInt("id_Produit")),res.getInt("id_utilisateur"),res.getInt("Quantite"));
            PreparedStatement ste= conn.prepareStatement(req);
         //   PreparedStatement ste1= conn.prepareStatement(req1);
           // ste.setInt(1, commande.getId_utilisateur());
           ste.setInt(2,Produit.getId_Produit());
           NewFXMain1 main=new NewFXMain1();
           ste.setInt(1,main.u.getId());
           ste.setString(3,"En Cours");
      
           
             //ste1.setInt(1,Produit.getQuantite()-1);
                    /*ste1.setInt(2, Produit.getId_Produit());
                     ste1.execute();*/
           
           
            ste.executeUpdate();
           // System.out.print(Produit.getId_Produit());
       
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean ModifierQuantite(Commande Commande) throws SQLException{
        String req="UPDATE `Commande` Set `Quantite`=? Where id=?";
        String req1="Update Produit set Quantite=? WHERE id_Produit=?";
        try {
        PreparedStatement ste= conn.prepareStatement(req);
        PreparedStatement ste1= conn.prepareStatement(req1);
        ste.setInt(1,Commande.getQuantite());
        ste.setInt(2,Commande.getId());
        ste1.setInt(1,Commande.getId_produit().getQuantite());
        ste1.setInt(2,Commande.getId_produit().getId_Produit());
     //   ste.setInt(2,Commande.getId_produit()):
        ste1.execute();
        ste.execute();
        return true;
    }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
}


  /*  public List<Commande> selectCommande(){
        String req="select * from commande";
        List<Commande> liste= new ArrayList<>();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
            while(rs.next()){
                Commande g =new Commande(rs.getInt(2), rs.getInt(3));
                liste.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    public void updateCommande(Commande commande, int id){
        List<Commande> liste=new ArrayList<>();
        String req="UPDATE `commande` SET `id_utilisateur`=?,`id_produit`=? WHERE id=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
            ste.setInt(1, id);
           ste.setInt(2, commande.getId_utilisateur());
         //   ste.setInt(3, commande.getId_produit());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    */
    public void supprimerCommande(Commande commande ){
        String req="DELETE FROM commande WHERE id=?";
       // String req1="Update Produit set Quantite=? WHERE id_Produit=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
         //   PreparedStatement ste1= conn.prepareStatement(req1);
            ste.setInt(1, commande.getId());
            
            ste.executeUpdate();
       
                    
           //         ste1.setInt(1,((commande.getId_produit().getQuantite())+(commande.getQuantite())));
             //       ste1.setInt(2, commande.getId_produit().getId_Produit());
               //      ste1.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void supprimer(Produit produit ){
        String req="DELETE FROM commande WHERE id_produit=?";
       // String req1="Update Produit set Quantite=? WHERE id_Produit=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
           // PreparedStatement ste1= conn.prepareStatement(req1);
            ste.setInt(1, produit.getId_Produit());
            
            ste.executeUpdate();
       
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public List<Commande> afficher() {
        String sql = "SELECT * FROM  Commande Where id_utilisateur=?";
   /*     String nomProduit ="SELECT Nom_Produit FROM PRODUIT  P WHERE P.id_Produit = Commande.id_Produit";
        String Prix ="SELECT Prix FROM PRODUIT  P WHERE P.id_Produit = Commande.id_Produit";
        String Description ="SELECT Description FROM PRODUIT  P WHERE P.id_Produit = Commande.id_Produit";
       // String Quantite ="SELECT Quantite FROM Commande";*/
        List<Commande> listproduit = new ArrayList<>();
           ServiceGestionProfil sg = new ServiceGestionProfil();
         try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            NewFXMain1 main=new NewFXMain1();
             stmt.setInt(1,main.u.getId());
            ResultSet res= stmt.executeQuery();
            while (res.next()) {
                //produit produit = new produit();
                Commande Commande = new Commande(res.getInt("id"),this.findCategorie(res.getInt("id_Produit")),res.getInt("id_utilisateur"),res.getInt("Quantite"),res.getString("Statut")); 
             
             /*   produit.setDescription(res.getString("Description"));
                produit.setQuantite(res.getInt("Quantite"));
                */
               
                 /*
                 Commande.setId_utilisateur(res.getInt("id_utilisateur"));
                 Commande.setId_produit(res.getInt("id_produit"));
           */
                
                listproduit.add(Commande);
            }
             } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        return listproduit;
    }
            public List<Commande> afficherVendeur() {
        String sql = "SELECT c.* FROM `commande` c JOIN produit p ON p.id_Produit=c.`id_Produit` WHERE p.`id_utilisateur`=? And Statut IN ('En Cours', 'Non Payé' )";
   /*     String nomProduit ="SELECT Nom_Produit FROM PRODUIT  P WHERE P.id_Produit = Commande.id_Produit";
        String Prix ="SELECT Prix FROM PRODUIT  P WHERE P.id_Produit = Commande.id_Produit";
        String Description ="SELECT Description FROM PRODUIT  P WHERE P.id_Produit = Commande.id_Produit";
       // String Quantite ="SELECT Quantite FROM Commande";*/
        List<Commande> listproduit = new ArrayList<>();
           ServiceGestionProfil sg = new ServiceGestionProfil();
         try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            // stmt.setInt(1,sg.recupererUtilisateur(userconnecte.getUsername()).getId());
            // stmt.setString(3, "En Cours");
            NewFXMain1 main=new NewFXMain1();
             stmt.setInt(1,main.u.getId());
            // stmt.setString(2, "En Cours");
            ResultSet res= stmt.executeQuery();
            while (res.next()) {
                //produit produit = new produit();
                Commande Commande = new Commande(res.getInt("id"),this.findCategorie(res.getInt("id_Produit")),res.getInt("id_utilisateur"),res.getInt("Quantite"),res.getString("Statut")); 
             
             /*   produit.setDescription(res.getString("Description"));
                produit.setQuantite(res.getInt("Quantite"));
                */
               
                 /*
                 Commande.setId_utilisateur(res.getInt("id_utilisateur"));
                 Commande.setId_produit(res.getInt("id_produit"));
           */
                
                listproduit.add(Commande);
            }
             } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        return listproduit;
    }
      
          public List<Produit> afficherproduit() {
        String sql = "SELECT * FROM produit";
        List<Produit> listProduit = new ArrayList<>();
         try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet res= stmt.executeQuery();
            while (res.next()) {
                Produit Produit = new Produit();
                Produit.setId_Produit(res.getInt("id_Produit"));
                Produit.setPrix(res.getInt("Prix"));
                Produit.setDescription(res.getString("Description"));
                Produit.setQuantite(res.getInt("Quantite"));
                
           
                listProduit.add(Produit);
            }
             } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        return listProduit;
    }
        /*  public void Ajouterpanier (produit p) throws SQLException

{String req="insert into Commande (id_Produit) values (?)";

PreparedStatement prs=conn.prepareStatement(req);

prs.setInt(1,p.getId_Produit());

prs.executeUpdate();}
*/
    public Produit findCategorie(int id) throws SQLException
    { Produit a=null;
       statement  = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from produit where id_Produit="+"'"+id+"'");
           while(rs.next())
           {   a=new Produit(
                         
                        rs.getInt(3),
                       
                           rs.getString(5),
                                   rs.getInt(6),
                                          
                                                   rs.getInt(8),
 rs.getString(7)                      
              
                        );
            
           } statement.close();
        
        return a;
    }
    public boolean payment(Commande Commande){
        
           String req="UPDATE `Commande` Set `Statut`=? Where id=?";
     
        try {
        PreparedStatement ste= conn.prepareStatement(req);
      
        ste.setString(1,"En Cours");
        ste.setInt(2,Commande.getId());
      
     //   ste.setInt(2,Commande.getId_produit()):
       
        ste.executeUpdate();
        this.afficher();
        return true;
    }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            this.afficher();
            return false;
        }
        
    }
     public void ViderCommande(Commande commande ){
        String req="DELETE FROM commande WHERE id=?";
      //  String req1="Update Produit set Quantite=? WHERE id_Produit=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
         //   PreparedStatement ste1= conn.prepareStatement(req1);
            ste.setInt(1, commande.getId());
            
            ste.executeUpdate();
       
                    
           //         ste1.setInt(1,((commande.getId_produit().getQuantite())+(commande.getQuantite())));
               //     ste1.setInt(2, commande.getId_produit().getId_Produit());
                 //    ste1.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public boolean findproduit(Produit produit) throws SQLException
     {
         String req="Select COUNT(id_Produit) as isThere from Commande where id_Produit=? And id_utilisateur=?";
         ServiceGestionProfil sg = new ServiceGestionProfil();
         try {
             
            PreparedStatement ste= conn.prepareStatement(req);
         ste.setInt(1,produit.getId_Produit());
         NewFXMain1 main=new NewFXMain1();
          ste.setInt(2,main.u.getId());
        ResultSet res= ste.executeQuery();
        while(res.next()){
        if ( res.getInt("isThere")!=0){
            return false;
            
        } else {
            return true;
        }
        }
     
        
         }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
}
        return false;
}
     public boolean validervendeur(Commande Commande) throws SQLException, BadElementException, IOException{
    
                  String req="UPDATE `Commande` Set `Statut`=? Where id=?";
        String req1="Update Produit set Quantite=? WHERE id_Produit=?";
             dureeAttente(Commande);
        // pdf(Commande);
       //  mailsend(Commande);
        try {
        PreparedStatement ste= conn.prepareStatement(req);
        PreparedStatement ste1= conn.prepareStatement(req1);
        ste.setString(1,"Validée");
        ste.setInt(2,Commande.getId());
        ste1.setInt(1,Commande.getId_produit().getQuantite()-Commande.getQuantite());
        ste1.setInt(2,(Commande.getId_produit().getId_Produit()));
      // ste.setInt(2,Commande.getId_produit()):
        ste1.execute();
        ste.execute();
        this.afficher();
         
        return true;
        
    }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            this.afficher();
            return false;
        }
       
     }
     public void refuservendeur(Commande Commande){
                 String req="UPDATE `Commande` Set `Statut`=? Where id=?";
       // String req1="Update Produit set Quantite=? WHERE id_Produit=?";
        try {
            PreparedStatement ste= conn.prepareStatement(req);
           // PreparedStatement ste1= conn.prepareStatement(req1);
            ste.setString(1,"Non Validée");
            ste.setInt(2,Commande.getId());
            ste.executeUpdate();
       
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
        }
      public void dureeAttente(Commande Commande) throws SQLException{
          String req="CREATE DEFINER=`root`@`localhost`EVENT myevent\n" +
"    ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 120 SECOND  \n" +
"    DO\n" +
"      UPDATE user.commande SET Statut = 'Non Payé';";
          
          
                  java.util.Date myDate = new java.util.Date();
                  Calendar cal = Calendar.getInstance();
cal.setTime(myDate);
cal.add(Calendar.MINUTE, 1);
SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.000000");
 
String myDateToString = myFormat.format(myDate);
          
          
    /*   String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
          
          String time = "'"+timeStamp+"'";*/
        
  
           try {
            
          PreparedStatement ste= conn.prepareStatement(req);
         // ste.setString(1,"time" );
        //  ste.setString(1, myDateToString);
           //ste.setInt(1,Commande.getId());
            ste.execute();
            } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      
      
      
      
      public void pdf(Commande Commande) throws BadElementException, IOException{
          
                            java.util.Date myDate = new java.util.Date();
                  Calendar cal = Calendar.getInstance();
cal.setTime(myDate);
SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
 
String myDateToString = myFormat.format(myDate);
                  Document document = new Document(PageSize.A6);
        document.addAuthor("Soug El Mdina");
        document.addTitle("Facture");
        System.out.println("Document Created");
        generateQRCodeImage(Commande,"Facture"+Commande.toString1());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Facture"+" "+Commande.toString()+".pdf"));
             System.out.println("Writer instance created");
             Paragraph titre = new Paragraph("Souk El Mdina");
             Paragraph Date = new Paragraph(myDateToString);
             Paragraph NomProduit = new Paragraph("Vous voulez achter"+" "+Commande.getId_produit().getNom_Produit());
             Paragraph Prix = new Paragraph("Prix :"+String.valueOf((Commande.getId_produit().getPrix())*(Commande.getQuantite()))+" ");
             document.open();
             document.add(titre);
             document.add(Date);
             document.add(NomProduit);
             document.add(Prix);
 Image image = Image.getInstance("http://127.0.0.1//Facture"+Commande.toString1()+".JPG");
      document.add(image);        
              System.out.println("Document Created");
      
        }catch (DocumentException e){
            e.printStackTrace();
        }catch(FileNotFoundException e){
            e.printStackTrace();
    }
        document.close();
      }
   
       public  void generateQRCodeImage(Commande Commande,String src) throws FileNotFoundException, IOException { 
        String details = Commande.toString();
        ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
        File f = new File("C:\\wamp64\\www\\"+src+".JPG");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();
        
    }
         public void mailsend(Commande Commande,String adresse){
             ServiceCommande sc = new ServiceCommande();
              try{
        String host ="smtp.gmail.com";
                String user ="alaa.barbou18@gmail.com";
                String pass ="alaaesprit18alaaesprit18";
                String from ="alaa.barbou18@gmail.com";
                String to =adresse;
                String subject ="Consultation du mot de passe ";
                String messageText ="votre mot de passe est zabeb";
                boolean sessionDebug =false ;
                
                Properties props = System.getProperties();
                
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");
                
                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession =Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new java.util.Date());
                msg.setText(messageText);
               
     /*  String file = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\PIDEV\\Facture"+Commande.toString()+".pdf";
        String fileName = "Votre Facture";
        FileDataSource source = new FileDataSource(file);
        msg.setDataHandler(new DataHandler(source));
        msg.setFileName(fileName);
      */
                
                Transport transport =mailSession.getTransport("smtp");
                transport.connect(host, user , pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                System.out.println("message envoyé");
        }
                catch(Exception ex){
                        ex.printStackTrace();
                        }
        }
              public  String sendto (Commande Commande) throws SQLException{
       
       System.out.print(Commande.getId_utilisateur());
         String s = "begin";
        String req="SELECT u.id,u.email as ml FROM `commande`c JOIN user u on c.`id_utilisateur`= u.id WHERE c.`id`="+Commande.getId_utilisateur();
        try{  
             User us = new User();
            Statement ste= conn.createStatement();
            //  ste.setInt(1, Commande.getId_utilisateur() );
                     
        
     
         
         
		
              rs = ste.executeQuery(req);
        while(rs.next()){
             us= new User();
             s=rs.getString("ml");
             System.out.println(s);
             Commande.getId_user().setEmail(rs.getString("ml"));
             Commande.getId_user().setId(rs.getInt("id"));
            
          
             
      
        
        }
        return s ;
    }                                   catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
       
         

}
              public boolean isstatut(Commande Commande)
              {
                  String req="Select Statut as test from Commande Where id_utilisateur=? And id=?";
                    ServiceGestionProfil sg = new ServiceGestionProfil();
         try {
             
            PreparedStatement ste= conn.prepareStatement(req);
         //ste.setString(2,"Validée");
         ste.setInt(2, Commande.getId());
         NewFXMain1 main=new NewFXMain1();
          ste.setInt(1,main.u.getId());
        ResultSet res= ste.executeQuery();
        while(res.next()){
        if ( res.getString("test").equals("Validée")){
            return true;
            
        } else {
            return false;
        }
        }
     
        
         }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
}
        return false;
              }
              
              public void AnnulerCommande(Commande Commande) throws SQLException{
                    String req1="Update Produit set Quantite=? WHERE id_Produit=?";
                    try {
                      PreparedStatement ste1= conn.prepareStatement(req1);
         
        ste1.setInt(1,Commande.getId_produit().getQuantite()+Commande.getQuantite());
        ste1.setInt(2,(Commande.getId_produit().getId_Produit()));
      // ste.setInt(2,Commande.getId_produit()):
        ste1.execute();
              }catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
}
        this.afficher();
              }
      




 public User selectUser(){
                    String req = "select U.username from user U inner join commande C on (U.id_utilisateur=C.id_utilisateur) ";

        User u = new User();
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(req);
           
            while(rs.next()){
                u.setUsername(rs.getString("username"));
                
                
            }
            
        }
        catch (SQLException e) {
            System.out.println(e);;
        }
        return u ;
    }
 

}
     

