public class Cliente {
    private int idCliente;
    private String nomeCliente;
    private String telefone;
    private String email;

    public Cliente(int idCliente, String nomeCliente, String telefone, String email) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.email = email;
    }
    
    public void exibirCliente() {
        System.out.println("Cliente: " + nomeCliente + ", Telefone: " + telefone + ", Email: " + email);
    }


    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
