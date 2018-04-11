/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DataBase.DataSource;
import Entity.Evenement;
import Entity.Participer;
import Entity.User;
import static Gui.NewFXMain.main;
import static Gui.NewFXMain1.main;
import static Gui.NewFXMain2.main;
import static Gui.NewFXMain3.main;
import static Gui.Produit2.main;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import service.ServiceEvenement;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import service.ServiceMail;


/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class GestionEvenementnterfaceControllerController implements Initializable {

    @FXML
    private TableColumn<Evenement, String> TableColumnNom;
    @FXML
    private TableColumn<Evenement, String> TableColumnDesc;
    private TableColumn<Evenement, Integer> TableColumnId;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelDateDebut;
    @FXML
    private Label labelDateFin;
    @FXML
    private Label labelAdresse;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelParticipe;
    
    @FXML
    private TableView<Evenement> tableviewEvenement;

    private List<Evenement> listEvenement;
    private ObservableList<Evenement> observableListEvenement;
    
    private final  Connection con = DataSource.getInstance().getConnection();
    private final ServiceEvenement se = new ServiceEvenement();
   private int year;
private int month;
private int day;
   
    @FXML
    private AnchorPane btnP;
    @FXML
    private TextField searchBox;
    @FXML
    private Label labelMax;
    @FXML
    private Button bntP;

    public TableColumn<Evenement, String> getTableColumnNom() {
        return TableColumnNom;
    }

    private User user;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
             
             
            carregarTableViewEvenement() ;
                
            
            tableviewEvenement.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewEvenement(newValue));
                
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvenementnterfaceControllerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     public void carregarTableViewEvenement() throws SQLException {
         
            listEvenement = se.selectEvenementVendeur();
       TableColumnNom.setCellValueFactory(new PropertyValueFactory<>("Nom_Evenement"));
//       TableColumnAdresse.setCellValueFactory(new PropertyValueFactory<>("Lieux"));
        TableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
//       TableColumnDebut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
//       TableColumnFin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
//       TableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
//       TableColumnNbParticipe.setCellValueFactory(new PropertyValueFactory<>("nbParticipe"));

     

        observableListEvenement = FXCollections.observableArrayList(listEvenement);
        tableviewEvenement.setItems(observableListEvenement);
        tableviewEvenement.refresh();
        
    }
          
      public void selecionarItemTableViewEvenement(Evenement evenement){
        if (evenement != null) {
            labelNom.setText(String.valueOf(evenement.getNom_Evenement()));
            labelAdresse.setText(evenement.getLieux());            
            labelDescription.setText(evenement.getDescription());
// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                Calendar cal = Calendar.getInstance();
//
//                day = cal.get(Calendar.DAY_OF_MONTH);
//                month = cal.get(Calendar.MONTH);
//                year = cal.get(Calendar.YEAR);
                //System.out.println(hour + ":" + (minute) + ":" + second);
                labelDateDebut.setText(String.valueOf( evenement.getDate_Debut()));
                labelDateFin.setText(String.valueOf( evenement.getDate_Fin()));
                labelParticipe.setText(Integer.toString(evenement.getNbParticipe()));
                labelMax.setText(Integer.toString(evenement.getNbMax()));
            //    bntP.setVisible(false);
        } else {
            labelNom.setText("");
            labelAdresse.setText("");
            labelDescription.setText("");
            labelDateDebut.setText("");
            labelDateFin.setText("");
            labelParticipe.setText("");
            labelMax.setText("");
        }
        }

//    public void handleButtonAjouter() throws IOException, SQLException {
//        
//        Evenement evenement = new Evenement();
//        boolean buttonConfirmerClicked = showGestionEvenementManipulation(evenement);
//        if (buttonConfirmerClicked) {
//        se.insertEvenementR(evenement); 
//                  
//        carregarTableViewEvenement();
//        
//        }
//    }
    

//      public void handleButtonModifier() throws IOException, SQLException {
//       
//        
//        Evenement evenement = tableviewEvenement.getSelectionModel().getSelectedItem();
//        if (tableviewEvenement.getSelectionModel().getSelectedItem()!=null) 
//        {
//            boolean buttonConfirmerClicked = showGestionEvenementManipulation(evenement);
//            if (buttonConfirmerClicked) 
//            {
//              
//se.updateEvenement(evenement);      
//carregarTableViewEvenement();
//            }
//        } else {
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setContentText("Selectionner un evenement");
//            alert.show();
//
//        
//        
//        
//    }}
//      public void handleButtonSupprimer() throws IOException, SQLException {
//        Evenement evenement = tableviewEvenement.getSelectionModel().getSelectedItem();
//        if (evenement != null) {
//se.supprimerEvenement(evenement);
//        carregarTableViewEvenement();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Choisir une evenement");
//            alert.show();
//
//        }
//    }
     public boolean showGestionEvenementManipulation(Evenement evenement) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionEvenementnterfaceControllerController.class.getResource("ajoutFXML.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Evenement");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        AjoutFXMLController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEvenement(evenement);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmerClicked();

    }
@FXML
    private void participerevent(ActionEvent event) throws SQLException, IOException{
               NewFXMain1 main = new NewFXMain1();

                Participer p =new Participer();
                Evenement evenement = tableviewEvenement.getSelectionModel().getSelectedItem();
                 if(se.Existee(evenement.getId())==false && evenement.getNbParticipe()<=evenement.getNbMax()){
                   
              se.participer(evenement);
            p.setIdEvenement(evenement.getId());
             p.setEmail(main.u.getEmail());
             
                 
  if (tableviewEvenement.getSelectionModel().getSelectedItem()!=null ) {
             ServiceEvenement se = new ServiceEvenement();
                   
                          se.EvenementModifier(p);

          
               carregarTableViewEvenement();
               ServiceMail ser=new ServiceMail();
           SendEmail sm = new SendEmail(ser.selectEmail(evenement.getId()), "Bonjour", "Vous avez participer a un evenement");
           
            
               
    }
  else
  {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("selectionner un evenement");
            alert.show();

  }
    }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("utilisateur deja participé");
            alert.show(); 
            }
}

    @FXML
    private void searchRecord(KeyEvent event) {
        
        FilteredList<Evenement> filterData=new FilteredList<>(observableListEvenement,p->true);
        searchBox.textProperty().addListener((observable, oldValue, newValue)->{
            filterData.setPredicate((t) -> {
                
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String typedText=newValue.toLowerCase();
              if (t.getNom_Evenement().toLowerCase().indexOf(typedText)!=-1) {
                   return true;
              }
               if (t.getDescription().toLowerCase().indexOf(typedText)!=-1) {
                   return true;
              }
                if (t.getLieux().toLowerCase().indexOf(typedText)!=-1) {
                   return true;
                   
              }
                  if (t.getDate_Debut().toString().indexOf(typedText)!=-1) {
                   return true;
                   
              }
                     if (t.getDate_Fin().toString().indexOf(typedText)!=-1) {
                   return true;
                   
              }
              return false;
            }
            );
            SortedList<Evenement>sortedList=new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableviewEvenement.comparatorProperty());
            tableviewEvenement.setItems(sortedList);
        });
                }

    
        
    @FXML
    private void Produit (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }
    
    @FXML
    private void Magasin (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("MagasinFront.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }}
    
    
    @FXML
    private void Evenement (MouseEvent event) throws IOException
    {
        
        
      
           NewFXMain1 main= new NewFXMain1();
           try{
           if(main.u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")){
                
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("Vendeur.fxml"));
                 Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
      
           }
              
     
           else if (main.u.getRoles().equals("a:1:{i:0;s:16:\"ROLE_UTILISATEUR\";}")){
              
                             Parent home_page_parent = FXMLLoader.load(getClass().getResource("GestionEvenementnterfaceController.fxml"));
  Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
           }                  
           else{
               System.out.println("erreur");
           }
             
       
    
    } catch (IOException ex) {
           
               }    
    }
    
    
    
    @FXML
    private void Panier (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("PanierFXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
}


@FXML
    private void Profil (MouseEvent event) throws IOException
    {
        
        
      
           NewFXMain1 main= new NewFXMain1();
           try{
           if(main.u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")){
                
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("ProfilVendeurFXML.fxml"));
                 Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
      
           }
              
     
           else if (main.u.getRoles().equals("a:1:{i:0;s:16:\"ROLE_UTILISATEUR\";}")){
              
                             Parent home_page_parent = FXMLLoader.load(getClass().getResource("PofilClientFXML.fxml"));
  Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
           }                  
           else{
               System.out.println("erreur");
           }
             
       
    
    } catch (IOException ex) {
           
               }    
    }
    

  @FXML
    private void Reclamation (MouseEvent event) throws IOException
    {
        
        
       try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("ReclamationRS.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
}
    
          @FXML
    private void ClickMenu(MouseEvent event) throws IOException {

 try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }
    }

