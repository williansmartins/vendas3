package br.com.exemplo.vendas.negocio.ejb.interfaces ;

import javax.ejb.Remote ;

import br.com.exemplo.vendas.negocio.interfaces.UsuarioInterface ;

@Remote
public interface UsuarioRemote extends UsuarioInterface
{
}
