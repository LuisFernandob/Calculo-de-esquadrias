package dao;

public class Cliente {

	private int idCliente;
	private String nome = new String();
	private String empresa = new String();
	private String telefone = new String();
	private String endereco = new String();
	private String dataNascimento = new String();
	private String email = new String();
	private String cnpj = new String();
	private String cpf = new String();
	private String observacoes = new String();
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
	public String converterDataMysqlParaJTable(String data) {
		String[] elementos = data.split("-",3);
 		String dataJTable = elementos[2];
 		dataJTable = dataJTable.concat("/");
 		dataJTable = dataJTable.concat(elementos[1]);
 		dataJTable = dataJTable.concat("/");
 		dataJTable = dataJTable.concat(elementos[0]);
	    	
	    	return dataJTable;
	}
	public String converterDataJTableParaMysql(String data) {
			
			String[] elementos = data.split("/",3);
	 
	 		String datamysql = elementos[2];
	 		datamysql = datamysql.concat("-");
	 		datamysql = datamysql.concat(elementos[1]);
	 		datamysql = datamysql.concat("-");
	 		datamysql = datamysql.concat(elementos[0]);

	 	return datamysql;
     }
	
}
