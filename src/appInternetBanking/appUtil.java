package appInternetBanking;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class appUtil {

	public static String formataMoeda(double valor) {

		DecimalFormat df = new DecimalFormat("#.##");
		String formatted = df.format(valor);

		return formatted;
	}
	
	public static FileWriter path(int choose) throws IOException {
		// 1 = path alterações
		// 2 = path notafiscal
		if(choose == 1) {
			FileWriter arq1 = new FileWriter(
					"E:\\Meus Documentos\\Área de Trabalho\\Workspace\\SISTEMA-BANCARIO---JAVA1-POO---RESIDENCIA-DE-SOFTWARE\\Projeto Banco\\alteracoes.txt",
					true);
			return arq1;
		}
		if(choose==2){
			FileWriter arq2 = new FileWriter(
					"E:\\Meus Documentos\\Área de Trabalho\\Workspace\\SISTEMA-BANCARIO---JAVA1-POO---RESIDENCIA-DE-SOFTWARE\\Projeto Banco\\nota fiscal.txt",
					false);
			return arq2;
		}
		return null;
		
		
	}


	

}