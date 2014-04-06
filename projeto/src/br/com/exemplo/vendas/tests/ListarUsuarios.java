package br.com.exemplo.vendas.tests;

import java.util.List;

import br.com.exemplo.vendas.apresentacao.service.Service;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class ListarUsuarios
{
    public static void main( String[] args )
    {
	Service service = new Service();
	try
	{
	    List<UsuarioVO> lista = service.listarUsuarios();
	    System.out.println(lista);
	    
	} catch ( LayerException e )
	{
	    System.out.println("ERRO:" + e.getMessage());
	}
    }
}
