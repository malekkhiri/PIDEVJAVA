/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Annonces;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ConnextionSingleton;

/**
 * FXML Controller class
 *
 * @author BlacK SouL
 */
public class DashboardController implements Initializable {


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
private ObservableList<Annonces> data;
    Connection con = ConnextionSingleton.getInstance().getConnection();
              ResultSet rs = null;
               private int iduser ;
    @FXML
    private AnchorPane tabannonce;
    @FXML
    private Button refresh;
    @FXML
    private JFXButton voirBtn;
    @FXML
    private Label idannoncelab;
    private int idVenteLibre;
private String titres ;
private String villes ;
private String regions	 ;
private String descriptions ;
private String image_name ;
private Double prixs ;
private String datepublication ;
private Boolean approuver ;
private int user_id ;
private int TelephoneVendeur ;
 private ObservableList<Annonces> masterData = FXCollections.observableArrayList();
    @FXML
    private JFXTextField filterField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         setsCellTable();
        data = FXCollections.observableArrayList();
        loadDataFromDatabase();// recuperer les donnes apartir mel base
        setCellValue(); // pour remplir les champs d'un tableau
    
        
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

     private void setCellValue() {
        tableannonce.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Annonces pl = tableannonce.getItems().get(tableannonce.getSelectionModel().getSelectedIndex());
                idannoncelab.setText(Integer.toString(pl.getIdVenteLibre()));
               idVenteLibre =pl.getIdVenteLibre()  ;
titres  = pl.getTitre() ;
villes  =  pl.getVille();
regions    =  pl.getRegion();
descriptions  =  pl.getDescription();
image_name  =  pl.getImage_name();
prixs  =  pl.getPrix();
datepublication  =  pl.getDatepublication();
 approuver  =  pl.getApprouver();
user_id  =  pl.getUser_id();
TelephoneVendeur  =  pl.getTelephoneVendeur();
                
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
        public void afficheAnnonce() {
           try {
                Statement stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM annonces where approuver = 1");
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
               
                data.add(new Annonces( idVenteLibrea,  TelephoneVendeura,  user_ida,  titrea,  villea,  regiona,  descriptiona,  image_namea,  datepublicationa,  prixa,  approuvera));
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableannonce.setItems(data);
    }

    @FXML
    private void reloadPage(ActionEvent event) {
        for ( int i = 0; i<tableannonce.getItems().size(); i++) {
    tableannonce.getItems().clear();
    loadDataFromDatabase();
}
    }

    @FXML
    private void showAnnonce(ActionEvent event) throws IOException, SQLException {
                 
                           FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Gui/ShowAnnonce.fxml"));
                    loader.load();
                    ShowAnnonceController display = loader.getController();
                    System.out.println(prixs);
                    
                    display.setTexte( idVenteLibre,titres ,villes ,regions ,descriptions ,image_name ,
                                        prixs ,datepublication , approuver , user_id , TelephoneVendeur );
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();    
    }
}
