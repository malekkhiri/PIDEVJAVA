/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Evenement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceEvenement;


import service.ServiceMail;

import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PostUpdate;
import facebook4j.conf.ConfigurationBuilder;
import java.net.MalformedURLException;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class AdminEvenementController implements Initializable {

    @FXML
    private TableView<Evenement> tableviewEvenementt;
    @FXML
    private TableColumn<?, ?> TableColumnNomm;
    @FXML
    private TableColumn<?, ?> TableColumnDescc;
    @FXML
    private TextField searchBoxx;
    @FXML
    private Label labelNomm;
    @FXML
    private Label labelDateDebutt;
    @FXML
    private Label labelDateFinn;
    @FXML
    private Label labelAdressee;
    @FXML
    private Label labelDescriptionn;
    @FXML
    private Label labelParticipee;
    @FXML
    private Label labelMaxx;
    @FXML
    private Button buttonAjouterr;
    @FXML
    private Button buttonModifierr;
    @FXML
    private Button buttonSupprimerr;

    /**
     * Initializes the controller class.
     */
     private List<Evenement> listEvenement1;
    private ObservableList<Evenement> observableListEvenement;
    ServiceEvenement se = new ServiceEvenement();
    @FXML
    private Button btn;
    @FXML
    private ImageView imageViewP;
    @FXML
    private HBox ev;
    @FXML
    private Button showStat;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      try {
            // TODO
            carregarTableViewEvenement() ;
            
            tableviewEvenementt.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewEvenement(newValue));
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvenementnterfaceControllerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     public void carregarTableViewEvenement() throws SQLException {
            listEvenement1 = se.selectEvenement();
       TableColumnNomm.setCellValueFactory(new PropertyValueFactory<>("Nom_Evenement"));
        TableColumnDescc.setCellValueFactory(new PropertyValueFactory<>("Description"));


        observableListEvenement = FXCollections.observableArrayList(listEvenement1);
        tableviewEvenementt.setItems(observableListEvenement);
        tableviewEvenementt.refresh();
        
    }
      public void selecionarItemTableViewEvenement(Evenement evenement){
        if (evenement != null) {
            labelNomm.setText(String.valueOf(evenement.getNom_Evenement()));
            labelAdressee.setText(evenement.getLieux());            
            labelDescriptionn.setText(evenement.getDescription());
// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                Calendar cal = Calendar.getInstance();
//
//                day = cal.get(Calendar.DAY_OF_MONTH);
//                month = cal.get(Calendar.MONTH);
//                year = cal.get(Calendar.YEAR);
                //System.out.println(hour + ":" + (minute) + ":" + second);
                labelDateDebutt.setText(String.valueOf( evenement.getDate_Debut()));
                labelDateFinn.setText(String.valueOf( evenement.getDate_Fin()));
                labelParticipee.setText(Integer.toString(evenement.getNbParticipe()));
                labelMaxx.setText(Integer.toString(evenement.getNbMax()));
            //    bntP.setVisible(false);
        } else {
            labelNomm.setText("");
            labelAdressee.setText("");
            labelDescriptionn.setText("");
            labelDateDebutt.setText("");
            labelDateFinn.setText("");
            labelParticipee.setText("");
            labelMaxx.setText("");
        }
        } 
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
    private void handleButtonAjouterr(ActionEvent event) throws IOException, SQLException {
        Evenement evenement = new Evenement();
        boolean buttonConfirmerClicked = showGestionEvenementManipulation(evenement);
        if (buttonConfirmerClicked) {
        se.insertEvenement(evenement);         
        carregarTableViewEvenement();
        }
    }
    

    @FXML
    private void handleButtonModifierr(ActionEvent event) throws IOException, SQLException {
          Evenement evenement = tableviewEvenementt.getSelectionModel().getSelectedItem();
        if (tableviewEvenementt.getSelectionModel().getSelectedItem()!=null) {
            boolean buttonConfirmerClicked = showGestionEvenementManipulation(evenement);
            if (buttonConfirmerClicked) {
              
se.updateEvenement(evenement);      
carregarTableViewEvenement();
   ServiceMail ser=new ServiceMail();
           SendEmail sm = new SendEmail(ser.selectEmail(evenement.getId()), "Bonjour", "l'evenement que vous avez participer a été modifié");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selectionner un evenement");
            alert.show();

        }
        
    }

    @FXML
    private void handleButtonSupprimerr(ActionEvent event) throws SQLException {
        Evenement evenement = tableviewEvenementt.getSelectionModel().getSelectedItem();
        if (evenement != null) {
se.supprimerEvenement(evenement);
        carregarTableViewEvenement();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choisir une evenement");
            alert.show();

        }
    }

    @FXML
    private void searchRecordd(KeyEvent event) {
        FilteredList<Evenement> filterData=new FilteredList<>(observableListEvenement,p->true);
        searchBoxx.textProperty().addListener((observable, oldValue, newValue)->{
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
               
              return false;
            }
            );
            SortedList<Evenement>sortedList=new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableviewEvenementt.comparatorProperty());
            tableviewEvenementt.setItems(sortedList);
        });
                }

    @FXML
    private void PartagersurFB(ActionEvent event) {
               PostUpdate post = null;
               Evenement sharefb = tableviewEvenementt.getSelectionModel().getSelectedItem();
String accessToken="EAACEdEose0cBADuxZBRARHkLLX4k616rcRSljCQnpBkZAeD3eddOzAoU8rnxGcyf0vZAnCBHZBvz7tMp7HplYANK1zxIqVCkg3vhNq6HZB8yiv2C5pT3JTLKUG4w0JD3wOD4c1PxWbyQ2NgbHxPxaWJ4DRqAqhuAab0q5ZB9e89Az02fZCZAI1L7OJZAyGa77eUCZCX4pEJdfyiAZDZD";
String appId="564166143982848";
String appSecret="4aca5d04f2509c9856d9e94145c8dde6";
String authPermission="publish_actions,manage_pages,publish_pages...";
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAppId(appId)
                .setOAuthAppSecret(appSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthPermissions(authPermission);
        FacebookFactory ff = new FacebookFactory(cb.build());
        facebook4j.Facebook facebook = ff.getInstance();

        try {
            post = new PostUpdate(new URL("https://www.facebook.com/soukmedina.official/"))
                    .picture(new URL("http://127.0.0.1:8000/match/schedule/"))
                    .name(sharefb.getNom_Evenement())
                    .caption("facebook4j.org")
                    .description("VOTRE PUBLICATION \t"+sharefb.getNom_Evenement()+"\n "+sharefb.getDescription()+"\n "+sharefb.getLieux())
                    .message("VOTRE PUBLICATION \t"+sharefb.getNom_Evenement()+"\n "+sharefb.getDescription()+"\n "+sharefb.getLieux());
            System.out.println("VOTRE PUBLICATION \t"+sharefb.getNom_Evenement()+"\n "+sharefb.getDescription()+"\n "+sharefb.getLieux());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {

            facebook.postFeed(post);
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void showStat(ActionEvent event) {
        Parent root = null;
                try {
                    
                    root = FXMLLoader.load(getClass().getResource("/Gui/PieChart.fxml"));
                    Scene s1 = new Scene(root);
                    Stage primaryStage=new Stage();
                    primaryStage.setScene(s1);
                    primaryStage.show();
                    
                    Stage actualStage = (Stage) showStat.getScene().getWindow();
                    actualStage.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(AdminEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
  
 
      
    }
    
    
    
    

