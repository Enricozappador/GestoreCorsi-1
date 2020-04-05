package it.polito.tdp.corsi.model;

import java.util.HashMap;
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
	
	public Map<String, Integer>  getDivisioneCDS(Corso c){
		/*List<Studente> studenti = dao.getStudentibyCorso(c); 
		
		Map<String, Integer> statistiche = new HashMap<String, Integer>();
		
		for(Studente s : studenti) {
			
			if(s.getCds() != null && !s.getCds().equals("")) {
			
			if(statistiche.containsKey(s.getCds())) {
				statistiche.put(s.getCds(), statistiche.get(s.getCds())+1); 
			}
			else {
				statistiche.put(s.getCds(), 1); 
			}
		}
		}
		
		return statistiche; */
		return dao.getDivisioneCDS(c);
	}
	

}
