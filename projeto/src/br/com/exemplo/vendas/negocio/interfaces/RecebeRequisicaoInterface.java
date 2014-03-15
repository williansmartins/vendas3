package br.com.exemplo.vendas.negocio.interfaces ;

public interface RecebeRequisicaoInterface
{
	public br.com.exemplo.vendas.util.dto.ServiceDTO inserirFila(
			br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO )
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException ;

}
