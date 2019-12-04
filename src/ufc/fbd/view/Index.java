package ufc.fbd.view;

import java.util.Scanner;

import ufc.fbd.controller.HospedeController;
import ufc.fbd.controller.ProdutoController;
import ufc.fbd.controller.QuartoController;
import ufc.fbd.controller.ReservaController;
import ufc.fbd.controller.ReservaProdutoController;
import ufc.fbd.controller.ReservaServicoController;
import ufc.fbd.controller.ServicoController;

public class Index {
	public void start() {
		boolean continuar = true;
		do {
			System.out.println(
					" 1-Hospedes, 2-Reservas, 3-Quartos, 4-Produtos, 5-Serviços\n"
					+ "6- Vendas de Produtos, 7- Vendas de Serviços, ENTER para Sair");
			Scanner input = new Scanner(System.in);
			String opt =  input.nextLine();
			
			switch (opt) {
			case "1":
				HospedeController hospedeCtr = new HospedeController();
				hospedeCtr.start();
				break;
			case "2":
				ReservaController reservaCtr = new ReservaController();
				reservaCtr.start();
				break;
			case "3":
				QuartoController quartoCtr = new QuartoController();
				quartoCtr.start();
				break;
			case "4":
				ProdutoController produtoCtr = new ProdutoController();
				produtoCtr.start();
				break;
			case "5":
				ServicoController servicoCtr = new ServicoController();
				servicoCtr.start();
				break;
			case "6":
				ReservaProdutoController vendaProCtr = new ReservaProdutoController();
				vendaProCtr.start();
				break;
			case "7":
				ReservaServicoController vendaServCtr = new ReservaServicoController();
				vendaServCtr.start();
				break;
			case "":
				continuar = false;
				break;
			default:
				break;
			}
		}while(continuar);
	}
}
