package it.polito.tdp.corsi.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model(); 
		
		System.out.println(model.getCorsibyPeriodo(1));
		
		
		System.out.println(model.getIscrittibyPeriodo(1));
	}

}
