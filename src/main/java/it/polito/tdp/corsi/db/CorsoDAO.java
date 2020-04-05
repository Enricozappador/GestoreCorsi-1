package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {
	
	public boolean esisteCorso(String codins) { 
		String sql = "SELECT * FROM corso where codins = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {	
				conn.close();
				return true; 
				
			} else {
				conn.close();
				return false; 
			}
			
		
			
		}catch(SQLException e) {
			throw new RuntimeException(e); 
		}
		
	
		
	}
	
	// select c.codins, c.nome, c.crediti, c.pd, COUNT(*) as tot from corso as c, iscrizione as i where c.pd =  and c.codins = i.codins
	//gtoup by c.codins, c.nome, c.crediti, c.pd
	
	public List<Corso> getCorsibyPeriodo(Integer pd){
		String sql = "SELECT * FROM corso WHERE pd = ?"; 
		List<Corso> result = new ArrayList<Corso>(); 
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd") ); 
				result.add(c); 
			}
			
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e); 
		}
		
		return result; 
		
	}
	
	public Map<Corso, Integer> getIscrittibyPeriodo(Integer pd){
		
		String sql = "select c.codins, c.nome, c.crediti, c.pd, "
				+ "COUNT(*) as tot from corso as c, iscrizione as i where c.pd "
				+ "= ? and c.codins = i.codins group by c.codins, c.nome, c.crediti, c.pd"; 
		
		Map<Corso, Integer> result = new HashMap<Corso, Integer>(); 
				
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
			 Corso c = new Corso (rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")); 
			 Integer n = rs.getInt("tot"); 
			 result.put(c, n); 
			}
			
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e); 
		}
		return result;
		
	}
	
	
	public List<Studente> getStudentibyCorso(Corso corso){
	
	
	String sql = "select s.matricola, s.nome, s.cognome, s.CDS from studente as s, "
			+ "iscrizione as i where s.matricola = i.matricola and i.codins = ?"; 
	List<Studente> studenti = new LinkedList<Studente>(); 
	
	try { 
		Connection conn = ConnectDB.getConnection(); 
		PreparedStatement st= conn.prepareStatement(sql); 
		st.setString(1, corso.getCodins());
		ResultSet rs = st.executeQuery(); 
		
		while(rs.next()) {
			Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS")); 
			studenti.add(s); 
		}
		
		conn.close();
		
	}catch(SQLException e) {
		throw new RuntimeException(e); 
	}
	return studenti;
	
	
	
	}
	
	
	public Map<String, Integer> getDivisioneCDS(Corso c){
		
		String sql=" select c.CDS, COUNT(*) as tot from studente as s, iscrizione as i "
				+ "where s.matricola = i.matricola and s.cds<> \"\" and i.codins = ? group by s.CDS ";
				
		Map<String, Integer> statistiche = new HashMap<String, Integer>();
		try { 
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement st= conn.prepareStatement(sql); 
			st.setString(1, c.getCodins());
			ResultSet rs = st.executeQuery(); 
			
			while(rs.next()) {
				statistiche.put(rs.getString("CDS"), rs.getInt("tot") );
			}
			
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e); 
		}
		return statistiche;
	}

}
