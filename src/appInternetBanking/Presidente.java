package appInternetBanking;

public class Presidente extends Usuario {

	String cargo = "Presidente";

	public Presidente(String tipoUsuario, String cPF, String senha, String nome, String tipoConta) {
		super(tipoUsuario, cPF, senha, nome, tipoConta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Presidente [" + (cargo != null ? "cargo=" + cargo + ", " : "")
				+ (tipoUsuario != null ? "tipoUsuario=" + tipoUsuario + ", " : "") + "CPF=" + CPF + ", senha=" + senha
				+ ", " + (nome != null ? "nome=" + nome + ", " : "")
				+ (tipoConta != null ? "tipoConta=" + tipoConta : "") + "]";
	}

}