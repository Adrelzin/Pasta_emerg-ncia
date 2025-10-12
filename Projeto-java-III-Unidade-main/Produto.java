public class Produto {
    private int idProduto;
    private String nome;
    private double preco;
    private String categoria;

    public Produto(int idProduto, String nome, double preco, String categoria) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public void exibirProduto() {
        System.out.println("ID do produto: " + idProduto + ", Produto: " + nome + ", Preço: " + preco + ", Categoria: " + categoria);
    }


    public int getIdProduto() {
        return this.idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
