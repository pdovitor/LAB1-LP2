import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private Usuario usuarioLogado;

    public void cadastroUsuario(String nome, String cpf, String senha) {
        if (!usuarios.containsKey(cpf)) {
            usuarios.put(cpf, new Usuario(nome, cpf, senha));
            System.out.println("\nUsuário cadastrado com sucesso!\n");
        }
        else{
            System.out.println("\nCPF já cadastrado!\n");
        }
    }

    public void autenticacao(String cpf, String senha) {
        Usuario usuario = usuarios.get(cpf);
        if (usuario != null && usuario.validarSenha(senha)) {
            usuarioLogado = usuario;
            System.out.println("\nBem vindo(a) " + usuarioLogado.getNome() + "!\n");
        }
        else{
            System.out.println("\nCPF ou senha incorretos.\n");
        }
    }

    public void criarConta(String tipo, double saldoInicial) {
        if (usuarioLogado != null) {
            Conta novaConta = new Conta(tipo, saldoInicial);
            usuarioLogado.adicionarConta(novaConta);
            System.out.println("\nConta criada com sucesso! Número da conta: " + novaConta.getNumero() + "\n");
        } else {
            System.out.println("\nNenhum usuário logado.\n");
        }
    }

    public double consultarSaldo(int numeroConta) {
        if (usuarioLogado != null) {
            for (Conta conta : usuarioLogado.getContas()) {
                if (conta.getNumero() == numeroConta) {
                    System.out.println("\nSaldo da conta " + numeroConta + ": " + conta.getSaldo() + "\n");
                    return conta.getSaldo();
                }
            }
        }
        System.out.println("\nConta não encontrada.\n");
        return -1;
    }

    public void depositar(int numeroConta, double valor) {
        if (usuarioLogado != null) {
            for (Conta conta : usuarioLogado.getContas()) {
                if (conta.getNumero() == numeroConta) {
                    conta.depositar(valor);
                    System.out.println("\nDepósito realizado com sucesso! Saldo atual da conta " + numeroConta + ": " + conta.getSaldo() + "\n");
                }
            }
        }
        else{
            System.out.println("\nConta não encontrada.\n");
        }
    }

    public boolean sacar(int numeroConta, double valor) {
        if (usuarioLogado != null) {
            for (Conta conta : usuarioLogado.getContas()) {
                if (conta.getNumero() == numeroConta) {
                    if (conta.sacar(valor)) {
                        System.out.println("\nSaque realizado com sucesso! Saldo atual da conta " + numeroConta + ": " + conta.getSaldo() +"\n");
                        return true;
                    } else {
                        System.out.println("\nSaldo insuficiente.\n");
                        return false;
                    }
                }
            }
        }
        System.out.println("\nConta não encontrada.\n");
        return false;
    }

    public boolean transferir(int numeroContaOrigem, int numeroContaDestino, double valor) {
        if (usuarioLogado != null) {
            Conta contaOrigem = null, contaDestino = null;
            for (Usuario usuario : usuarios.values()) {
                for (Conta conta : usuario.getContas()) {
                    if (conta.getNumero() == numeroContaOrigem) {
                        contaOrigem = conta;
                    } else if (conta.getNumero() == numeroContaDestino) {
                        contaDestino = conta;
                    }
                }
            }
            if (contaOrigem != null && contaDestino != null) {
                if (contaOrigem.transferir(contaDestino, valor)) {
                    System.out.println("Transferência realizada com sucesso!");
                    System.out.println("Saldo da conta origem (" + numeroContaOrigem + "): " + contaOrigem.getSaldo());
                    System.out.println("Saldo da conta destino (" + numeroContaDestino + "): " + contaDestino.getSaldo());
                    return true;
                } else {
                    System.out.println("Saldo insuficiente na conta origem.");
                    return false;
                }
            }
        }
        System.out.println("Uma ou ambas as contas não foram encontradas.");
        return false;
    }

    public void listarContas() {
        if (usuarioLogado != null) {
            System.out.println("Contas do usuário: " + usuarioLogado.getNome());
            for (Conta conta : usuarioLogado.getContas()) {
                System.out.println("Número: " + conta.getNumero() + ", Tipo: " + conta.getTipo() + ", Saldo: " + conta.getSaldo());
            }
        } else {
            System.out.println("Nenhum usuário logado.");
        }
    }    

    public void logout() {
        if (usuarioLogado != null) {
            usuarioLogado = null;
            System.out.println("Logout realizado com sucesso!");
        }
        else{
            System.out.println("Nenhum usuário logado!");
        }
    }
}