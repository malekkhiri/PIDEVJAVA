/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Annonces;
import Entity.User;
import Gui.NewFXMain1;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ConnextionSingleton;

/**
 * FXML Controller class
 *
 * @author BlacK SouL
 */
public class MesannoncesController implements Initializable {

    @FXML
    private AnchorPane tabannonce;
    @FXML
    private TableView<Annonces> tableannonce;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> ville;
    @FXML
    private TableColumn<?, ?> region;
    @FXML
    private TableColumn<?, ?> annonceid;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnfavoris;
    @FXML
    private Button btnmesannonce;
    @FXML
    private Button btnannonce;
    @FXML
    private Button refresh;
private ObservableList<Annonces> data;
private ObservableList<Annonces> masterData = FXCollections.observableArrayList();
    Connection con = ConnextionSingleton.getInstance().getConnection();
              ResultSet rs = null;
               private int iduser  ;
                private User U;
    @FXML
    private JFXTextField filterField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NewFXMain1 main=new NewFXMain1();
  
     U=main.getU();
     iduser = U.getId();
        // TODO
           setsCellTable();
        data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        setCellValue();
                rechercheave();

    }    
  public void setsCellTable() {
        annonceid.setCellValueFactory(new PropertyValueFactory<>("idVenteLibre"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        region.setCellValueFactory(new PropertyValueFactory<>("region"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    private void loadDataFromDatabase() {
     afficheAnnonce();

    }
    public void rechercheave(){
            FilteredList<Annonces> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(annonce -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (annonce.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } 
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Annonces> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableannonce.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableannonce.setItems(sortedData);
             // TODO
    }
 public void afficheAnnonce() {
           try {
                Statement stmt = con.createStatement();
                System.out.println(iduser);
            rs = stmt.executeQuery("SELECT * FROM annonces where user_id ="+iduser);
            while (rs.next()) {
                int idVenteLibre = rs.getInt("idVenteLibre");
                String titre = rs.getString("titre");
                 String ville = rs.getString("ville");
                 String region	 = rs.getString("region");
                 String description = rs.getString("description");
                 String image_name = rs.getString("image_name");
                 Double prix = rs.getDouble("prix");
              String datepublication = rs.getString("datepublication");
              Boolean approuver = rs.getBoolean("approuver");
                int user_id = rs.getInt("user_id");
                int TelephoneVendeur = rs.getInt("TelephoneVendeur");
                
               masterData.add(new Annonces( idVenteLibre,  TelephoneVendeur,  user_id,  titre,  ville,  region,  description,  image_name,  datepublication,  prix,  approuver));
               
                data.add(new Annonces( idVenteLibre,  TelephoneVendeur,  user_id,  titre,  ville,  region,  description,  image_name,  datepublication,  prix,  approuver));
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableannonce.setItems(data);
    }
     private void setCellValue() {
        tableannonce.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Annonces pl = tableannonce.getItems().get(tableannonce.getSelectionModel().getSelectedIndex());
                //titre.setText(pl.getTitre());
                //description.setText((pl.getDescription()));
                //prix.setText(Double.toString(pl.getPrix()));
                //ville.setText((pl.getVille()));
                //region.setText((pl.getRegion()));
                
          
            }
        });
    }
    @FXML
    private void addAnnonce(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/AddAnnonce.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void showWishList(ActionEvent event) throws IOException {
          ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/WishList.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void showAnnonces(ActionEvent event) throws IOException {
          ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void showMyAnnoces(ActionEvent event) throws IOException {
          ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Mesannonces.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void reloadPage(ActionEvent event) {
          for ( int i = 0; i<tableannonce.getItems().size(); i++) {
    tableannonce.getItems().clear();
}
        loadDataFromDatabase();
    }
    
}
