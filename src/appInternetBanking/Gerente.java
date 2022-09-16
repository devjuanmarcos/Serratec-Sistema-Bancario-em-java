package appInternetBanking;

public class Gerente extends Usuario {

	String cargo = "Gerente";

	int agencia;

	public Gerente(String tipoUsuario, String cPF, String senha, String nome, String tipoConta, int agencia) {
		super(tipoUsuario, cPF, senha, nome, tipoConta);
		this.agencia = agencia;
	}

	@Override
	public String toString() {
		return "[Cargo = " + cargo + ", CPF = " + CPF + ", nome = " + nome + ", agencia=" + agencia + "]";
	}

	

}