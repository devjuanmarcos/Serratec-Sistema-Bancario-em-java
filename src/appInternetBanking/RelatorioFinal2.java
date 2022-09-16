package appInternetBanking;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RelatorioFinal2 extends Conta {
	RelatorioFinal2(String TipoConta, 
	
		String CPFDoTitular, String nome, String tipoUsuario, int numeroDaConta,
			int agencia, double saldo, double tarifacao) {
		super(TipoConta, CPFDoTitular, nome, tipoUsuario, numeroDaConta, agencia, saldo, tarifacao);

	}


	// digitador.txt para ocasiões de DEPOSITOS
	public static PrintWriter rfDepositos(String nome, double valor, double valor2) throws IOException {
		appUtil.path(1);
		
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(appUtil.path(1));
		String.format("%.2f",valor2);
		gravarArq.println(data + " - " + "Usuário " + nome + " efetuou um depósito de R$ " + valor);
		gravarArq.close();
			
		return gravarArq;
	}
	// digitador.txt para ocasiões de SAQUES
	public static PrintWriter rfSaques(String nome, double valor, double valor2) throws IOException {
		appUtil.path(1);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(appUtil.path(1));
		String.format("%.2f", valor2);
		gravarArq.println("Usuário " + nome + " efetuou um saque de R$ " + valor + ", restando R$ " + valor2
				+ " na conta. " + "- " + data);
		gravarArq.close();

		return gravarArq;
	}
	// digitador.txt para ocasiões de TRANSFERENCIAS
	public static PrintWriter rfTransferencias(String nome, double valor, int destino) throws IOException {
		appUtil.path(1);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(appUtil.path(1));
		gravarArq.println("Usuário " + nome + " efetuou uma transferência de R$ " + valor + " para a conta " + destino
				+ "- " + data);
		gravarArq.close();

		return gravarArq;
	}
	
	public static PrintWriter rfSeguroDeVida(String nome, double valor) throws IOException {
		appUtil.path(1);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(appUtil.path(1));
		gravarArq.println("Usuário " + nome + " assegurou o valor de R$ " + valor 
				+ "- " + data);
		gravarArq.close();

		return gravarArq;
	}
	
	// digitador.txt para ocasiões de NF SAQUE
	public static PrintWriter nfSaque(String nome, String cPFDoTitular, double valor) throws IOException {
		
		appUtil.path(2);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(appUtil.path(2));
//		String.format("%.2f", destino);
		gravarArq.printf("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-----------------------SAQUE-----------------------" + "\nUsuário: " + nome + ", CPF: " + cPFDoTitular
				+ "- " + data + "\nValor do saque: " + valor);
		gravarArq.close();

		return gravarArq;
	}
	// digitador.txt para ocasiões de NF DEPOSITO
	public static PrintWriter nfDeposito(String nome, String cPFDoTitular, double valor) throws IOException {
		appUtil.path(2);

		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(appUtil.path(2));
//		String.format("%.2f", destino);
		gravarArq.printf("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-----------------------DEPÓSITO-----------------------" + "\nUsuário: " + nome + ", CPF: " + cPFDoTitular
				+ "- " + data + "\nValor do depósito: " + valor);
		gravarArq.close();
		return gravarArq;
	}
	// digitador.txt para ocasiões de NF SEGURO
	public static PrintWriter nfSeguro(String nome, String cPFDoTitular, double valor) throws IOException {
		appUtil.path(2);

		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(appUtil.path(2));
//		String.format("%.2f", destino);
		gravarArq.print("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-----------------------Seguro de Vida-----------------------" +"\n" + data + " - Usuário: " + nome
				+ ", CPF: " + cPFDoTitular +"\nfechou contrato do serviço Seguro de vida, pagando uma taxa tributação de R$ " + valor + " (20% do valor solicitade a assegurar)");
		gravarArq.close();

		return gravarArq;
	}

}