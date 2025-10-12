public class Dinheiro extends Pagamento {
    private double troco;

    public Dinheiro(int idPagamento, double valor, double troco){
        super(idPagamento, valor);
        this.troco = troco;
    }

    public double getTroco() {
        return this.troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento em dinheiro. Troco: " + troco);
    }
    
}