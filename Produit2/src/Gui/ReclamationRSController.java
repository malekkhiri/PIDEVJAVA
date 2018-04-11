/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DataBase.DataSource;
import Entity.Commande;
import Entity.Reclamation;
import Entity.User;
import service.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
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
import services.SendMail;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class ReclamationRSController implements Initializable {

    @FXML
    private TableView<Reclamation> tableViewReclamation;
    @FXML
    private TableColumn<?, ?> tableColumnContenu;
    @FXML
    private Label labelReclamationEmetteur;
    @FXML
    private Label labelReclamationContenu;
    @FXML
    private Label labelReclamationRembourssement;
    @FXML
    private Button buttonrepondre;
    @FXML
    private Button buttonsuppriimer;
    @FXML
    private TextField searchBox;

    private List<Reclamation> listReclamation;
    private ObservableList<Reclamation> observableListReclamation;
    private final Connection con = DataSource.getInstance().getConnection();
    private final ServiceReclamation sr = new ServiceReclamation();
    static int idrec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTableViewReclamation();
        tableViewReclamation.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewReclamation(newValue));
        NewFXMain1 main=new NewFXMain1();
        if(main.u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_VENDEUR\";}")){
            
            buttonrepondre.setVisible(true);
        }
        else{
            buttonrepondre.setVisible(false);
        }
    }

    public void carregarTableViewReclamation() {
        tableColumnContenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
        Gui.NewFXMain1 main=new Gui.NewFXMain1();
        listReclamation = sr.selectReclamation1(main.u.getId());
        observableListReclamation = FXCollections.observableArrayList(listReclamation);
        tableViewReclamation.setItems(observableListReclamation);
    }

    public void selecionarItemTableViewReclamation(Reclamation reclamation) {
        if (reclamation != null) {
            ServiceReclamation sr=new ServiceReclamation();
            labelReclamationEmetteur.setText(String.valueOf(sr.selectu2(reclamation.getEmetteur()).getUsername()));
            
            labelReclamationContenu.setText(reclamation.getContenu());
            idrec=reclamation.getIdReclamation();
            if(reclamation.getRemboursement() == 0)
            {
                labelReclamationRembourssement.setText("");
            }
            if(reclamation.getRemboursement() == 1)
            {
                labelReclamationRembourssement.setText("Rembourssable");
            }
            if(reclamation.getRemboursement() == 2)
            {
                labelReclamationRembourssement.setText("Non Rembourssable");
            }
        } else {
            labelReclamationEmetteur.setText("");
            labelReclamationContenu.setText("");
        }
    }

    @FXML
    private void handlebuttonrep() throws Exception {
        Reclamation r = new Reclamation();
        boolean buttonConfirmerClicked = showGestionReclamationRS(r);       
        if (buttonConfirmerClicked) {
            //sr.insertReclamation(r);
            carregarTableViewReclamation();
         
        }
    }

    @FXML
    private void handlebuttonsupp() throws IOException {
        Reclamation r = tableViewReclamation.getSelectionModel().getSelectedItem();
        if (r != null) {
            sr.supprimerReclamation(r);
            carregarTableViewReclamation();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choisir une reclamation");
            alert.show();
        }
    }

    public boolean showGestionReclamationRS(Reclamation reclamation) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionReclamationRSController.class.getResource("GestionReclamationRS.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion ReclamationS");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        GestionReclamationRSController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setReclamation(reclamation);
        dialogStage.showAndWait();
        return controller.isButtonConfirmerClicked();
    }

    @FXML
    private void searchRecord(KeyEvent ke) {
        FilteredList<Reclamation> filterData = new FilteredList<>(observableListReclamation, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate((recla) -> {
                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (recla.getContenu().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });
            SortedList<Reclamation> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewReclamation.comparatorProperty());
            tableViewReclamation.setItems(sortedList);

        });

    }

}
