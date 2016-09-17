package br.com.casadocodigo.loja.managedbean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.model.Book;

/*
 * Anontation do CDI que indique que os objetos da classe por ela anotada sempre serão criados no escopo
 * de request, e ficarão disponíveis para serem acessados pela Expression Language.
 */
@Model
public class AdminBooksBean {
	private Book product = new Book();
	
	/*
		A annotation @Inject é justamente a responsável por indicar os pontos de injeção dentro da sua classe.
		Em um projeto usando CDI, a única configuração necessária para habilitar o scan de classes é a criação do 
		arquivo beans.xml, dentro da pasta WEB-INF.
	*/
	@Inject
	private BookDAO bookDAO;
	
	/*
		A annotation @Transactional foi introduzida a partir do Java EE 7, e permite marcar métodos gerenciados pelo
		CDI rodem dentro de um contexto transacional. Além disso, no início do capítulo, fizemos uma leve configuração 
		no arquivo persistence.xml.
	*/
	public void save() {
		bookDAO.save(product);
	}
	
	public Book getProduct() {
		return product;
	}
}
