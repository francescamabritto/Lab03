package it.polito.tdp.spellchecker.model;



public class RichWord {
	String parolaInput;
	boolean corretta;
	
	public RichWord(String parolaInput) {
		this.parolaInput = parolaInput;
		this.corretta = false;
	}
	
	public String getParolaInput() {
		return parolaInput;
	}
	public void setParolaInput(String parolaInput) {
		this.parolaInput = parolaInput;
	}
	public boolean isCorretta() {
		return corretta;
	}
	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}
	
	
}