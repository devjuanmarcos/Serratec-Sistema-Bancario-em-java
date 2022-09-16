package appInternetBanking;

import java.util.Scanner;

public class Usuario {

	String tipoUsuario;
	String CPF;
	String senha;
	String nome;
	String tipoConta;

	public Usuario(String tipoUsuario, String cPF, String senha, String nome, String tipoConta) {

		this.tipoUsuario = tipoUsuario;
		this.CPF = cPF;
		this.senha = senha;
		this.nome = nome;
		this.tipoConta = tipoConta;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public String getCPF() {
		return CPF;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public String getTipoConta() {
		return tipoConta;
	}
	


}