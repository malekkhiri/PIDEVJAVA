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

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class VendeurController implements Initializable {

    @FXML
    private TableView<Evenement> tableviewEvenementtt;
    @FXML
    private TableColumn<?, ?> TableColumnNommm;
    @FXML
    private TableColumn<?, ?> TableColumnDesccc;
    @FXML
    private TextField searchBoxxx;
    @FXML
    private Label labelNommm;
    @FXML
    private Label labelDateDebuttt;
    @FXML
    private Label labelDateFinnn;
    @FXML
    private Label labelAdresseee;
    @FXML
    private Label labelDescriptionnn;
    @FXML
    private Label labelParticipeee;
    @FXML
    private Label labelMaxxx;
    @FXML
    private Button buttonAjouterrr;
    @FXML
    private Button buttonParticiperrr;

    /**
     * Initializes the controller class.
     */
    private List<Evenement> listEvenement2;
    private ObservableList<Evenement> observableListEvenement;
    ServiceEvenement se=new ServiceEvenement();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      try {
            // TODO
            
             
             
            carregarTableViewEvenement() ;
                
            
            tableviewEvenementtt.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewEvenement(newValue));
                
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvenementnterfaceControllerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     public void carregarTableViewEvenement() throws SQLException {
         
            listEvenement2 = se.selectEvenementVendeur();
       TableColumnNommm.setCellValueFactory(new PropertyValueFactory<>("Nom_Evenement"));
//       TableColumnAdresse.setCellValueFactory(new PropertyValueFactory<>("Lieux"));
        TableColumnDesccc.setCellValueFactory(new PropertyValueFactory<>("Description"));
//       TableColumnDebut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
//       TableColumnFin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
//       TableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
//       TableColumnNbParticipe.setCellValueFactory(new PropertyValueFactory<>("nbParticipe"));

     

        observableListEvenement = FXCollections.observableArrayList(listEvenement2);
        tableviewEvenementtt.setItems(observableListEvenement);
        tableviewEvenementtt.refresh();
        
    }
          
      public void selecionarItemTableViewEvenement(Evenement evenement){
        if (evenement != null) {
            labelNommm.setText(String.valueOf(evenement.getNom_Evenement()));
            labelAdresseee.setText(evenement.getLieux());            
            labelDescriptionnn.setText(evenement.getDescription());
// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                Calendar cal = Calendar.getInstance();
//
//                day = cal.get(Calendar.DAY_OF_MONTH);
//                month = cal.get(Calendar.MONTH);
//                year = cal.get(Calendar.YEAR);
                //System.out.println(hour + ":" + (minute) + ":" + second);
                labelDateDebuttt.setText(String.valueOf( evenement.getDate_Debut()));
                labelDateFinnn.setText(String.valueOf( evenement.getDate_Fin()));
                labelParticipeee.setText(Integer.toString(evenement.getNbParticipe()));
                labelMaxxx.setText(Integer.toString(evenement.getNbMax()));
            //    bntP.setVisible(false);
        } else {
            labelNommm.setText("");
            labelAdresseee.setText("");
            labelDescriptionnn.setText("");
            labelDateDebuttt.setText("");
            labelDateFinnn.setText("");
            labelParticipeee.setText("");
            labelMaxxx.setText("");
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
    private void searchRecorddd(KeyEvent event) {
         FilteredList<Evenement> filterData=new FilteredList<>(observableListEvenement,p->true);
        searchBoxxx.textProperty().addListener((observable, oldValue, newValue)->{
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
            sortedList.comparatorProperty().bind(tableviewEvenementtt.comparatorProperty());
            tableviewEvenementtt.setItems(sortedList);
        });
                }
    

    @FXML
    private void handlebuttonAjouterrr(ActionEvent event) throws SQLException, IOException {
         Evenement evenement = new Evenement();
        boolean buttonConfirmerClicked = showGestionEvenementManipulation(evenement);
        if (buttonConfirmerClicked) {
        se.insertEvenement(evenement);         
        carregarTableViewEvenement();
        }
    }

    @FXML
    private void handlebuttonParticiperrr(ActionEvent event) throws SQLException {
                        Evenement evenement = tableviewEvenementtt.getSelectionModel().getSelectedItem();

    
         if (tableviewEvenementtt.getSelectionModel().getSelectedItem()!=null && evenement.getNbParticipe()<=evenement.getNbMax()) {
             ServiceEvenement se = new ServiceEvenement();
                   
               se.participer(evenement);
               carregarTableViewEvenement();

            
               
    }
  else
  {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("vous etes deja participé");
            alert.show();

  }
    
}
}
