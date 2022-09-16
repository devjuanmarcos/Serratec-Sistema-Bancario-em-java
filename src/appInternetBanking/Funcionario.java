package appInternetBanking;

public abstract class Funcionario extends Usuario {

	public Funcionario(String tipoUsuario, String CPF, String senha, String nome, String tipoConta) {
		super(tipoUsuario, CPF, senha, nome, tipoConta);
	}

}