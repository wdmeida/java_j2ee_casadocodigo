package br.com.casadocodigo.loja.infra;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessagesHelper {
	@Inject
	private FacesContext facesContext;

	public void addFlash(FacesMessage facesMessage) {

		/*
		 * A classe FacesContext é usada internamente pelo JSF e contém várias informações associadas a um request
		 * na aplicação. A seguir, temos alguns métodos de exemplo:
		 * 
		 * 	getExternalContext() - para acessarmos objetos da especificação de Servlets;
		 * 	getMessagesList() - para termos acesso a todas as mensagens adicionadas;
		 * 	addMessages(...) - para adicionarmos uma mensagem a ser exibida na view;
		 * 	isValidationFailed() - para verificarmos se houve falhas no processo de validação.
		 * 
		 * Ainda existem vários outros métodos. Utilizamos addMessage pra adicionar a mensagem de sucesso. O segundo
		 * parâmetro é até bem claro, a classe FacesMessage é a abstração do JSF para representar uma mensage que vai
		 * ser adicionada à tela. 
		 * O primeiro parâmetro seve para indicarmos o id do elemento com o qual a mensagem deve estar associada. Como não
		 * queremos ligar a mensagem a nenhum elemento, passamos o null e a deixamos global, podendo ser exibida pela tag
		 * messages.
		 *
		 * O método getFlash retorna um objeto do tipo Flash. Invocando o setKeepMessages nesse objeto, estamos dizendo ao
		 * JSF que ele deve manter as mensagens até o próximo request.
		
		*/
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, facesMessage);
	}
}
