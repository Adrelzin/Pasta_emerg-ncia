public class Cartao extends Pagamento {
    private String numeroCartao;
    private String nomeTitular;
    private String validade;

	public Cartao(int idPagamento, double valor, String numeroCartao, String nomeTitular, String validade){
		super(idPagamento, valor);
		this.numeroCartao = numeroCartao;
		this.nomeTitular = nomeTitular;
		this.validade = validade;
	}

    public String getNumeroCartao() {
        return this.numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return this.nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getValidade() {
        return this.validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento com cartão para " + nomeTitular);
    }

    
}