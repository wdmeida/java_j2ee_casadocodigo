package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadocodigo.loja.model.Author;

@Stateless
public class AuthorDAO {
	@PersistenceContext
	private EntityManager manager;
	
	public void add(Author author){
		manager.persist(author);
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> list(){
		Query q = manager.createQuery("select a from Author a");
		return q.getResultList();
	}
}
