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

	/*
	 * @Override public void saca(double valor) { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void deposito(double valor) { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 * 
	 * @Override public void transferencia(Conta c, double valor) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

}