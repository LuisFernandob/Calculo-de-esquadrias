package dao;

public class Tipologia {

	private int idTipologia;
	private int idProduto;
	private String codigo_componente = new String();
	private double multiplicadorLargura;
	private double offsetLargura;
	private double multiplicadorAltura;
	private double offsetAltura;
	private double peso_por_metro;
	private String descricaoProduto = new String();
	private String descricaoComponente = new String();	
	private static int idProdutoAtual;
	
	
	
	public int getIdTipologia() {
		return idTipologia;
	}
	public void setIdTipologia(int idTipologia) {
		this.idTipologia = idTipologia;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getCodigo_componente() {
		return codigo_componente;
	}
	public void setCodigo_componente(String codigo_componente) {
		this.codigo_componente = codigo_componente;
	}
	public double getMultiplicadorLargura() {
		return multiplicadorLargura;
	}
	public void setMultiplicadorLargura(double multiplicadorLargura) {
		this.multiplicadorLargura = multiplicadorLargura;
	}
	public double getMultiplicadorAltura() {
		return multiplicadorAltura;
	}
	public void setMultiplicadorAltura(double multiplicadorAltura) {
		this.multiplicadorAltura = multiplicadorAltura;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public String getDescricaoComponente() {
		return descricaoComponente;
	}
	public void setDescricaoComponente(String descricaoComponente) {
		this.descricaoComponente = descricaoComponente;
	}
	public double getPeso_por_metro() {
		return peso_por_metro;
	}
	public void setPeso_por_metro(double peso_por_metro) {
		this.peso_por_metro = peso_por_metro;
	}
	public static int getIdProdutoAtual() {
		return idProdutoAtual;
	}
	public static void setIdProdutoAtual(int idProdutoAtual) {
		Tipologia.idProdutoAtual = idProdutoAtual;
	}
	public double getOffsetLargura() {
		return offsetLargura;
	}
	public void setOffsetLargura(double offsetLargura) {
		this.offsetLargura = offsetLargura;
	}
	public double getOffsetAltura() {
		return offsetAltura;
	}
	public void setOffsetAltura(double offsetAltura) {
		this.offsetAltura = offsetAltura;
	}
	
}
