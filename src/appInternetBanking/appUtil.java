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
		
		String path = "E:\\Meus Documentos\\Área de Trabalho\\Workspace\\Banco\\SISTEMA-BANCARIO--POO-RESIDENCIA-SOFTWARE\\";
		// 1 = path alterações
		// 2 = path notafiscal
		if(choose == 1) {
			FileWriter arq1 = new FileWriter(
					path + "alteracoes.txt",
					true);
			return arq1;
		}
		if(choose==2){
			FileWriter arq2 = new FileWriter(
					path + "nota fiscal.txt",
					false);
			return arq2;
		}
		return null;
		
		
	}


	

}