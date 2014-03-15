package br.com.exemplo.vendas.apresentacao.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.exemplo.vendas.apresentacao.service.Service;
import br.com.exemplo.vendas.apresentacao.web.Action;
import br.com.exemplo.vendas.util.exception.LayerException;


public class EnviarMensagemACT implements Action {
	public String execute( HttpServletRequest request,  HttpServletResponse response ) throws LayerException {
		
		String mensagem = request.getParameter("mensagem");
		
		Service service = new Service();
		String ticket = service.enviaMensagem( mensagem ) ;

		request.getSession().setAttribute("ticket", ticket );

		return "popup.jsp";
	}
}
