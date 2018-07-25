package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;


public class Dictionary {
	
	String linguaScelta;
	List<String> lingueDisponibili;
	List <String>listaWords;
	
	public Dictionary() {
		this.listaWords = new ArrayList<String>();
		this.lingueDisponibili = new ArrayList<String>();
		lingueDisponibili.add("Italian");
		lingueDisponibili.add("English");
	}
	
	public String getLinguaScelta() {
		return linguaScelta;
	}


	public void setLinguaScelta(String linguaScelta) {
		this.linguaScelta = linguaScelta;
	}


	public void loadDictionary(String language) {
	//TODO
		try {
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr); 
			String word;
			while ((word = br.readLine()) != null) {
			// Aggiungere parola alla struttura dati
				this.listaWords.add(word);
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file"); }
	}
	
	
	public List<RichWord> spellCheckText(List<RichWord> inputTextList){
		//TODO
		for(RichWord rw: inputTextList) {
			if(this.listaWords.contains(rw.getParolaInput())) {	
				// LA PAROLA E' CORRETTA
				rw.setCorretta(true);
			}
			else
				rw.setCorretta(false);
		}
		return inputTextList;
	}

	public List<String> getLingueDisponibili() {
		return this.lingueDisponibili;
	}
	
	

}
