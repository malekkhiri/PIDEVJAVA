/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DataBase.DataSource;;
import Entity.Commande;
import Entity.Produit;
import Entity.Reclamation;
import Entity.User;
import service.ServiceCommande;
import service.ServiceProduit;
import service.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.SendMail;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class CRUDReclamationController implements Initializable {

    @FXML
    private TableView<Commande> tableViewCommande;
    @FXML
    private TableColumn<?, ?> tableColumnNomProduit;
    @FXML
    private TableColumn<?, ?> tableColumnQuantite;
    @FXML
    private Label labelCommandeNomVendeur;
    @FXML
    private Label labelCommandeNomProduit;
    @FXML
    private Label labelCommandeQuantite;
    @FXML
    private Button buttonAjouter;

    private List<Commande> listCommande;
    private ObservableList<Commande> observableListCommande;
    private List<Produit> listProduit;
    private ObservableList<Produit> observableListProduit;

    private final Connection con = DataSource.getInstance().getConnection();
    private final ServiceCommande sc = new ServiceCommande();
    private final ServiceReclamation sr = new ServiceReclamation();
    private final ServiceProduit sp = new ServiceProduit();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTableViewCommande();
        tableViewCommande.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewReclamation(newValue));
    }

    public void carregarTableViewCommande() {
        tableColumnNomProduit.setCellValueFactory(new PropertyValueFactory<>("id_Produit"));
        tableColumnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        listCommande = sc.afficher();

        observableListCommande = FXCollections.observableArrayList(listCommande);
        tableViewCommande.setItems(observableListCommande);
    }

    public void selecionarItemTableViewReclamation(Commande commande) {

        try {
            if (commande != null) {
                ServiceCommande sc = new ServiceCommande();
                ServiceReclamation sR = new ServiceReclamation();
                Test2 t = new Test2();
                labelCommandeNomVendeur.setText(String.valueOf(commande.getId_utilisateur()));
                labelCommandeNomProduit.setText(String.valueOf(commande.getId_produit().getId_Produit()));
                labelCommandeQuantite.setText(String.valueOf(commande.getQuantite()));
            } else {
                labelCommandeNomVendeur.setText("");
                labelCommandeNomProduit.setText("");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void handleButtonAjouter() throws IOException {
        Reclamation r = new Reclamation();
        Commande c = tableViewCommande.getSelectionModel().getSelectedItem();
        User us;
        Test2 test = new Test2();
        test.commande = c;
        System.out.println(c.getId_produit());
        ServiceReclamation sr = new ServiceReclamation();
        us = sr.selectu(c.getId_produit().getId_Produit());
        test.nom = us.getUsername();
        System.out.println(us.getUsername());
        System.out.println(us.getEmail());
        System.out.println(test.nom);
        boolean buttonConfirmerClicked = showGestionCRUDReclamation(r);
        if (buttonConfirmerClicked) {
            sr.insertReclamation(r);
            SendMail sm = new SendMail(us.getEmail(), "Bonjour", "Vous avez reçu une réclamation veuillez consulter vos réclamations");
        }
    }

    public boolean showGestionCRUDReclamation(Reclamation reclamation) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GestionCRUDReclamationController.class.getResource("GestionCRUDReclamation.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion ReclamationS");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        GestionCRUDReclamationController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setReclamation(reclamation);
        dialogStage.showAndWait();
        return controller.isButtonConfirmerClicked();
    }

//    @FXML
//    private void searchRecord(KeyEvent ke) {
//        FilteredList<Reclamation> filterData = new FilteredList<>(observableListReclamation, p -> true);
//        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
//            filterData.setPredicate((recla) -> {
//                if (newvalue == null || newvalue.isEmpty()) {
//                    return true;
//                }
//                String typedText = newvalue.toLowerCase();
//                if (recla.getContenu().toLowerCase().indexOf(typedText) != -1) {
//
//                    return true;
//                }
//                return false;
//            });
//            SortedList<Reclamation> sortedList = new SortedList<>(filterData);
//            sortedList.comparatorProperty().bind(tableViewReclamation.comparatorProperty());
//            tableViewReclamation.setItems(sortedList);
//
//        });
//
//    }
}
