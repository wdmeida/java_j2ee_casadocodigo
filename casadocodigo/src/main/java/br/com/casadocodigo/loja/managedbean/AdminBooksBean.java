package br.com.casadocodigo.loja.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.AuthorDAO;
import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.model.Author;
import br.com.casadocodigo.loja.model.Book;

/*
 * Anontation do CDI que indique que os objetos da classe por ela anotada sempre serão criados no escopo
 * de request, e ficarão disponíveis para serem acessados pela Expression Language.
 */
@Model
public class AdminBooksBean {
	private Book product = new Book();
	private List<Long> selectedAuthorsIds = new ArrayList<>();
	private List<Author> authors = new ArrayList<>();
	
	/*
		A annotation @Inject é justamente a responsável por indicar os pontos de injeção dentro da sua classe.
		Em um projeto usando CDI, a única configuração necessária para habilitar o scan de classes é a criação do 
		arquivo beans.xml, dentro da pasta WEB-INF.
	*/
	@Inject
	private BookDAO bookDAO;
	@Inject
	private AuthorDAO authorDAO;
	
	/*
		A annotation @Transactional foi introduzida a partir do Java EE 7, e permite marcar métodos gerenciados pelo
		CDI rodem dentro de um contexto transacional. Além disso, no início do capítulo, fizemos uma leve configuração 
		no arquivo persistence.xml.
	*/
	public void save() {
		populateBookAuthor();
		bookDAO.save(product);
	}
	
	/*
	 * O método populateBookAuthor usa um pouco da parte dos lambdas do Java 8.
	 */
	private void populateBookAuthor() {
		selectedAuthorsIds.stream().map( (id) -> {
			return new Author(id);
		}).forEach(product :: add);		
	}
	
	@PostConstruct
	public void loadObjects(){
		this.authors = authorDAO.list();
	}
	
	public Book getProduct() {
		return product;
	}

	public List<Long> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Long> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
}
