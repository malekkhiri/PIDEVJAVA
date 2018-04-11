/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Evenement;
import java.sql.Connection;
import DataBase.DataSource;
import Entity.Participer;
import Gui.NewFXMain1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author ASUS-PC
 */
public class ServiceEvenement {
      Connection conn= DataSource.getInstance().getConnection();
    private Statement ste;

    public ServiceEvenement() {
         try {
            ste = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
 
    
    public void insertEvenement(Evenement ev)throws SQLException{
        String req="INSERT INTO"
                + " `evenement`(`Date_Debut`, `Date_Fin`, `Nom_Evenement`, `Lieux`, `Description`, `nbParticipe`,`nbMax`,`roleuser`) "
                + "VALUES (?,?,?,?,?,?,?,?)";
      
        
            PreparedStatement ste= conn.prepareStatement(req);
      
            ste.setDate(1,  ev.getDate_Debut());
             ste.setDate(2,  ev.getDate_Fin());
            ste.setString(3, ev.getNom_Evenement());
             ste.setString(4, ev.getLieux());
              ste.setString(5, ev.getDescription());
             ste.setInt(6, ev.getNbParticipe());
             ste.setInt(7, ev.getNbMax());
ste.setString(8,ev.getRoleuser());
             
            ste.executeUpdate();
             System.out.println("evenement créé!!!!");
        
   
    }
    
    public List<Evenement> selectEvenement() throws SQLException{
        String req="select * from evenement";
        
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(req);
        List<Evenement> liste= new ArrayList<>();
        try {
            ste=conn.createStatement();
            while(rs.next()){
               Evenement e =new Evenement(rs.getInt("Id"),rs.getDate("Date_Debut"), rs.getDate("Date_Fin"), rs.getString("Nom_Evenement"),rs.getString("Lieux"),rs.getString("Description"),rs.getInt("nbParticipe"),rs.getInt("nbMax"));
                liste.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    public void updateEvenement(Evenement evenement){
       try {
           String req="UPDATE `evenement` SET `Date_Debut`=?,`Date_Fin`=?,`Nom_Evenement`=?,"
                + "`Lieux`=?,`Description`=?,`nbParticipe`=?,`nbMax`=? WHERE id=?";
       
            PreparedStatement ste= conn.prepareStatement(req);
           ste.setDate(1,  evenement.getDate_Debut());
             ste.setDate(2, evenement.getDate_Fin());
            ste.setString(3, evenement.getNom_Evenement());
              ste.setString(4, evenement.getLieux());
              ste.setString(5, evenement.getDescription());
             ste.setInt(6, evenement.getNbParticipe());
             ste.setInt(8, evenement.getId());
             ste.setInt(7, evenement.getNbMax());


            ste.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex);        
        }
        
    }
    
    
    
    public void supprimerEvenement(Evenement evenement){
       try {
           String req="DELETE FROM `evenement` WHERE `Nom_Evenement`=?";
        
           PreparedStatement pst = conn.prepareStatement(req);
            pst.setString(1, evenement.getNom_Evenement());
            pst.executeUpdate();
            System.out.println("Event suppriméé!!!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void participer(Evenement evenement) throws SQLException
    {
         String r="UPDATE  evenement SET nbParticipe=nbParticipe+1 WHERE id=?";
              Connection conn = DataSource.getInstance().getConnection();
              PreparedStatement st = conn.prepareStatement(r);
              st.setInt(1, evenement.getId());
              st.executeUpdate();
    }
    
     public void EvenementModifier(Participer ev) throws SQLException
    {
       String e=" INSERT INTO `participer`(`idEvenement`, `email`) VALUES (?,?)";
        PreparedStatement ste= conn.prepareStatement(e);
      
            ste.setInt(1,  ev.getIdEvenement());
             ste.setString(2,  ev.getEmail());
           
             
            ste.executeUpdate();
    }
  /*  public boolean ifParticiper(Evenement evenement){
        String req="SELECT Evenement "
    }*/
    public void insertEvenementR(Evenement ev)throws SQLException{
        String req="INSERT INTO"
                + " `evenement`(`Date_Debut`, `Date_Fin`, `Nom_Evenement`, `Lieux`, `Description`, `nbParticipe`, `roleuser`,`nbMax`) "
                + "VALUES (?,?,?,?,?,?,?,?)";
      
        
            PreparedStatement ste= conn.prepareStatement(req);
      
            ste.setDate(1,  ev.getDate_Debut());
             ste.setDate(2,  ev.getDate_Fin());
            ste.setString(3, ev.getNom_Evenement());
             ste.setString(4, ev.getLieux());
              ste.setString(5, ev.getDescription());
             ste.setInt(6, ev.getNbParticipe());
             ste.setString(7, ev.getRoleuser());
             ste.setInt(8, ev.getNbMax());
             
            ste.executeUpdate();
             System.out.println("evenement créé!!!!");
        
   
    }
     public List<Evenement> selectEvenementVendeur() throws SQLException{
        String req="select * from evenement";
        
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(req);
        List<Evenement> liste= new ArrayList<>();
        try {
            ste=conn.createStatement();
            while(rs.next()){
               Evenement e =new Evenement(rs.getInt("id"),rs.getDate("Date_Debut"), rs.getDate("Date_Fin"), rs.getString("Nom_Evenement"),rs.getString("Description"),rs.getString("Lieux"),rs.getInt("nbParticipe"),rs.getString("roleuser"),rs.getInt("nbMax") );
             NewFXMain1 main=new NewFXMain1();
               if((main.u.getRoles().equalsIgnoreCase("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}"))&& (e.getRoleuser().equalsIgnoreCase("a:2:{i:0;s:16:\"ROLE_UTILISATEUR\";i:1;s:10:\"ROLE_ADMIN\";}")))  
               {
               liste.add(e);
            }
               else if((main.u.getRoles().equalsIgnoreCase("a:1:{i:0;s:16:\"ROLE_UTILISATEUR\";}"))&& (e.getRoleuser().equalsIgnoreCase("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")))
               {
                   liste.add(e);
               }
               else {
                   
               }
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
     
//     public Class SendEmail() {
//        
//            Participer p = new Participer();
//
//      // Recipient's email ID needs to be mentioned.
//      String to = p.getEmail();
//
//      // Sender's email ID needs to be mentioned
//      String from = "elyesmestiri95@gmail.com";
//
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//
//      // Get system properties
//      Properties properties = System.getProperties();
//
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//
//      try {
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//         // Set Subject: header field
//         message.setSubject("This is the Subject Line!");
//
//         // Now set the actual message
//         message.setText("This is actual message");
//
//         // Send message
//         Transport.send(message);
//         System.out.println("Sent message successfully....");
//      } catch (MessagingException mex) {
//      }
//          return null;
//   }
    public boolean  Existee(int idE) throws SQLException{
        String req2="select * from participer";
        boolean resultat=false;
         Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(req2);
        try{
         ste=conn.createStatement();
                      while(rs.next()){

Participer par=new Participer(rs.getInt("idEvenement"), rs.getString("email"));
NewFXMain1 main=new NewFXMain1();
if(main.u.getEmail().equals(par.getEmail()) && par.getIdEvenement()==idE){
    resultat=true;
   
}
else{
    resultat = false;
}

         
    }    }catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
    }
       
                      return resultat;
    }         
    
    public ObservableList<PieChart.Data> StatNbrParticipant() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = conn.prepareStatement("select sum(nbParticipe),Nom_Evenement from evenement group BY Nom_Evenement");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(2), rs.getInt(1)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
            //System.out.println("ici" + observableList.size());
            return observableList;

        } catch (SQLException ex) {
             Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

        }


        
