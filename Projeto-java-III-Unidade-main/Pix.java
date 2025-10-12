public class Pix extends Pagamento {
    private String chavePix;

    public Pix(int idPagamento, double valor, String chavePix){
        super(idPagamento, valor);
        this.chavePix = chavePix;
    }

    public String getChavePix() {
        return this.chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento via Pix para chave: " + chavePix);
    }
    
}