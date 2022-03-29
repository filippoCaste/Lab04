package it.polito.tdp.lab04.model;

public class Studente {

	private Integer matricola;
	private String nome;
	private String cognome;
	private String cDS;

	public Studente(Integer matricola, String nome, String cognome, String cDS) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cDS = cDS;
	}

	public Integer getMatricola() {
		return matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getcDS() {
		return cDS;
	}

	public void setcDS(String cDS) {
		this.cDS = cDS;
	}
	
	

}
