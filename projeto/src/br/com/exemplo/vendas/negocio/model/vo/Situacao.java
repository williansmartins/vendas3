package br.com.exemplo.vendas.negocio.model.vo;

public enum Situacao {

	CRIADO		("C", "CRIADO"),
	APROVADO	("A", "CRIADO"),
	REJEITADO	("R", "CRIADO"),
	ENTREGUE	("E", "CRIADO");
	
	private String codigo;
	private String descricao;
	
	private Situacao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public final String getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public final String getDescricao() {
		return descricao;
	}
	
	public static final Situacao getSituacao(String codigo){
		for(Situacao s: Situacao.values()){
			if(s.getCodigo().equalsIgnoreCase(codigo)){
				return s;
			}
		}
		return null;
	}
}
