package application;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Program {

	static Scanner sc = new Scanner(System.in);
	static ArrayList<Conta> contasBancarias;

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		contasBancarias = new ArrayList<>();
		operacoes();
	}

	public static void operacoes() {
		System.out.println("|---------------------------------------------------|");
		System.out.println("|------------Bem vindo a nossa Agência--------------|");
		System.out.println("|---------------------------------------------------|");
		System.out.println("|**** Selecione a operação que deseja realizar *****|");
		System.out.println("|---------------------------------------------------|");
		System.out.println("| Opção 1 - Criar Conta                              |");
		System.out.println("| Opção 2 - Depositar                                |");
		System.out.println("| Opção 3 - Sacar                                    |");
		System.out.println("| Opção 4 - Transferir                               |");
		System.out.println("| Opção 5 - Listar                                   |");
		System.out.println("| Opção 6 - Sair                                     |");
		System.out.println("|---------------------------------------------------|");

		int operacao = sc.nextInt();
		sc.nextLine(); // consume newline

		switch (operacao) {
		case 1:
			criarConta();
			break;
		case 2:
			depositar();
			break;
		case 3:
			sacar();
			break;
		case 4:
			transferir();
			break;
		case 5:
			listarContas();
			break;
		case 6:
			System.out.println("Obrigado por utilizar nossa agência.");
			System.exit(0);
			break;
		default:
			System.out.println("Opção inválida!");
			operacoes();
			break;
		}
	}

	public static void criarConta() {
		System.out.print("Nome: ");
		String nome = sc.nextLine();

		System.out.print("CPF: ");
		String cpf = sc.nextLine();

		while (!Cliente.validarCPF(cpf)) {
			System.out.println("CPF inválido! Tente novamente.");
			System.out.print("CPF: ");
			cpf = sc.nextLine();
		}

		System.out.print("Email: ");
		String email = sc.nextLine();

		Cliente cliente = new Cliente(nome, cpf, email);

		System.out.print("Deseja fazer um depósito inicial? (s/n): ");
		String respostaInicial = sc.nextLine();
		double depositoInicial = 0.0;
		if (respostaInicial.equalsIgnoreCase("s")) {
			System.out.print("Valor do depósito inicial: ");
			depositoInicial = sc.nextDouble();
			sc.nextLine(); // consume newline
		}

		Conta conta = new Conta(cliente, depositoInicial);
		contasBancarias.add(conta);

		System.out.println("Sua conta foi criada com sucesso!");
		operacoes();
	}

	private static Conta encontrarConta(int numeroConta) {
		for (Conta c : contasBancarias) {
			if (c.getNumeroConta() == numeroConta) {
				return c;
			}
		}
		return null;
	}

	public static void depositar() {
		System.out.print("Número da conta: ");
		int numeroConta = sc.nextInt();
		sc.nextLine(); // consume newline

		Conta conta = encontrarConta(numeroConta);

		if (conta != null) {
			System.out.print("Qual valor deseja depositar? ");
			double valorDeposito = sc.nextDouble();
			sc.nextLine(); // consume newline
			conta.depositar(valorDeposito);
		} else {
			System.out.println("Conta não encontrada!");
		}
		operacoes();
	}

	public static void sacar() {
		System.out.print("Número da conta: ");
		int numeroConta = sc.nextInt();
		sc.nextLine(); // consume newline

		Conta conta = encontrarConta(numeroConta);

		if (conta != null) {
			System.out.print("Qual valor deseja sacar? ");
			double valorSaque = sc.nextDouble();
			sc.nextLine(); // consume newline
			conta.sacar(valorSaque);
		} else {
			System.out.println("Conta não encontrada!");
		}
		operacoes();
	}

	public static void transferir() {
		System.out.print("Número da conta do remetente: ");
		int numeroContaRemetente = sc.nextInt();
		sc.nextLine(); // consume newline

		Conta contaRemetente = encontrarConta(numeroContaRemetente);

		if (contaRemetente != null) {
			System.out.print("Número da conta do destinatário: ");
			int numeroContaDestinatario = sc.nextInt();
			sc.nextLine(); // consume newline

			Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

			if (contaDestinatario != null) {
				System.out.print("Valor da transferência: ");
				double valor = sc.nextDouble();
				sc.nextLine(); // consume newline
				contaRemetente.transferir(contaDestinatario, valor);
			} else {
				System.out.println("Conta do destinatário não encontrada!");
			}
		} else {
			System.out.println("Conta do remetente não encontrada!");
		}
		operacoes();
	}

	public static void listarContas() {
		if (contasBancarias.size() > 0) {
			for (Conta conta : contasBancarias) {
				System.out.println(conta);
			}
		} else {
			System.out.println("Não há contas cadastradas!");
		}
		operacoes();
	}
}

