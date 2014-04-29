package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import javax.xml.ws.Endpoint;

public class PublicaLocalizarProdutos {

	public static void main(String[] args) {
		LocalizarProdutos localizarProdutos = new LocalizarProdutos();
		Endpoint endpoint = Endpoint.publish("http://localhost:8080/localizarProdutos", localizarProdutos);
	}
}