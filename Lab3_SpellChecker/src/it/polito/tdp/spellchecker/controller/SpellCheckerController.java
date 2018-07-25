	/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.ResourceBundle;


import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SpellCheckerController {
	
	Dictionary model = new Dictionary();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnLanguage"
    private ComboBox<String> cmbLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="txtText"
    private TextArea txtText; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtWrongWords"
    private TextArea txtWrongWords; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrors"
    private Text txtErrors; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearAll"
    private Button btnClearAll; // Value injected by FXMLLoader

    @FXML // fx:id="txtSeconds"
    private Text txtSeconds; // Value injected by FXMLLoader

 
    
    @FXML
    void doChooseLanguage(ActionEvent event) {
    		this.model.setLinguaScelta(this.cmbLanguage.getValue());
    }

    @FXML
    void doClearAll(ActionEvent event) {
    		txtWrongWords.clear();
    		txtText.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {

	    	try {
				// creo la lista di stringhe
				String s= txtText.getText();
				   s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
				   s.replaceAll("\\?", "");
     			   s.replaceAll("\n", " ");
				s.toLowerCase();
				String[] arrayInput = s.split(" "); // divido il testo in stringhe
   
				// creo una lista contenente le singole parole
				List<RichWord> listaInput = new ArrayList<RichWord>();
					for(int i=0; i<arrayInput.length; i++) {
						listaInput.add(new RichWord(arrayInput[i])) ;
					}
					
				// metodo che carica il dizionario 
				if(this.cmbLanguage.getValue()!=null)
					this.model.loadDictionary(this.cmbLanguage.getValue());   
				else {
					throw new InvalidParameterException("Seleziona un valore per il dizionario!");
				}
				
				
				// metodo che valuta se le singole stringhe sono corrette    	
				   this.model.spellCheckText(listaInput);
				   
				   
				   List<RichWord>wwList = new ArrayList<>();
				   
				   for(RichWord w: listaInput) {
									if(w.isCorretta()==false) {
										wwList.add(w);
									    txtWrongWords.appendText(w.getParolaInput()+"\n");
									}
				   }
			} catch (InvalidParameterException e) {
				e.printStackTrace();
				this.txtWrongWords.appendText("Nessuna lingua selezionata!\n");
			}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtText != null : "fx:id=\"txtText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearAll != null : "fx:id=\"btnClearAll\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtSeconds != null : "fx:id=\"txtSeconds\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        
    }

	public Dictionary getModel() {
		return model;
	}

	public void setModel(Dictionary model) {
		this.model = model;
		List<String> lingue = model.getLingueDisponibili();
		this.cmbLanguage.getItems().setAll(lingue);
		this.cmbLanguage.setPromptText("...");
	}
    
    
}
