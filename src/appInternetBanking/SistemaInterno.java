package appInternetBanking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SistemaInterno {

	public static void main(String[] args) throws NumberFormatException, IOException {

		Map<String, Usuario> mapUsers = new HashMap<>();
		Map<String, Conta> mapContas = new HashMap<>();
		Map<Integer, Conta> mapContasNumeroConta = new HashMap<>();
		List<Object> numeroAgencias = new ArrayList<>();

		Usuario user = null;
		Conta conta = null;

		String path = "E:\\Meus Documentos\\Área de Trabalho\\Workspace\\SISTEMA-BANCARIO---JAVA1-POO---RESIDENCIA-DE-SOFTWARE\\Projeto Banco\\usuario.txt";
		String linha = "";

//		FileWriter arq = new FileWriter(
//				"C:\\Users\\jncon\\Documents\\workspace-spring-tool-suite-4-4.15.3.RELEASE\\Projeto Banco\\alteracoes.txt");
//		PrintWriter gravarArq = new PrintWriter(arq);
//		BufferedWriter arq2 = new BufferedWriter(arq);
		BufferedReader br = new BufferedReader(new FileReader(path));

		while ((linha = br.readLine()) != null) {
			String tipoUsuario;
			String CPF;
			String senha;
			String nome;
			String TipoConta;
			int numeroConta;
			int agencia;
			double saldo;
			double tarifacao;

			String[] data = linha.split(",");
			tipoUsuario = data[0];
			CPF = data[1];
			senha = data[2];
			nome = data[3];
			TipoConta = data[4];
			numeroConta = Integer.parseInt(data[5]);
			agencia = Integer.parseInt(data[6]);
			saldo = Double.parseDouble(data[7]);
			tarifacao = Double.parseDouble(data[8]);

			switch (tipoUsuario) {

			case "Cliente":

				user = new Cliente(tipoUsuario, CPF, senha, nome, TipoConta);
				mapUsers.put(CPF, user);

				break;

			case "Gerente":

				user = new Gerente(tipoUsuario, CPF, senha, nome, TipoConta, agencia);
				mapUsers.put(CPF, user);
				break;

			case "Diretor":

				user = new Diretor(tipoUsuario, CPF, senha, nome, TipoConta);
				mapUsers.put(CPF, user);
				break;

			case "Presidente":

				user = new Presidente(tipoUsuario, CPF, senha, nome, TipoConta);
				mapUsers.put(CPF, user);
				break;

			}

			switch (TipoConta) {

			case "Corrente":

				conta = new Corrente(TipoConta, CPF, nome, tipoUsuario, numeroConta, agencia, saldo, tarifacao);
				mapContas.put(CPF, conta);

				mapContasNumeroConta.put(numeroConta, conta);
				numeroAgencias.add(conta);
				break;

			case "Poupanca":

				conta = new Poupanca(TipoConta, CPF, nome, tipoUsuario, numeroConta, agencia, saldo, tarifacao);
				mapContas.put(CPF, conta);
				mapContasNumeroConta.put(numeroConta, conta);
				numeroAgencias.add(conta);
				break;

			}

		}

		br.close();
		try {
			System.out.println("Digite seu CPF: ");
			Scanner scan = new Scanner(System.in);
			String CPFParaLogar = scan.nextLine();

			Usuario logado = login(CPFParaLogar, mapUsers);
			Conta logada = mapContas.get(CPFParaLogar);

			primeiroMenu(logado.getCPF(), logada, mapContas, mapContasNumeroConta, numeroAgencias);
		} catch (NullPointerException e) {

		} catch (Exception e) {
			System.out.println("");
		}

	}

//LOGIN///
	public static Usuario login(String cPFParaLogar, Map<String, Usuario> mapUsers) {

		Usuario logado = mapUsers.get(cPFParaLogar);

		if (mapUsers.get(cPFParaLogar) != null) {

			System.out.println("Digite sua senha: ");
			Scanner scan2 = new Scanner(System.in);
			String senhaDigitada = scan2.next();

			if (senhaDigitada.equals(logado.getSenha())) {
				return logado;
			}

			else {
				System.out.println("Senha incorreta. Inicie novamente");
				return login(senhaDigitada, mapUsers);
			}

		} else {
			System.out.println("Usuário não encontrado. Inicie novamente");
			return null;
		}

	}
	
	public static void primeiroMenu(String string, Conta logada, Map<String, Conta> mapContas,
			Map<Integer, Conta> mapContasNumeroConta, List<Object> numeroAgencias) throws IOException {

		boolean sair = false;

		do {
			// Primeiro Menu
			System.out.println("\nSeja bem vindo, " + logada.nome + "!");
			System.out
					.println("Nmr da Conta: " + logada.getNumeroDaConta() + " Agência: " + logada.getAgencia() + "\n");
			System.out.println("Escolha a operação desejada:" + "\n1- Movimentações\n2- Relatórios\n3- Sair ");
			Scanner scan = new Scanner(System.in);
			int op = scan.nextInt();

			switch (op) {

			// para Movimentacoess
			case 1:

				// chama menuMovimentacoes
				Conta.movimentacoes(string, logada, mapContas, mapContasNumeroConta);

				break;

			// para Relatorios
			case 2:

				Conta.relatorios(string, logada, mapContas, mapContasNumeroConta, numeroAgencias);

				// realizar outra operacao ou sair
				if (verificaSairDoPrimeiroMenu()) {
					System.out.println("Obrigado por utilizar o Internet Banking.");
					sair = true;
				}
				break;

			// para sair
			case 3:
				try {
					telaSaida.obraDeArte();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				sair = true;
				break;

			default:
				System.out.println("Operação inválida.\n");
				break;
			}
		} while (sair != true);

	}

	// realizar outra operacao ou sair
	public static boolean verificaSairDoPrimeiroMenu() {
		int input1;
		do {
			System.out.println("Deseja realizar outra operação? Digite 1 para SIM e 2 para NÃO\n");
			input1 = new Scanner(System.in).nextInt();
		} while (input1 < 1 || input1 > 2);

		if (input1 == 2) {
			return true;
		} else {

			return false;
		}

	}

}
