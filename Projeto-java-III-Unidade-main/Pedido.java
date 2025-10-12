import java.util.*;

public class Pedido {
    private int idPedido;
    private String tipoPedido;
    private double valorPedido;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private Pagamento pagamento;
	
	public Pedido(int idPedido, String tipoPedido, double valorPedido, Cliente cliente, List<ItemPedido> itens, Pagamento pagamento){
		this.idPedido = idPedido;
		this.tipoPedido = tipoPedido;
		this.valorPedido = valorPedido;
		this.cliente = cliente;
		this.itens = itens;
		this.pagamento = pagamento;
	}

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        calcularValorTotal();
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
        calcularValorTotal();
    }

    public double calcularValorTotal() {
        valorPedido = 0;
        for (ItemPedido item : itens) {
            valorPedido += item.calcularSubTotal();
        }
        return valorPedido;
    }

    public void exibirPedido() {
        System.out.println("Pedido ID: " + idPedido + ", Tipo: " + tipoPedido + ", Valor Total: " + valorPedido);
        cliente.exibirCliente();
        for (ItemPedido item : itens) {
            item.exibirItem();
        }
        if (pagamento != null) {
            pagamento.processarPagamento();
        }
    }

    
}
