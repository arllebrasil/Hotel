package ufc.fbd.view;

import java.util.Scanner;

public class Index {
	public void start() {
		boolean executar = true;
		do {
			System.out.println("Digite o menu desejado ( 1-Hospedes, 2-Reservas, 3-Quartos, 4-Produtos, 5-Serviços)");
			Scanner input = new Scanner(System.in);
			String opt =  input.nextLine();
			
			switch (opt) {
			case "1":
				
				break;
			case "2":
				
				break;
			case "3":
				
				break;
			case "4":
				
				break;
			case "5":
				
				break;
			default:
				break;
			}
		}while(executar);
	}
}
