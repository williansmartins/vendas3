package br.com.exemplo.vendas.negocio.ejb.interfaces ;

import javax.ejb.Remote;

import br.com.exemplo.vendas.negocio.interfaces.ClienteFisicoInterface;

@Remote
public interface ClienteFisicoRemote extends ClienteFisicoInterface {
	
}