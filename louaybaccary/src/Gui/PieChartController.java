/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import service.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PieChartController implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private Label status;

    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stat();
    }    
    public void Stat() {
        ServiceEvenement p=new ServiceEvenement();
       
        pie.getData().clear();
        pie.setData(p.StatNbrParticipant());
        pie.setAnimated(true);
        pie.setVisible(true);
        
        for(final PieChart.Data data : pie.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                }
            });
        }

    }
    
}
