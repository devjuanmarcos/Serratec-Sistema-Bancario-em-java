package appInternetBanking;

public class Cliente extends Usuario {

	public Cliente(String tipoUsuario, String cPF, String senha, String nome, String TipoConta) {
		super(tipoUsuario, cPF, senha, nome, TipoConta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cliente [" + (tipoUsuario != null ? "tipoUsuario=" + tipoUsuario + ", " : "") + "CPF=" + CPF
				+ ", senha=" + senha + ", " + (nome != null ? "nome=" + nome + ", " : "")
				+ (tipoConta != null ? "tipoConta=" + tipoConta : "") + "]";
	}

}