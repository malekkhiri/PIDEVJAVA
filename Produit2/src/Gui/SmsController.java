/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.GestionMagasin;

/**
 * FXML Controller class
 *
 * @author seif
 */
public class SmsController implements Initializable {

    @FXML
    private JFXTextField ak;
    @FXML
    private JFXTextField se;
    @FXML
    private JFXTextField num;
    @FXML
    private TextArea msg;
    @FXML
    private JFXButton bte;
    @FXML
    private JFXComboBox <String> USNAME;

    private List<String> l = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ak.setText("ZTH9wSkiaew-FSWgDnq1cnTeGe16oeAsVQR9Q4YrSZ");

        l.addAll(GestionMagasin.selectM());
        USNAME.getItems().addAll(l);

        // TODO
    }
    
    
    
    @FXML
    private void selectNum(ActionEvent event) throws SQLException{
        
        String n=USNAME.getValue();
        
        int b=GestionMagasin.numeroaffich(n);
        String c=String.valueOf(b);
         c="216"+c;
         num.setText(c);
         
    }

    public void envoyer(ActionEvent event) {

        String apik = "apikey=" + ak.getText();
        String mess = "&message=" + msg.getText();
        String send = "&sender=" + se.getText();
        String numb = "&numbers=" + num.getText();

        try {
            // Construct data
            String apiKey = "apikey=" + ak.getText();
            String message = "&message=" + msg.getText();
            String sender = "&sender=" + se.getText();
            String numbers = "&numbers=" + num.getText();
            System.out.println(apiKey);
            System.out.println(message);
            System.out.println(sender);
            System.out.println(numbers);

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                /*stringBuffer.append(line);*/
                JOptionPane.showMessageDialog(null, "okki");
            }
            rd.close();

            /*return stringBuffer.toString();*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Error SMS " + e);

            System.out.println(apik);
            System.out.println(mess);
            System.out.println(send);
            System.out.println(numb);
            /*return "Error "+e;*/
        }
    }
        @FXML
    private void ClickRetour(ActionEvent event) throws IOException {

 try {
              Parent home_page_parent = FXMLLoader.load(getClass().getResource("ValiderMagasin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
            
                //app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
        
            
        } catch (IOException ex) {
           
        
    }
    }

}
