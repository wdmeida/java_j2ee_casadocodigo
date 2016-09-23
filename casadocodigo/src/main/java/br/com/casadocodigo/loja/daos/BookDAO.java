package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Book;

/*
 * Quando não colocamos nenhuma anotação em cima da classe, estamos dizendo
 * que, na verdade, quem decide o escopo do objeto é o local onde ele vai ser
 * injetado.
 * Como já vimos, a annotation @Model indica que os objetos da classe anotada
 * devem viver pelo tempo de uma requisição web e, além disso, também devem ficar
 * expostos na expression language. Como a classe BookDAO não definiu o escopo 
 * explicitamente, o tempo de vida do seu objeto, nesse caso, vai ser o da duração
 * de um request, já que a classe que solicitou sua injeção definiu este escopo.
 * Caso tivéssemos pedido essa injeção em uma classe de escopo session, o objeto do
 * tipo BookDAO teria assumido o mesmo. Essa estratégia dentro do CDI é chamada de 
 * escopo @Dependent. Não adicionar nenhuma anotação de tempo de vida é o mesmo que
 * dizer que o escopo da classe é @Dependent.
 */
public class BookDAO {
	/*
	 * Indica dentro de um container Java EE, a necessidade de injeção de um EntityManager
	 * que seja controlado e criado pelo próprio servidor. O legal dessa annotation é que
	 * sua semântica é muito forme, então, várias outras tecnologias, como o Spring, fazem
	 * seu uso para indicar a injeção de um EntityManager.
	 */
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Book product){
		manager.persist(product);
	}
	
	public List<Book> list(){
		return manager.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class).getResultList();
	}
}
