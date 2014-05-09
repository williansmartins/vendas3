package br.com.exemplo.vendas.apresentacao.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ColumnResizeEvent;

import br.com.exemplo.vendas.apresentacao.service.Service;
import br.com.exemplo.vendas.util.JSFMessageUtil;
import br.com.exemplo.vendas.util.Redirecionador;


public class AbstractController {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
	private Service servico;

	public AbstractController() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}
	
	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}
	
	protected void closeDialog(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}
	
	protected void keepDialogOpen(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}
	
	public void onResize(ColumnResizeEvent event) {  
        FacesMessage msg = new FacesMessage("Column " + event.getColumn().getClientId( FacesContext.getCurrentInstance() ) + " resized", "W:" + event.getWidth() + ", H:" + event.getHeight());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    } 
	
	protected RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}
	
	public void irPara(String url){
		Redirecionador.redirecionar(url);
	}
	
	/**
	 * @return the service
	 */
	public final Service getServico() {
		if (this.servico == null) {
			this.servico = new Service();
		}
		return servico;
	}
}