/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCercaIScrittiByCorso"
    private Button btnCercaIScrittiByCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="btnRicercaCorsi"
    private Button btnRicercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnValidate"
    private Button btnValidate; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCorso"
    private ComboBox<String> cmbCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldCognome"
    private TextField txtFieldCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldMatricola"
    private TextField txtFieldMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldNome"
    private TextField txtFieldNome; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader


    @FXML
    void doCercaIScrittiByCorso(ActionEvent event) {

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	this.txtFieldCognome.clear();
    	this.txtFieldNome.clear();
    	this.txtFieldMatricola.clear();
    	this.txtResult.clear();
    	
    }

    @FXML
    void doRicercaCorsi(ActionEvent event) {

    }

    @FXML
    void doValidate(ActionEvent event) {

    }
    
    public void setModel(Model m) {
    	this.model = m;
    	for(Corso c : this.model.getTuttiICorsi()) {
    		this.cmbCorso.getItems().add(c.getNome());
    	}
    	this.cmbCorso.getItems().add("   ");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCercaIScrittiByCorso != null : "fx:id=\"btnCercaIScrittiByCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicercaCorsi != null : "fx:id=\"btnRicercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnValidate != null : "fx:id=\"btnValidate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorso != null : "fx:id=\"cmbCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFieldCognome != null : "fx:id=\"txtFieldCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFieldMatricola != null : "fx:id=\"txtFieldMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFieldNome != null : "fx:id=\"txtFieldNome\" was not injected: check your FXML file 'Scene.fxml'.";
        
        this.txtFieldCognome.setDisable(true);
        this.txtFieldNome.setDisable(true);    
    }

}
