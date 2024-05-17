package application;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	private static int counter = 1;

	private String nome;
	private String cpf;
	private String email;

	public Cliente(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		setCounter(getCounter() + 1);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "\nNOME: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nEMAIL: " + this.getEmail();
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Cliente.counter = counter;
	}

	public static boolean validarCPF(String cpf) {
		if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
			return false;
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48);
			}

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}

			return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
		} catch (Exception e) {
			return false;
		}
	}
}
