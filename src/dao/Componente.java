package dao;

public class Componente {

	private String codigo_componente = new String();
	private String descricao = new String();
	private String tipo = new String();
	private double comprimento_barra_m;
	private String unidade = new String();
	private int largura_mm;
	private int largura_encaixe_mm;
	private double peso_por_metro;
	
	public String getCodigo_componente() {
		return codigo_componente;
	}
	public void setCodigo_componente(String codigo_componente) {
		this.codigo_componente = codigo_componente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getComprimento_barra_m() {
		return comprimento_barra_m;
	}
	public void setComprimento_barra_m(double comprimento_barra_m) {
		this.comprimento_barra_m = comprimento_barra_m;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public int getLargura_mm() {
		return largura_mm;
	}
	public void setLargura_mm(int largura_mm) {
		this.largura_mm = largura_mm;
	}
	public int getLargura_encaixe_mm() {
		return largura_encaixe_mm;
	}
	public void setLargura_encaixe_mm(int largura_encaixe_mm) {
		this.largura_encaixe_mm = largura_encaixe_mm;
	}
	public double getPeso_por_metro() {
		return peso_por_metro;
	}
	public void setPeso_por_metro(double peso_por_metro) {
		this.peso_por_metro = peso_por_metro;
	}
	public String getTipo() {
		
		String convertTipo = new String();

		try {
			

			if(!tipo.isEmpty()) {
				convertTipo = tipo;
			}
		} catch(NullPointerException e) {convertTipo = "-1";}
		
		return convertTipo;
		
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
