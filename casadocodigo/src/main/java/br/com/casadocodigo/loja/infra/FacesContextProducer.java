package br.com.casadocodigo.loja.infra;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/*
 * O JSF, até a versão 2.2, não possui uma integração completa com o CDI. Alguns
 * componentes, como o FacesContext, ainda não são gerenciados pelo container de 
 * injeção. A parte legal é que isso não é um problema muito grande, basta que 
 * ensinemos ao CDI como ele deve criar um objeto do tipo FacesContext.  
 */
@ApplicationScoped
public class FacesContextProducer {
	
	/*
	 * O método anotado com @Produces deve ser sempre usado quando o processo de criação
	 * de um objeto não é o padrão. Outra situação é quando queremos produzir um objeto 
	 * de uma classe que não é gerenciada pelo CDI. O FacesContext, por coincidência, se
	 * enquadra nas duas situações. Ainda usamos duas anotações referentes ao escopo:
	 * 	
	 * 	@ApplicationScoped - para dizer que só é necessária uma instância do FacesContextProducer;
	 * 	@RequestScoped - para dizer que o método get deve ser chamado uma vez a cada novo request.
	 * 
	 * É importante que o FacesContext seja novamente produzido a cadarequest, caso contrário, você 
	 * pode acabar injetando uma instância que foi populada com informações de outra requisição.
	 */
	@Produces
	@RequestScoped
	public FacesContext get(){
		return FacesContext.getCurrentInstance();
	}
}
