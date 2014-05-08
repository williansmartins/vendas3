package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Teste {

	@WebMethod
	public void method(){
		System.out.println("Web Services Executado!");
	}
}