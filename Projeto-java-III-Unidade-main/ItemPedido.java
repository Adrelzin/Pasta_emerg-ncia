public class ItemPedido {
    private int quantidade;
    private double subtotal;
    private Produto produto;

	public ItemPedido(int quantidade, double subtotal, Produto produto){
		this.quantidade = quantidade;
		this.subtotal = subtotal;
		this.produto = produto;
	}

    public double calcularSubTotal() {
        subtotal = produto.getPreco() * quantidade;
        return subtotal;
    }

    public void exibirItem() {
        produto.exibirProduto();
        System.out.println("Quantidade: " + quantidade + ", Subtotal: " + subtotal);
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
