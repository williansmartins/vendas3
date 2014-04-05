package br.com.exemplo.vendas.negocio.interfaces;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public interface ReservaInterface {

	public ServiceDTO inserirReserva(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO alterarReserva(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO excluirReserva(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO selecionarTodosReserva(ServiceDTO requestDTO) throws LayerException, RemoteException;
	public ServiceDTO getReserva(ServiceDTO requestDTO, Long codigo) throws LayerException, RemoteException;
}