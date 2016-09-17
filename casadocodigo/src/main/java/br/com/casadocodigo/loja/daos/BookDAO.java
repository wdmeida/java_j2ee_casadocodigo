package br.com.casadocodigo.loja.daos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.model.Book;

@Stateless
public class BookDAO {
	/*
	 * A annotation abaixo, é utilizada para indicar, dentro de um container Java EE, a necessidade de injeção
	 * de um EntityManager que seja controlado e criado pelo próprio servidor. O legal dessa annotation é que
	 * sua semântica é muito forte, então, várias outras tecnologias, como o Spring, fazem seu uso para indicar
	 * a injeção de um EntityManager.
	 */
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Book product){
		manager.persist(product);
	}

}
