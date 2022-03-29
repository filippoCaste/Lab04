package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;

	public Model() {
		super();
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

}
