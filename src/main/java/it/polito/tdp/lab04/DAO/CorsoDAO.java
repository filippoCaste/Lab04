package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/**
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}
			rs.close();
			st.close();
			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<String> getTuttiICorsiByNome() {

		final String sql = "SELECT corso.nome FROM corso";

		List<String> nomeCorsi = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String nome = rs.getString("nome");
				nomeCorsi.add(nome);
			}
			rs.close();
			st.close();
			conn.close();
			
			return nomeCorsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/**
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String nomeCorso) {
		Corso res = null;
		String sql = "SELECT * FROM corso c WHERE c.nome= ? ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, nomeCorso);
			ResultSet rs = st.executeQuery();
			
			if(rs.first())
				res = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore DB", e);
		}
		
		return res;
	}

	/**
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public Set<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola=i.matricola AND i.codins=c.codins AND c.codins= ? ";
		Set<Studente> result = new HashSet<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.add(new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS")));
			}
			rs.close();
			st.close();
			conn.close();
			
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore DB", e);
		}	
	}
	
	public boolean inserisciNuovoIscritto(Studente studente, Corso corso) {
		String sql = "INSERT INTO iscrizione "
				+ "VALUES ( ? , ? )";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			st.execute();
			
			st.close();
			conn.close();
			return true;
						
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore DB", e);
		}	
	}

	/**
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
