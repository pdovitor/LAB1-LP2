import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String cpf;
    private String senha;
    private ArrayList<Conta> contas = new ArrayList<>();

    public Usuario(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }
}