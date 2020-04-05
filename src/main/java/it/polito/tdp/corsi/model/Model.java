package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	
	private CorsoDAO dao; 
	
	public Model() {
		dao = new CorsoDAO(); 
	}
	
	public List<Corso> getCorsibyPeriodo(Integer pd){
	
		return dao.getCorsibyPeriodo(pd); 
	}
	
	public Map<Corso, Integer> getIscrittibyPeriodo(Integer pd){
		
		return dao.getIscrittibyPeriodo(pd); 
	}
	
	public List<Studente> getStudentibyCorso(Corso c){
		return dao.getStudentibyCorso(c); 
	}
	
	public boolean esisteCorso(String codins) {
		
		return dao.esisteCorso(codins); 
		
	}
	

}
