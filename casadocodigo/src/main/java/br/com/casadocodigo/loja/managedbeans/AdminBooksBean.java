package br.com.casadocodigo.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AuthorDAO;
import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.models.Author;
import br.com.casadocodigo.loja.models.Book;

/*
 * Annotation @Model é parte do CDI que indica que os objetos da classe
 * por ela anotada sempre serão criados no escopo de request, e ficarão 
 * disponíveis para serem acessados pela Expression Language.
 */
@Model
public class AdminBooksBean {
	
	private Book product = new Book();
	
	/*
	 * Poderiamos instanciar o objeto BookDAO de forma tradicional (bookDAO = new BookDAO();), o
	 * problema é que, provavelmente, o construtor do BookDAO vai precisar receber algum
	 * objeto que represente a conexão com o banco de dados e, nesse caso, vamos ter de 
	 * começar a controlar essas dependências na mão. Essa é uma ótima parte para usarmos
	 * mais do CDI no projeto.
	 * Como a nossa classe já é gerenciada pelo CDI, podemos pedir para que ele instancie um
	 * novo objeto do tipo BookDAO para nós. O nosso único trabalho é indicar que precisamos 
	 * receber essa instância injetada.
	 * A annotation @Inject é justamente a responsável por indicar os pontos de injeção dentro
	 * de sua classe. Esse é um bom momemto para lembrar de que, em um projeto usando CDI, a 
	 * única configuração necessária para habilitar o scan de classes é a criação do arquivo
	 * beans.xml, dentro da pasta WEB-INF.
	 */
	@Inject
	private BookDAO bookDAO;
	@Inject
	private AuthorDAO authorDAO;
	private List<Integer> selectedAuthorsIds = new ArrayList<>();
	private List<Author> authors = new ArrayList<>();
	
	/*
	 * A annotation @Transactional foi introduzida a partir do Java EE 7, e permite marcar que
	 * métodos gerenciados pelo CDI rodem dentro de um contexto transacional. Além disso, no ínicio 
	 * do capítulo, também fizemos uma leve configuração no arquivo persistence.xml.
	 * Indicamos que quem vai cuidar das nossas transações é a JTA, justamente a especificação que
	 * cuida da parte de transações no Java EE. Também é por conta dessa especificação que usamos a tag
	 * <jta-data-source>, para informar que nosso DataSource deve ser criado de modo que as transações
	 * sejam gerenciadas pela JTA.
	 * Apenas por curiosidade, quando rodamos nossa aplicação em um servidor de aplicação, o valor do 
	 * atributo transaction-type já é JTA por default. Quando rodamos uma aplicação que usa a JPA dentro
	 * de outro ambiente, o valor é RESOURCE_LOCAL que informa que o responsável por cuidar das transações
	 * é o próprio EntityManager.
	 */
	@Transactional
	public void save(){
		populateBookAuthor();
		bookDAO.save(product);
	}
	
	private void populateBookAuthor() {
		selectedAuthorsIds.stream().map( (id) -> {
			return new Author(id);
		}).forEach(product :: add);
	}
	
	public Book getProduct() {
		return product;
	}

	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}

	public List<Author> getAuthors() {
		return authors;
	}
	
	/*
	 * Essa annotation vem de uma espeficifação chamada Commons Annotations for the
	 * Java Platform. Este método é chamado automaticamente pela nossa 
	 * implementação do CDI e será execuado logo após a parte de injeção de dependências
	 * ter sido realizada.
	 */
	@PostConstruct
	private void loadObjetcs(){
		this.authors = authorDAO.list();
	}
	
}
