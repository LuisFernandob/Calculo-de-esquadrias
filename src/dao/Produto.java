package dao;

public class Produto {

	private int idProduto;
	private String tipo = new String();
	private String descricao = new String();
	private String folhas = new String();
	private String observacoes = new String();
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getFolhas() {
		return folhas;
	}
	public void setFolhas(String folhas) {
		this.folhas = folhas;
	}
	
	
	
}
