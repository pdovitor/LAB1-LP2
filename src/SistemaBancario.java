import java.util.Scanner;

public class SistemaBancario {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println(" ------------------------------ ");
            System.out.println("|    1. Cadastro de usuário    |");
            System.out.println("|    2. Login                  |");
            System.out.println("|    3. Logout                 |");
            System.out.println("|    4. Criar conta            |");
            System.out.println("|    5. Consultar saldo        |");
            System.out.println("|    6. Depositar              |");
            System.out.println("|    7. Sacar                  |");
            System.out.println("|    8. Transferir             |");
            System.out.println("|    9. Listar contas          |");
            System.out.println("|    10. Sair                  |");
            System.out.println(" ------------------------------ ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.next();
                    System.out.print("CPF: ");
                    String cpf = scanner.next();
                    System.out.print("Senha: ");
                    String senha = scanner.next();
                    banco.cadastroUsuario(nome, cpf, senha);
                    break;
                case 2:
                    System.out.print("CPF: ");
                    cpf = scanner.next();
                    System.out.print("Senha: ");
                    senha = scanner.next();
                    banco.autenticacao(cpf, senha);
                    break;
                case 3:
                    banco.logout();
                    break;
                case 4:
                    System.out.print("Tipo da conta (corrente/poupanca): ");
                    String tipo = scanner.next();
                    double saldoInicial = 1000; // Saldo inicial fixo
                    banco.criarConta(tipo, saldoInicial);
                    break;
                case 5:
                    System.out.print("Número da conta: ");
                    int numeroConta = scanner.nextInt();
                    banco.consultarSaldo(numeroConta);
                    break;
                case 6:
                    System.out.print("Número da conta: ");
                    numeroConta = scanner.nextInt();
                    System.out.print("Valor para depositar: ");
                    double valorDeposito = scanner.nextDouble();
                    banco.depositar(numeroConta, valorDeposito);
                    break;
                case 7:
                    System.out.print("Número da conta: ");
                    numeroConta = scanner.nextInt();
                    System.out.print("Valor para sacar: ");
                    double valorSaque = scanner.nextDouble();
                    banco.sacar(numeroConta, valorSaque);
                    break;
                case 8:
                    System.out.print("Número da conta origem: ");
                    int numeroContaOrigem = scanner.nextInt();
                    System.out.print("Número da conta destino: ");
                    int numeroContaDestino = scanner.nextInt();
                    System.out.print("Valor para transferir: ");
                    double valorTransferencia = scanner.nextDouble();
                    banco.transferir(numeroContaOrigem, numeroContaDestino, valorTransferencia);
                    break;
                case 9:
                    banco.listarContas();
                    break;
                case 10:
                    System.out.print("\nSaindo...\n");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}