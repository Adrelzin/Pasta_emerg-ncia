import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();
        
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1:
                    Cliente novoCliente = cadastrarCliente(scanner);
                    clientes.add(novoCliente);
                    System.out.println("\n Cliente Cadastrado!\n");
                    novoCliente.exibirCliente();
                    break;

                case 2:
                    Produto novoProduto = cadastrarProduto(scanner);
                    produtos.add(novoProduto);
                    System.out.println("\n Produto Cadastrado!");
                    novoProduto.exibirProduto();
                    break;

                case 3:
					if (clientes.isEmpty()){
						System.out.println("Não tem clientes cadastrados");
						break;
					}
					
					if	(produtos.isEmpty()){
						System.out.println("Não tem produtos cadastrados");
						break;
					}
					
                    Pedido novoPedido = fazerPedido(scanner, clientes, produtos, pedido.size() + 1);
					pedidos.add(novoPedido);
					System.out.println
                    break;

                case 4:
                    System.out.println("Saindo");
                    break;

                default:
                    System.out.println("Opção Inválida!");
            }

        } while(opcao != 4); 
        
        scanner.close();
    }

    
    public static void exibirMenu() {
        System.out.println("""
                ========================================
                Bem-vindo ao Restaurante de Darel!
                ========================================
                1 - Cadastrar Cliente
                2 - Cadastrar Produto
                3 - Fazer Pedido
                4 - Sair
                ========================================
                """);
    }
    
    public static Cliente cadastrarCliente(Scanner scanner){
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        return new Cliente(id, nome, telefone, email);
    }

    public static Produto cadastrarProduto(Scanner scanner){
        System.out.print("Digite o id do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite a categoria do produto: ");
        String categoria = scanner.nextLine();

        return new Produto(id, nome, preco, categoria);
    }
	
	public static ItemPedido cadastrarPedido(Scanner scanner){
			
	}
} 
