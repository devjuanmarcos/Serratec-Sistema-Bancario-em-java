package appInternetBanking;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;



public abstract class Conta implements Comparable<Conta> {
	
	String TipoConta;
	String CPFDoTitular;
	String nome;
	String tipoUsuario;
	int numeroDaConta;
	int agencia;
	double saldo;
	public double tarifacao;
	DecimalFormat df = new DecimalFormat("#.##");

	
	public int compareTo(Conta a) {
		return nome.compareTo(a.getNome());
	}
	
	protected Conta(String TipoConta, String cPF, String nome, String tipoUsuario, int numeroDaConta, int agencia,
			double saldo, double tarifacao) {
		this.TipoConta = TipoConta;
		this.CPFDoTitular = cPF;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
		this.numeroDaConta = numeroDaConta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.tarifacao = tarifacao;
	}

	@Override
	public String toString() {
		return "[TipoConta=" + TipoConta + ", CPFDoTitular=" + CPFDoTitular + ", numeroDaConta=" + numeroDaConta
				+ ", agencia=" + agencia + ", saldo=" + saldo + "]";
	}

	public static void movimentacoes(String string, Conta logada, Map<String, Conta> mapContas,
			Map<Integer, Conta> mapContasNumeroConta) throws IOException {
		boolean sair = false;

		do {
			// Menu inicial
			System.out.println("Escolha a operação desejada:"
					+ "\n1- Saque\n2- Depósito\n3- Transferência\n4- Seguro de Vida\n5- Voltar ");
			Scanner scan = new Scanner(System.in);
			int op = scan.nextInt();

			switch (op) {

			// para saque
			case 1:
				try {
					System.out.println("Digite o Valor do saque:");
					double valorASacar = new Scanner(System.in).nextDouble();
					// saque(valorASacar);

					if (verificaValor(valorASacar) && valorASacar <= logada.getSaldo()
							&& logada.getTipoConta().equals("Corrente")) {

						saque(valorASacar, logada);
						logada.saldo = logada.saldo - 0.1;
						logada.tarifacao += 0.1;
						System.out.println(
								"Taxa de saque: R$ " + appUtil.formataMoeda(logada.tarifacao)+"\nSaldo restante: R$ " + logada.saldo);
						RelatorioFinal2.rfSaques(logada.nome, valorASacar, logada.saldo);
						RelatorioFinal2.nfSaque(logada.nome, logada.CPFDoTitular, valorASacar);
						System.out
								.println("\nRetire sua nota fiscal na boca do caixa antes que o vento leve embora.\n");

					} else if (verificaValor(valorASacar) && valorASacar <= logada.getSaldo()
							&& logada.getTipoConta().equals("Poupanca")) {

						saque(valorASacar, logada);

						RelatorioFinal2.rfSaques(logada.nome, valorASacar, logada.saldo);

					}

					else {
						System.out.println("Não foi possível realizar a operação. Digite um valor válido.\n");
					}

					// realizar outra operacao ou sair
					if (verificaSairDoSegundoMenu()) {

						System.out.println("Obrigado por utilizar o Internet Banking.");

						sair = true;
						System.exit(0);
					}
					break;
				} catch (Exception ee) {
					System.out.println("Você inseriu um caracter inválido. Por favor, tente novamente.\n ");
					break;
				}
				// para deposito
			case 2:
				try {
					System.out.println("Digite o valor a depositar em sua conta");
					double valorADepositar = new Scanner(System.in).nextDouble();

					if (verificaValor(valorADepositar) && logada.getTipoConta().equals("Corrente")) {
						deposito(valorADepositar, logada);
						logada.saldo = logada.saldo - 0.1;
						logada.tarifacao += 0.1;
						System.out.println("Taxa de depósito: R$ " +appUtil.formataMoeda(logada.tarifacao));
						String.format("%.2f", logada.saldo);
						RelatorioFinal2.rfDepositos(logada.nome, valorADepositar, logada.saldo);
						RelatorioFinal2.nfDeposito(logada.nome, logada.CPFDoTitular, valorADepositar);
					} else if (verificaValor(valorADepositar) && logada.getTipoConta().equals("Poupanca")) {
						deposito(valorADepositar, logada);
						RelatorioFinal2.rfDepositos(logada.nome, valorADepositar, logada.saldo);
						RelatorioFinal2.nfDeposito(logada.nome, logada.CPFDoTitular, valorADepositar);
					}

					else {
						System.out.println("Não foi possível realizar a operação. Digite um valor válido.\n");
					}

					// realizar outra operacao ou sair
					if (verificaSairDoSegundoMenu()) {
						System.out.println("Obrigado por utilizar o Internet Banking.");
						sair = true;
						System.exit(0);

					}

					break;
				} catch (Exception ee) {
					System.out.println("Você inseriu um caracter inválido. Por favor, tente novamente.\n");
					break;
				}
				// para Transferencia
			case 3:
				try {
					System.out.println("Digite a conta de destino");
					int contaDestino = new Scanner(System.in).nextInt();

					if (mapContasNumeroConta.get(contaDestino) != null) {
						Conta temporaria = mapContasNumeroConta.get(contaDestino);
						System.out.println("É cobrado uma taxa de R$ 0,20 para transferências bancárias. \nPara continuar digite o valor a transferir");
						double valorATransferir = new Scanner(System.in).nextDouble();
//						System.out.println("Transferencia efetuada com sucesso. ");

						if (verificaValor(valorATransferir) && valorATransferir <= logada.getSaldo()
								&& logada.getTipoConta().equals("Corrente")) {

							logada.saldo -= valorATransferir;
							temporaria.saldo += valorATransferir;

							logada.saldo = logada.saldo - 0.2;
							logada.tarifacao += 0.2;
							System.out.println("Transferencia efetuada com sucesso. ");
							RelatorioFinal2.rfTransferencias(logada.nome, valorATransferir, contaDestino);
							// Thread.sleep(120);
							if (verificaSairDoSegundoMenu()) {
								System.out.println("Obrigado por utilizar o Internet Banking.");
								sair = true;
								System.exit(0);
							}
							break;

						} else if (verificaValor(valorATransferir) && valorATransferir <= logada.getSaldo()
								&& logada.getTipoConta().equals("Poupanca")) {
							logada.saldo -= valorATransferir;
							temporaria.saldo += valorATransferir;
							RelatorioFinal2.rfTransferencias(logada.nome, valorATransferir, contaDestino);
						} else {
							System.out.println("Não foi possível realizar a operação. Digite um valor válido.\n");
						}

					}

					break;
				} catch (Exception ee) {
					System.out.println("Você inseriu um caracter inválido. Por favor, tente novamente.\n");
					break;
				}

				// seguro de vida
			case 4:

				System.out.println("Digite o valor que deseja assegurar: ");
				double contaSeguro = new Scanner(System.in).nextInt();

				if (logada.getSaldo() >= contaSeguro * 0.2) {
					double valorTributacao = SeguroDeVida.Seguro(contaSeguro);
					System.out.printf("Valor assegurado: R$ %.2f\n", contaSeguro);
					System.out.println(
							"Será cobrada uma taxa de 20% para realizar essa operação.\nValor da tributação: R$ "
									+ valorTributacao);
					System.out.println("\nDeseja continuar com a solicitação?\n1- SIM\n2- NÃO");
					int vS = scan.nextInt();

					switch (vS) {
					case 1:
						logada.saldo -= SeguroDeVida.Seguro(contaSeguro);
						System.out.println("\nParabéns! Você acaba de contratar nosso serviço de seguro!\n");
						RelatorioFinal2.nfSeguro(logada.nome, logada.CPFDoTitular, valorTributacao);
						RelatorioFinal2.rfSeguroDeVida(logada.nome, contaSeguro);

					case 2:
						break;

					}
					break;
				}

				else {
					System.out.println("Você não tem saldo suficiente para completar essa solicitação.\n");
				}


				// realizar outra operacao ou sair
				if (verificaSairDoSegundoMenu()) {

					System.out.println("Obrigado por utilizar o Internet Banking.");

					sair = true;
					System.exit(0);
				}

				// para Voltar
			case 5:
				sair = true;

				break;

			}
		} while (!sair);
	}

	protected static void relatorios(String string, Conta logada, Map<String, Conta> mapContas,
			Map<Integer, Conta> mapContasNumeroConta, List<Object> numeroAgencias) {

		if (logada.getTipoUsuario().equals("Cliente")) {
			if (logada.getTipoConta().equals("Corrente")) {
				System.out.println("Escolha a operação desejada:"
						+ "\n1- Saldo\n2- Relatório de Tarifação da Conta Corrente\n3- Informações sobre tarifas ");
				Scanner scan = new Scanner(System.in);
				int op = scan.nextInt();

				switch (op) {
				case 1:
					System.out.println(appUtil.formataMoeda(logada.getSaldo()));
					break;
				case 2:
					System.out.println(appUtil.formataMoeda(logada.getTarifacao()));
					break;

				case 3:
					System.out.println("Para cada saque será cobrado o valor de R$0.10 (dez centavos);\n"
							+ "Para cada depósito será cobrado o valor de R$0.10 (dez centavos);\n"
							+ "Para cada transferência será cobrado o valor de R$0.20 (vinte centavos)+"
							+ "Valores válidos para conta corrente. Conta poupança não será tarifada");
					break;
				default:
					break;
				}
			} else if (logada.getTipoConta().equals("Poupanca")) {
				System.out.println(
						"Escolha a operação desejada:" + "\n1- Saldo\n2- Relatório de de Rendimentos da Poupança");
				Scanner scan = new Scanner(System.in);
				int op = scan.nextInt();

				switch (op) {
				case 1:
					System.out.println(appUtil.formataMoeda(logada.getSaldo()));
					break;
				case 2:
					relRendimentosPoup(logada);
					break;

				default:
					break;
				}
			}
		}

		if (logada.getTipoUsuario().equals("Gerente")) {
			if (logada.getTipoConta().equals("Corrente")) {
				System.out.println("Escolha a operação desejada:"
						+ "\n1- Saldo\n2- Relatório de Tarifação da Conta Corrente\n3- Informações sobre tarifas "
						+ "\n4- Relatório de contas gerenciadas" + "\n5- Relatório de Rendimentos da Poupança");
				Scanner scan = new Scanner(System.in);
				int op = scan.nextInt();

				switch (op) {
				case 1:
					System.out.println(appUtil.formataMoeda(logada.getSaldo()));
					break;
				case 2:
					System.out.println(appUtil.formataMoeda(logada.getTarifacao()));
					break;

				case 3:
					System.out.println("Para cada saque será cobrado o valor de R$0.10 (dez centavos);\n"
							+ "Para cada depósito será cobrado o valor de R$0.10 (dez centavos);\n"
							+ "Para cada transferência será cobrado o valor de R$0.20 (vinte centavos)+"
							+ "Valores válidos para conta corrente. Conta poupança não será tarifada");
					break;

				case 4:
					System.out.println("Número de contas gerenciadas na mesma agência:");
					int contador = 0;

					for (Object object : numeroAgencias) {
						Conta temp = (Conta) object;
						if (temp.getAgencia() == logada.getAgencia()) {
							contador++;
						}
					}

					System.out.println(appUtil.formataMoeda(contador));

					break;

				case 5:
					relRendimentosPoup(logada);
					break;
				default:
					break;
				}
			} else if (logada.getTipoConta().equals("Poupanca")) {
				System.out
						.println("Escolha a operação desejada:" + "\n1- Saldo\n2- Relatório de Rendimentos da Poupança"
								+ "\n3- Relatório de contas gerenciadas");
				Scanner scan = new Scanner(System.in);
				int op = scan.nextInt();

				switch (op) {
				case 1:
					System.out.println(appUtil.formataMoeda(logada.getSaldo()));
					break;
				case 2:
					relRendimentosPoup(logada);
					break;
				case 3:
					System.out.println("Número de contas gerenciadas na mesma agência:");
					int contador = 0;

					for (Object object : numeroAgencias) {
						Conta temp = (Conta) object;
						if (temp.getAgencia() == logada.getAgencia()) {
							contador++;
						}
					}

					System.out.println(appUtil.formataMoeda(contador));
					break;
				default:
					break;
				}
			}
		}

		if (logada.getTipoUsuario().equals("Diretor")) {
			if (logada.getTipoConta().equals("Corrente")) {
				System.out.println("Escolha a operação desejada:"
						+ "\n1- Saldo\n2- Relatório de Tarifação da Conta Corrente\n3- Informações sobre tarifas "
						+ "\n4- Informações dos Clientes do Sistema");
				Scanner scan = new Scanner(System.in);
				int op = scan.nextInt();

				switch (op) {
				case 1:
					System.out.println(appUtil.formataMoeda(logada.getSaldo()));
					break;
				case 2:
					System.out.println(appUtil.formataMoeda(logada.getTarifacao()));
					break;

				case 3:
					System.out.println("Para cada saque será cobrado o valor de R$0.10 (dez centavos);\n"
							+ "Para cada depósito será cobrado o valor de R$0.10 (dez centavos);\n"
							+ "Para cada transferência será cobrado o valor de R$0.20 (vinte centavos)+"
							+ "Valores válidos para conta corrente. Conta poupança não será tarifada");
					break;

				case 4:
					System.out.println("Informações dos Clientes do Sistema:");
					List lista = new ArrayList();
					for (Object object : numeroAgencias) {
						Conta temp = (Conta) object;
						lista.add(temp);
						
					}
					Collections.sort(lista);
					for(Object temp: lista) {
						Conta conta = (Conta)(temp);
						System.out.println(conta.nome);
					}
					
					
					break;
				default:
					break;
				}
			} else if (logada.getTipoConta().equals("Poupanca")) {
				System.out.println(
						"Escolha a operação desejada:" + "\n1- Saldo\n2- Relatório de de Rendimentos da Poupança"
								+ "\n3- Informações dos Clientes do Sistema");
				Scanner scan = new Scanner(System.in);
				int op = scan.nextInt();

				switch (op) {
				case 1:
					System.out.println(appUtil.formataMoeda(logada.getSaldo()));
					break;
				case 2:
					relRendimentosPoup(logada);
					break;
				case 3:
					System.out.println("Informações dos Clientes do Sistema:");
					
					List lista = new ArrayList();
					for (Object object : numeroAgencias) {
						Conta temp = (Conta) object;
						lista.add(temp);
						
					}
					Collections.sort(lista);
					for(Object temp: lista) {
						Conta conta = (Conta)(temp);
						System.out.println(conta.nome);
					}
					break;

				default:
					break;
				}
			}
		}

		if (logada.getTipoUsuario().equals("Presidente")) {
			double capitalTotal = 0;

			if (logada.getTipoConta().equals("Corrente")) {
				System.out.println("Escolha a operação desejada:"
						+ "\n1- Saldo\n2- Relatório de Tarifação da Conta Corrente\n3- Informações sobre tarifas "
						+ "\n4- Informações dos Clientes do Sistema\n5- Relatório - Capital Total do Banco");
				Scanner scan = new Scanner(System.in);
				int op = scan.nextInt();

				switch (op) {
				case 1:
					System.out.println(appUtil.formataMoeda(logada.getSaldo()));
					break;
				case 2:
					System.out.println(appUtil.formataMoeda(logada.getTarifacao()));
					break;

				case 3:
					System.out.println("Para cada saque será cobrado o valor de R$0.10 (dez centavos);\n"
							+ "Para cada depósito será cobrado o valor de R$0.10 (dez centavos);\n"
							+ "Para cada transferência será cobrado o valor de R$0.20 (vinte centavos);\n"
							+ "Valores válidos para conta corrente. Conta poupança não será tarifada");
					break;

				case 4:
					System.out.println("Informações dos Clientes do Sistema:");
					for (Object object : numeroAgencias) {
						Conta temp = (Conta) object;
						System.out.println(temp.getNome() + temp.getCPFDoTitular() + temp.getAgencia());
					}
					break;

				case 5:
					System.out.println("Capital Total =");
					for (Object object : numeroAgencias) {
						Conta temp = (Conta) object;
						capitalTotal += temp.getSaldo() + temp.getTarifacao();
					}
					System.out.println(appUtil.formataMoeda(capitalTotal));

					break;
				default:
					break;
				}
			} else if (logada.getTipoConta().equals("Poupanca")) {
				System.out.println(
						"Escolha a operação desejada:" + "\n1- Saldo\n2- Relatório de de Rendimentos da Poupança"
								+ "\n3- Informações dos Clientes do Sistema\n4- Relatório - Capital Total do Banco");
				Scanner scan = new Scanner(System.in);
				int op = scan.nextInt();

				switch (op) {
				case 1:
					System.out.println(appUtil.formataMoeda(logada.getSaldo()));
					;
					break;
				case 2:
					relRendimentosPoup(logada);
					break;
				case 3:
					System.out.println("Informações dos Clientes do Sistema:");
					for (Object object : numeroAgencias) {
						Conta temp = (Conta) object;
						System.out.println(temp.getNome() + temp.getCPFDoTitular() + temp.getAgencia());
					}
					break;
				case 4:
					System.out.println("Capital Total =");
					for (Object object : numeroAgencias) {
						Conta temp = (Conta) object;
						capitalTotal += temp.getSaldo() + temp.getTarifacao();
					}
					System.out.println(appUtil.formataMoeda(capitalTotal));

					break;

				default:
					break;
				}
			}
		}

	}

	// realizar outra operacao ou voltar
	public static boolean verificaSairDoSegundoMenu() {

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

	public static void saque(double valorASacar, Conta logada) throws IOException {

		logada.saldo -= valorASacar;
//		relatorioFinal().print(logada.saldo);
//		relatorioFinal().close();
	}

	public static void deposito(double valorASacar, Conta logada) throws IOException {

		logada.saldo += valorASacar;
//		relatorioFinal().print(logada.saldo);
//		relatorioFinal().close();
	}

	public static boolean verificaValor(double valor) {
		if (valor > 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public static void relRendimentosPoup(Conta logada) {
		try {
			System.out.println("Insira quanto gostaria de investir: ");
			Scanner vlr = new Scanner(System.in);
			double op2 = vlr.nextDouble();
			System.out.println("Simule no prazo desejado:" + "\n1- 3 meses\n2- 6 meses\n3- 1 ano");
			Scanner scan = new Scanner(System.in);
			int op = scan.nextInt();

			switch (op) {

			case 1:
				System.out.println(appUtil.formataMoeda(op2 += op2 * 0.025));
				break;
			case 2:
				System.out.println(appUtil.formataMoeda(op2 += op2 * 0.05));
				break;
			case 3:
				System.out.println(appUtil.formataMoeda(op2 += op2 * 0.1));
				break;

			}
		} catch (InputMismatchException e) {
			System.out.println("Erro tal");

		}

	}
	

	public String getCPFDoTitular() {
		return CPFDoTitular;
	}

	public String getNome() {
		return nome;
	}

	public String getTipoConta() {
		return TipoConta;
	}

	public int getNumeroDaConta() {
		return numeroDaConta;
	}

	public int getAgencia() {
		return agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public double getTarifacao() {
		return tarifacao;
	}
	
}
