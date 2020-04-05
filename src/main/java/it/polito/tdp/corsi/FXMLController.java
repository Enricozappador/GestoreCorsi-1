package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model; 

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Periodotxt;

    @FXML
    private TextField Corsotxt;

    @FXML
    private Button CorsiperPeriodobtn;

    @FXML
    private Button Studentibtn;

    @FXML
    private Button NumeroStudentibtn;

    @FXML
    private Button DisivioneStudentibtn;

    @FXML
    private TextArea Resulttxt;

    @FXML
    void handleCorsiperPeriodo(ActionEvent event) {
    	
    	Resulttxt.clear();
    	
    	String pdString = Periodotxt.getText(); 
    	Integer pd; 
    	
    	try {
    	pd = Integer.parseInt(pdString); 
    	} catch (NumberFormatException e) {
    		Resulttxt.appendText("Devi inserire un numero!");
    		return; 
    	}
    	
    	
    	if(!pd.equals(1) && !pd.equals(2)) {
    		Resulttxt.appendText("Devi inserire un numero compreso tra 0  e 2");
    		return; 
    	}
    	
    	
    	List<Corso> corsi = this.model.getCorsibyPeriodo(pd); 
    	
    	for(Corso c : corsi) {
    		Resulttxt.appendText(c.toString()+ "\n");
    	}
    	
    	
    }

    @FXML
    void handleStampaDivisione(ActionEvent event) {

    }

    @FXML
    void handleStampaNumeroStudenti(ActionEvent event) {
Resulttxt.clear();
    	
    	String pdString = Periodotxt.getText(); 
    	Integer pd; 
    	
    	try {
    	pd = Integer.parseInt(pdString); 
    	} catch (NumberFormatException e) {
    		Resulttxt.appendText("Devi inserire un numero!");
    		return; 
    	}
    	
    	
    	if(!pd.equals(1) && !pd.equals(2)) {
    		Resulttxt.appendText("Devi inserire un numero compreso tra 0  e 2");
    		return; 
    	}
    	
    	Map<Corso, Integer> statistiche = this.model.getIscrittibyPeriodo(pd); 
    	
    	for(Corso c : statistiche.keySet()) {
    		Resulttxt.appendText(c.getNome() + " "+ statistiche.get(c)+ "\n");
    		
    	}

    }

    @FXML
    void handleStampaStudenti(ActionEvent event) {
    	
    	Resulttxt.clear();
    	
    	String codins = Corsotxt.getText(); 
    	
    	if(!this.model.esisteCorso(codins)) {
    		Resulttxt.appendText("il corso non esite!");
    		return; 
    	}
    	
    	List<Studente> studenti= this.model.getStudentibyCorso(new Corso(codins, null, null, null)); 
    	
    	if(studenti.size()==0) {
    		Resulttxt.appendText("Il corso non ha studenti iscritti");
    		return; 
    	
    	}
    	
    	for(Studente s : studenti) {
    		Resulttxt.appendText(s.toString()+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert Periodotxt != null : "fx:id=\"Periodotxt\" was not injected: check your FXML file 'Gestore corsi.fxml'.";
        assert Corsotxt != null : "fx:id=\"Corsotxt\" was not injected: check your FXML file 'Gestore corsi.fxml'.";
        assert CorsiperPeriodobtn != null : "fx:id=\"CorsiperPeriodobtn\" was not injected: check your FXML file 'Gestore corsi.fxml'.";
        assert Studentibtn != null : "fx:id=\"Studentibtn\" was not injected: check your FXML file 'Gestore corsi.fxml'.";
        assert NumeroStudentibtn != null : "fx:id=\"NumeroStudentibtn\" was not injected: check your FXML file 'Gestore corsi.fxml'.";
        assert DisivioneStudentibtn != null : "fx:id=\"DisivioneStudentibtn\" was not injected: check your FXML file 'Gestore corsi.fxml'.";
        assert Resulttxt != null : "fx:id=\"Resulttxt\" was not injected: check your FXML file 'Gestore corsi.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model= model; 
    }
}
