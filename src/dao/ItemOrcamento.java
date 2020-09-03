package dao;

public class ItemOrcamento {
	
    private int idItensOrcamento;
    private int idOrcamento;
    private int idProduto;
    private String contramarco = new String();
    private String contramarcoInferior = new String();
    private String arremate = new String();
    private String arremateInferior = new String();
    private int larguraMm;
    private int alturaMm;
    private int quantidade;
    private double pesoTotal;   
    private double valorTotal;        

	
    /**
     * @return the idItensOrcamento
     */
    public int getIdItensOrcamento() {
        return idItensOrcamento;
    }

    /**
     * @param idItensOrcamento the idItensOrcamento to set
     */
    public void setIdItensOrcamento(int idItensOrcamento) {
        this.idItensOrcamento = idItensOrcamento;
    }

    /**
     * @return the idOrcamento
     */
    public int getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * @param idOrcamento the idOrcamento to set
     */
    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * @return the idProduto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the larguraMm
     */
    public int getLarguraMm() {
        return larguraMm;
    }

    /**
     * @param larguraMm the larguraMm to set
     */
    public void setLarguraMm(int larguraMm) {
        this.larguraMm = larguraMm;
    }

    /**
     * @return the alturaMm
     */
    public int getAlturaMm() {
        return alturaMm;
    }

    /**
     * @param alturaMm the alturaMm to set
     */
    public void setAlturaMm(int alturaMm) {
        this.alturaMm = alturaMm;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the pesoTotal
     */
    public double getPesoTotal() {
        return pesoTotal;
    }

    /**
     * @param pesoTotal the pesoTotal to set
     */
    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

	public String getContramarco() {
		return contramarco;
	}

	public void setContramarco(String contramarco) {
		this.contramarco = contramarco;
	}

	public String getArremate() {
		return arremate;
	}

	public void setArremate(String arremate) {
		this.arremate = arremate;
	}

	public String getContramarcoInferior() {
		return contramarcoInferior;
	}

	public void setContramarcoInferior(String contramarcoInferior) {
		this.contramarcoInferior = contramarcoInferior;
	}

	public String getArremateInferior() {
		return arremateInferior;
	}

	public void setArremateInferior(String arremateInferior) {
		this.arremateInferior = arremateInferior;
	}
}
