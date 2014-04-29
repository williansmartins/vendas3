package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import javax.xml.ws.Endpoint;

public class PublicaLocalizarCliente {

	public static void main(String[] args) {
		LocalizarClientes localizarClientes = new LocalizarClientes();
		Endpoint endpoint = Endpoint.publish("http://localhost:8080/localizarClientes", localizarClientes);
	}
}