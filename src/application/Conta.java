package application;

import utilitarios.Utils;

public class Conta {
	private static int contadorDeContas = 1;

	private int numeroConta;
	private String holder;
	private Cliente cliente;
	private double saldo = 0.0;

	public Conta(Cliente cliente) {
		this.numeroConta = contadorDeContas++;
		this.cliente = cliente;
		this.holder = cliente.getNome();
	}

	public Conta(Cliente cliente, double depositoInicial) {
		this(cliente);
		deposit(depositoInicial);
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public void deposit(double amount) {
		saldo += amount;
		System.out.println("Depósito realizado com sucesso!");
	}

	public void depositar(double amount) {
		saldo += amount;
		System.out.println("Depósito realizado com sucesso!");
	}

	public void sacar(double amount) {
		if (amount > 0 && saldo >= amount + 5.0) {
			saldo -= amount + 5.0;
			System.out.println("Saque realizado com sucesso!");
		} else {
			System.out.println("Não foi possível realizar o saque!");
		}
	}

	public void withdraw(double amount) {
		if (amount > 0 && saldo >= amount + 5.0) {
			saldo -= (amount + 5.0);
			System.out.println("Saque realizado com sucesso!");
		} else {
			System.out.println("Não foi possível realizar o saque!");
		}
	}

	public void transferir(Conta contaParaDeposito, double amount) {
		if (amount > 0 && saldo >= amount) {
			saldo -= amount;
			contaParaDeposito.saldo += amount;
			System.out.println("Transferência realizada com sucesso!");
		} else {
			System.out.println("Não foi possível realizar a transferência!");
		}
	}

	@Override
	public String toString() {
		return "\nNúmero da conta: " + numeroConta + "\nNome: " + holder + "\nCPF: " + cliente.getCpf() + "\nEmail: "
				+ cliente.getEmail() + "\nSaldo: " + String.format("R$ %.2f", saldo) + "\n";
	}
}
