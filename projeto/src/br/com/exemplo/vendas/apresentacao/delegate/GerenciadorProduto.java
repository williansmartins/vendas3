/**
 * 
 */
package br.com.exemplo.vendas.apresentacao.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;

/**
 * @author Alex
 *
 */
public class GerenciadorProduto {

	private Map<Long, Produto> produtos;
	
	public void incluir(Produto vo) throws Exception{
		if(getProdutoPorNome(vo.getDescricao()) != null){
			throw new Exception("Produto já existente!");
		}
		int codigo = getProdutos().size();
		vo.setCodigo((long) codigo);
		getProdutos().put((long) codigo, vo);
	}
	
	public Produto getProdutoPorNome(String nome){
		for(Produto p: getProdutos().values()){
			if(p.getDescricao().equals(nome)){
				return p;
			}
		}
		return null;
	}
	
	public Produto getProdutoPorID(Long id){
		if(getProdutos().containsKey(id)){
			return getProdutos().get(id);
		}
		return null;
	}
	
	public void alterar(Produto vo){
		getProdutos().put(vo.getCodigo(), vo);
	}
	
	public void excluir(ProdutoVO vo){
		getProdutos().remove(vo.getCodigo());
	}

	/**
	 * @return the produtos
	 */
	private final Map<Long, Produto> getProdutos() {
		if(produtos == null){
			setProdutos(new HashMap<Long, Produto>());
		}
		return produtos;
	}

	/**
	 * @param produtos the produtos to set
	 */
	private final void setProdutos(Map<Long, Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ProdutoVO> selecionarTodosProdutosVOs() {
		List<ProdutoVO> lista = new ArrayList<ProdutoVO>();
		for(Produto p: listar()){
			ProdutoVO vo = new ProdutoVO();
			vo.setCodigo(p.getCodigo());
			vo.setDescricao(p.getDescricao());
			vo.setQuantidadeEstoque(p.getQuantidadeEstoque());
			vo.setPreco(p.getPreco());
			lista.add(vo);
		}
		return lista;
	}

	private List<Produto> listar() {
		return new ArrayList<Produto>(getProdutos().values());
	}
	
}
