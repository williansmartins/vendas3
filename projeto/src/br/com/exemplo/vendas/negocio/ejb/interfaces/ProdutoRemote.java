package br.com.exemplo.vendas.negocio.ejb.interfaces ;

import javax.ejb.Remote;

import br.com.exemplo.vendas.negocio.interfaces.ProdutoInterface;

@Remote
public interface ProdutoRemote extends ProdutoInterface {
	
}