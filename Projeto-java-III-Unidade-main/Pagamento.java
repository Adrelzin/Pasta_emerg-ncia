public abstract class Pagamento {
    private int idPagamento;
    private double valor;
	
	public Pagamento(int idPagamento, double valor){
		this.idPagamento = idPagamento;
		this.valor = valor;
	}

	public int getIdPagamento(){
		return this.idPagamento;
	}
	
	public void setIdPagamento(int idPagamento){
			this.idPagamento = idPagamento;
	}
	
	public double getValor(){
		return this.valor;
	}
	
	public void setValor(double valor){
			this.valor = valor;
	}

    public abstract void processarPagamento();
    
   
}