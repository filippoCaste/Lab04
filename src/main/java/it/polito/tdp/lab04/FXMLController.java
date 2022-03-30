/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

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
    void doCercaIScrittiByCorso(ActionEvent event) throws UnexpectedException {
    	txtResult.clear();
    	String nomeCorso = this.cmbCorso.getValue();
    	if(nomeCorso.equals("")||nomeCorso.equals(" ")) {
    		txtResult.setText("Nessuno studente rilevato; Corso non esistente");
    		return;
    	}
    	
    	Corso corso = this.model.getCorsoByNome(nomeCorso);
    	if(corso == null) {
    		throw new UnexpectedException("Non è stato rilevato alcun corso");
    	}
    	
    	Set<Studente> studentiIscritti = this.model.getStudentiByCorso(corso);

    	for(Studente s : studentiIscritti)
    		txtResult.appendText(s.toString() + "\n");
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtResult.clear();
    	String input = txtFieldMatricola.getText();
    	
    	if(input.isBlank()) {
    		txtResult.setText("Carissimo utente, devi inserire qualcosa.");
    		return;
    	}
    	
    	Integer matricola = null;
    	Studente studente = null;
    	Set<Corso> corsi = null;
    	String nomeCorso = this.cmbCorso.getValue();
    	if(nomeCorso.isBlank()) {
    		txtResult.setText("Carissimo utente, devi inserire qualcosa.");
    		return;
    	}
    	Corso corsoCercato = this.model.getCorsoByNome(nomeCorso);
    	if(corsoCercato == null) {
    		txtResult.setText("Nessun corso trovato.");
    		return;
    	}
    	try {
    		matricola = Integer.parseInt(input);
    		studente = this.model.getStudenteByMatricola(matricola);
    		corsi = this.model.getCorsiByStudente(studente);

//  		if(corsi.contains(corsoCercato)) {
//    			txtResult.setText("Lo studente è già regolarmente iscritto al corso.");
//    			return;
//    		} else {
   			boolean res = this.model.inserisciStudenteACorso(studente, corsoCercato);
    			if(res)
    				txtResult.setText("Lo studente è stato iscritto al corso selezionato.");
    			else
    				txtResult.setText("Lo studente risulta già iscritto a questo corso.");
 //   		}
    	} catch(NumberFormatException nfe) {
    		nfe.printStackTrace();
    		txtResult.setText("La matricola deve contenere solo cifre numeriche.");
    		return;
    	} catch(NullPointerException npe) {
    		npe.printStackTrace();
    		txtResult.setText("Lo studente non esiste.");
    		return;
    	}		
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
    	txtResult.clear();
    	String input = txtFieldMatricola.getText();
    	
    	if(input.isBlank()) {
    		txtResult.setText("Carissimo utente, devi inserire qualcosa.");
    		return;
    	}
    	
    	Integer matricola = null;
    	Studente studente = null;
    	Set<Corso> corsi = null;
    	if(this.cmbCorso.getValue()==null) {
    		// qui si entra se non ci sono corsi selezionati nella ComboBox
    		try {
    			matricola = Integer.parseInt(input);
    			studente = this.model.getStudenteByMatricola(matricola);
    			corsi = this.model.getCorsiByStudente(studente);
    			for(Corso c : corsi) {
    				txtResult.appendText(c.toString() + "\n");
    			}
    		} catch(NumberFormatException nfe) {
    			nfe.printStackTrace();
    			txtResult.setText("La matricola deve contenere solo cifre numeriche.");
    			return;
    		} catch(NullPointerException npe) {
    			npe.printStackTrace();
    			txtResult.setText("Lo studente non esiste.");
    			return;
    		}
    	}
    	else {
    		// qui si entra se viene selezionato un valore nella ComboBox
    		String nomeCorso = this.cmbCorso.getValue();
    		if(nomeCorso.isBlank()) {
    			txtResult.setText("Carissimo utente, devi inserire qualcosa.");
    			return;
    		}
    		Corso corsoCercato = this.model.getCorsoByNome(nomeCorso);
    		if(corsoCercato == null) {
    			txtResult.setText("Nessun corso trovato.");
    			return;
    		}
    		try {
    			matricola = Integer.parseInt(input);
    			studente = this.model.getStudenteByMatricola(matricola);
    			corsi = this.model.getCorsiByStudente(studente);
    			
    			if(corsi.contains(corsoCercato)) {
        			txtResult.setText("Lo studente è regolarmente iscritto al corso.");
        			return;
        		} else {
        			txtResult.setText("Lo studente non è iscritto al corso selezionato.");
        		}
    		} catch(NumberFormatException nfe) {
    			nfe.printStackTrace();
    			txtResult.setText("La matricola deve contenere solo cifre numeriche.");
    			return;
    		} catch(NullPointerException npe) {
    			npe.printStackTrace();
    			txtResult.setText("Lo studente non esiste");
    			return;
    		}		
    	}
    }

    @FXML
    void doValidate(ActionEvent event) throws UnexpectedException {
    	this.txtFieldCognome.clear();
    	this.txtFieldNome.clear();
    	String i = this.txtFieldMatricola.getText();
    	Integer matricola;
    	try {
    		
    		matricola = Integer.parseInt(i);
    		
    	} catch(NumberFormatException nfe) {
    		nfe.printStackTrace();
    		this.btnValidate.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Paint.valueOf("red"), CornerRadii.EMPTY, Insets.EMPTY)));
    		this.txtFieldMatricola.setText("Errore nella matricola. Inserire solo valori numerici");
    		return;
    	}
    	Studente s = this.model.getStudenteByMatricola(matricola);
    	if(s!=null) {
    		this.btnValidate.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Paint.valueOf("green"), CornerRadii.EMPTY, Insets.EMPTY)));
    		this.txtFieldCognome.setText(s.getCognome());
    		this.txtFieldNome.setText(s.getNome());
    	} else {
    		throw new UnexpectedException("Errore imprevisto");
    	}

    }
    
    public void setModel(Model m) {
    	this.model = m;
    	List<Corso> corsiOrdinati = new ArrayList<> (this.model.getTuttiICorsi());
    	Collections.sort(corsiOrdinati);
    	for(Corso c : corsiOrdinati) {
    		this.cmbCorso.getItems().add(c.getNome());
    	}
    	this.cmbCorso.getItems().add(" ");
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
