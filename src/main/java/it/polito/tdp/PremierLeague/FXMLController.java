/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Adiacenze;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnCreaGrafo"
	private Button btnCreaGrafo; // Value injected by FXMLLoader

	@FXML // fx:id="btnConnessioneMassima"
	private Button btnConnessioneMassima; // Value injected by FXMLLoader

	@FXML // fx:id="btnCollegamento"
	private Button btnCollegamento; // Value injected by FXMLLoader

	@FXML // fx:id="txtMinuti"
	private TextField txtMinuti; // Value injected by FXMLLoader

	@FXML // fx:id="cmbMese"
	private ComboBox<Month> cmbMese; // Value injected by FXMLLoader

	@FXML // fx:id="cmbM1"
	private ComboBox<Match> cmbM1; // Value injected by FXMLLoader

	@FXML // fx:id="cmbM2"
	private ComboBox<Match> cmbM2; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doConnessioneMassima(ActionEvent event) {
		txtResult.clear();
		if(!model.esistenzaGrafo()) {
			txtResult.appendText("Creare prima il grafo!\n");
			return;
		}
		String result = model.connessioneMax();
		if(result == null) {
			txtResult.appendText("Nessun arco presente nel grafo.\n");
			return;
		}
		
		txtResult.appendText(result);
	}

	@FXML
	void doCreaGrafo(ActionEvent event) {
		txtResult.clear();
		
		String s = txtMinuti.getText();
		Integer mese = 0;
		String stampa;
		Integer min = 0;

		try {
			min = Integer.parseInt(s);
		}catch (NumberFormatException nfe) {
			txtResult.appendText("Inserire un valore numerico.\n");
			return;
		}
		
		try {
			mese = cmbMese.getValue().getValue();
		}catch (NumberFormatException nfe) {
			txtResult.appendText("Scegliere un mese.\n");
			return;
		}
		
		stampa = model.creaGrafo(mese, min);
		
		if(stampa == null) {
			txtResult.appendText("Errore nella creazione del grafo!\n");
			return;
		}
		
		txtResult.appendText("Grafo creato\n"+stampa);
		
		this.cmbM1.getItems().addAll(model.getAllVertex());
		this.cmbM2.getItems().addAll(model.getAllVertex());
	}

	@FXML
	void doCollegamento(ActionEvent event) {
		txtResult.clear();
		if(!model.esistenzaGrafo()) {
			txtResult.appendText("Creare prima il grafo!\n");
			return;
		}
		Match m1 = cmbM1.getValue();
		Match m2 = cmbM2.getValue();
		model.camminoMassimo(m1, m2);
		
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnConnessioneMassima != null : "fx:id=\"btnConnessioneMassima\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCollegamento != null : "fx:id=\"btnCollegamento\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMinuti != null : "fx:id=\"txtMinuti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbMese != null : "fx:id=\"cmbMese\" was not injected: check your FXML file 'Scene.fxml'.";        assert cmbM1 != null : "fx:id=\"cmbM1\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbM2 != null : "fx:id=\"cmbM2\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;

		cmbMese.getItems().addAll(Month.values());
	}


}
