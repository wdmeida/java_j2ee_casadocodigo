package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadocodigo.loja.models.Author;

public class AuthorDAO {
	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Author> list(){
		Query q = manager.createQuery("select a from Author a");
		return q.getResultList();
	}
}
