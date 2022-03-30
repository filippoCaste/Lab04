package it.polito.tdp.lab04.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	/**
	 * Dato uno studente, ritorna tutti i corsi ai quali è iscritto
	 * @param studente
	 * @return Set di corsi ai quali lo studente è iscritto
	 */
	public Set<Corso> getCorsiStudente(Studente studente) {
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM studente s, corso c, iscrizione i "
				+ "WHERE s.matricola=i.matricola AND s.matricola= ? AND i.codins=c.codins "
				+ "ORDER BY c.codins, c.crediti, c.nome, c.pd";
		
		Set<Corso> corsi = new HashSet<Corso>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				corsi.add(new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")));
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore DB", e);
		}
		
		return corsi;		
	}

}
