package it.polito.tdp.lab04.model;

import java.util.List;
import java.util.Set;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;

	public Model() {
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}

	public List<Corso> getTuttiICorsi() {
		return this.corsoDao.getTuttiICorsi();
	}

	public List<String> getTuttiICorsiByNome() {
		return this.corsoDao.getTuttiICorsiByNome();
	}
	
	public Studente getStudenteByMatricola(Integer matricola) {
		return this.studenteDao.getStudenteByMatricola(matricola);
	}
	
	public Corso getCorsoByNome(String nome) {
		return this.corsoDao.getCorso(nome);
	}
	
	public Set<Studente> getStudentiByCorso(Corso c) {
		return this.corsoDao.getStudentiIscrittiAlCorso(c);
	}

	public Set<Corso> getCorsiByStudente(Studente studente) {
		return this.studenteDao.getCorsiStudente(studente);
	}
	
	public boolean inserisciStudenteACorso(Studente studente, Corso corso) {
		if(this.getCorsiByStudente(studente).contains(this.getCorsoByNome(corso.getNome())))
			return this.corsoDao.inserisciNuovoIscritto(studente, corso);
		else
			return false;
	}
}
