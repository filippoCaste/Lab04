package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	private Integer matricola;
	private String cognome;
	private String nome;
	private String CDS;
	
	public Integer getMatricola() {
		return matricola;
	}



	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCDS() {
		return CDS;
	}



	public void setCDS(String cDS) {
		CDS = cDS;
	}



	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String cognome = rs.getString("cognome");
				Integer matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				studenti.add(new Studente(matricola, nome, cognome, CDS));
			}
			rs.close();
			st.close();
			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}



	public Studente getStudenteByMatricola(Integer matricola) {
		final String sql = "SELECT * FROM studente s WHERE s.matricola = ? ";

		Studente sCercato = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				
				sCercato = new Studente(matricola, nome, cognome, CDS);
				
			}
			rs.close();
			st.close();
			conn.close();
			
			return sCercato;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
