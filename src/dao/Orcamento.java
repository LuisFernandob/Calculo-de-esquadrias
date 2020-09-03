package dao;
import java.time.LocalDateTime;

public class Orcamento {
	
	
	private int idOrcamento,idCliente;
	private double valorTotal,pesoTotal;
	private String descricao = new String();
	private String dataHora = new String();

	
	public Orcamento(int idCliente,String descricao) {
		setIdCliente(idCliente);
		setDescricao(descricao);
		setDataHora();
	}
	public Orcamento(boolean horaAtual) {
		if(horaAtual==true) {
			setDataHora();
		}
	}
	
	
	public int getIdOrcamento() {
		return idOrcamento;
	}
	public void setIdOrcamento(int idOrcamento) {
		this.idOrcamento = idOrcamento;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public void setDataHora() {
		String ld = LocalDateTime.now().toString();
		this.dataHora = ld	;
	}
	public double getPesoTotal() {
		return pesoTotal;
	}
	public void setPesoTotal(double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	
	
	
}
