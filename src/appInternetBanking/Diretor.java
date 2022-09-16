package appInternetBanking;

public class Diretor extends Usuario {

	String cargo = "Diretor";

	public Diretor(String tipoUsuario, String cPF, String senha, String nome, String tipoConta) {
		super(tipoUsuario, cPF, senha, nome, tipoConta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Diretor [" + (cargo != null ? "cargo=" + cargo : "") + "]";
	}


}