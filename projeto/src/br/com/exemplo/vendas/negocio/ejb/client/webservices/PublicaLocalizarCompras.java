package br.com.exemplo.vendas.negocio.ejb.client.webservices;

import javax.xml.ws.Endpoint;

public class PublicaLocalizarCompras {

	public static void main(String[] args) {
		LocalizarCompras localizarCompras = new LocalizarCompras();
		Endpoint endpoint = Endpoint.publish("http://localhost:8080/localizarCompras", localizarCompras);
	}
}