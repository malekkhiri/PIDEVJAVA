/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Annonces;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.ConnextionSingleton;

/**
 * FXML Controller class
 *
 * @author oussama
 */
public class ValiderAnnonceController implements Initializable {
private ObservableList<Annonces> data;
    @FXML
    private ImageView imageViewP;
    @FXML
    private HBox ev;
    @FXML
    private TableView<Annonces> TableViewP;
    @FXML
    private TableColumn<?, ?> titrev;
    @FXML
    private TableColumn<?, ?> descriptionv;
    @FXML
    private TableColumn<?, ?> prixv;
    @FXML
    private Button validerbtn;
    @FXML
    private Button supprimer;
    @FXML
    private Label titrelb;
    @FXML
    private Label prixlb;
    @FXML
    private Label descriptionlb;
    @FXML
    private Label tellb;
    Connection con = ConnextionSingleton.getInstance().getConnection();
              ResultSet rs = null;
 private ObservableList<Annonces> masterData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> idannoncev;
    @FXML
    private JFXTextField filterField;
    @FXML
    private Label idannoncelab;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          setsCellTable();
        data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        setCellValue();
        // TODO
    }    

    @FXML
    private void ClickProduit(MouseEvent event) {
    }

    @FXML
    private void Validannonce(ActionEvent event) {
          try {
                Statement stmt = con.createStatement();
                     String sql = ("UPDATE `annonces` SET `approuver` = '1' WHERE `annonces`.`idVenteLibre` ="+idannoncelab.getText());
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
                    System.out.println("jawek behi");
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
          for ( int i = 0; i<TableViewP.getItems().size(); i++) {
    TableViewP.getItems().clear();
    loadDataFromDatabase();
    }
    }
    @FXML
    private void Supprimer(ActionEvent event) {
        try {
            int id_prod = Integer.valueOf(idannoncelab.getText());
            String sql = ("DELETE FROM `annonces` where idVenteLibre = (?)");
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id_prod);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
          for ( int i = 0; i<TableViewP.getItems().size(); i++) {
    TableViewP.getItems().clear();
    loadDataFromDatabase();
          }
    }
      public void setsCellTable() {
        idannoncev.setCellValueFactory(new PropertyValueFactory<>("idVenteLibre"));
        titrev.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionv.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixv.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    private void loadDataFromDatabase() {
     afficheAnnonce();

    }

     private void setCellValue() {
        TableViewP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Annonces pl = TableViewP.getItems().get(TableViewP.getSelectionModel().getSelectedIndex());
                prixlb.setText(Double.toString(pl.getPrix()));
                titrelb.setText(pl.getTitre());
                descriptionlb.setText((pl.getDescription()));
                tellb.setText(Integer.toString(pl.getTelephoneVendeur()));
                idannoncelab.setText(Integer.toString(pl.getIdVenteLibre()));
            }
        });
    }
      public void afficheAnnonce() {
           try {
                Statement stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM annonces where approuver = 0");
            while (rs.next()) {
                int idVenteLibrea = rs.getInt("idVenteLibre");
                String titrea = rs.getString("titre");
                 String villea = rs.getString("ville");
                 String regiona	 = rs.getString("region");
                 String descriptiona = rs.getString("description");
                 String image_namea = rs.getString("image_name");
                 Double prixa = rs.getDouble("prix");
              String datepublicationa = rs.getString("datepublication");
              Boolean approuvera = rs.getBoolean("approuver");
                int user_ida = rs.getInt("user_id");
                int TelephoneVendeura = rs.getInt("TelephoneVendeur");
                masterData.add(new Annonces( idVenteLibrea,  TelephoneVendeura,  user_ida,  titrea,  villea,  regiona,  descriptiona,  image_namea,  datepublicationa,  prixa,  approuvera));
               
                data.add(new Annonces( idVenteLibrea,  TelephoneVendeura,  user_ida,  titrea,  villea,  regiona,  descriptiona,  image_namea,  datepublicationa,  prixa,  approuvera));
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        TableViewP.setItems(data);
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
        sortedData.comparatorProperty().bind(TableViewP.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        TableViewP.setItems(sortedData);
             // TODO
    }
   
}
