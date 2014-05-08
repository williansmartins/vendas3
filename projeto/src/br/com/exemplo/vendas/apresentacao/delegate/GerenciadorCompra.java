package br.com.exemplo.vendas.apresentacao.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.exemplo.vendas.negocio.entity.Compra;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;

public class GerenciadorCompra {

	private Map<Long, Compra> compras;
	
	public void incluir(Compra vo) throws Exception{
		if(getCompraPorId(vo.getNumero()) != null){
			throw new Exception("Compra já existente!");
		}
		getCompras().put(vo.getNumero(), vo);
	}
	
	public Compra getCompraPorId(Long numero){
		for(Compra c: getCompras().values()){
			if(c.getNumero().equals(numero)){
				return c;
			}
		}
		return null;
	}
	
	/*public Produto getProdutoPorID(Long id){
		if(getProdutos().containsKey(id)){
			return getProdutos().get(id);
		}
		return null;
	}*/
	
	public void alterar(Compra vo){
		getCompras().put(vo.getNumero(), vo);
	}
	
	public void excluir(Compra vo){
		getCompras().remove(vo.getNumero());
	}

	/**
	 * @return the produtos
	 */
	private final Map<Long, Compra> getCompras() {
		if(compras == null){
			setCompras(new HashMap<Long, Compra>());
		}
		return compras;
	}

	/**
	 * @param produtos the produtos to set
	 */
	private final void setCompras(Map<Long, Compra> compras) {
		this.compras = compras;
	}

	public List<CompraVO> selecionarTodosUsuariosVOs() {
		List<CompraVO> lista = new ArrayList<CompraVO>();
		for(Compra c: listar()){
			
			CompraVO vo = new CompraVO();
			vo.setData(c.getData());
			vo.setNumero(c.getNumero());
			vo.setSituacao(c.getSituacao());
			vo.setValor(c.getValor());
			
			ClienteVO clienteVO = new ClienteVO();
			clienteVO.setBloqueado(c.getCliente().getBloqueado());
			clienteVO.setCodigo(c.getCliente().getCodigo());
			clienteVO.setEndereco(c.getCliente().getEndereco());
			clienteVO.setLogin(c.getCliente().getLogin());
			clienteVO.setNome(c.getCliente().getNome());
			clienteVO.setPerfil(c.getCliente().getPerfil());
			clienteVO.setSenha(c.getCliente().getSenha());
			clienteVO.setSituacao(c.getCliente().getSituacao());
			clienteVO.setTelefone(c.getCliente().getTelefone());
			clienteVO.setUltimoAcesso(c.getCliente().getUltimoAcesso());
			vo.setCliente(clienteVO);
			
			ReservaVO reservaVO = new ReservaVO();
			reservaVO.setAtendente(c.getReserva().getAtendente());
			
			ClienteVO clienteDaReserva = new ClienteVO();
			clienteDaReserva.setBloqueado(c.getReserva().getCliente().getBloqueado());
			clienteDaReserva.setCodigo(c.getReserva().getCliente().getCodigo());
			clienteDaReserva.setEndereco(c.getReserva().getCliente().getEndereco());
			clienteDaReserva.setLogin(c.getReserva().getCliente().getLogin());
			clienteDaReserva.setNome(c.getReserva().getCliente().getNome());
			clienteDaReserva.setPerfil(c.getReserva().getCliente().getPerfil());
			clienteDaReserva.setSenha(c.getReserva().getCliente().getSenha());
			clienteDaReserva.setSituacao(c.getReserva().getCliente().getSituacao());
			clienteDaReserva.setTelefone(c.getReserva().getCliente().getTelefone());
			clienteDaReserva.setUltimoAcesso(c.getReserva().getCliente().getUltimoAcesso());
			
			reservaVO.setCliente(clienteVO);
			reservaVO.setCodigo(c.getReserva().getCodigo());
			reservaVO.setData(c.getReserva().getData());
			reservaVO.setSituacao(c.getReserva().getSituacao());
			reservaVO.setValor(c.getReserva().getValor());
			vo.setReserva(reservaVO);
			
			lista.add(vo);
		}
		return lista;
	}

	private List<Compra> listar() {
		return new ArrayList<Compra>(getCompras().values());
	}
}