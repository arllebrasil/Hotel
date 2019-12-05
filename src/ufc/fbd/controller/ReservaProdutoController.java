package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import ufc.fbd.DAO.HospedeReservaDAO;
import ufc.fbd.DAO.ProdutoDAO;
import ufc.fbd.DAO.ReservaDAO;
import ufc.fbd.DAO.ReservaProdutoDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.HospedeReserva;
import ufc.fbd.model.Produto;
import ufc.fbd.model.Reserva;
import ufc.fbd.model.ReservaCompleta;
import ufc.fbd.model.ReservaProduto;

public class ReservaProdutoController {
	private ReservaProdutoDAO reservaProDao = null;
	
	public ReservaProdutoController() {
		this.reservaProDao = new ReservaProdutoDAO(new FbdConnection());
	}
	public void start() {
		boolean continuar = true;
		do {
			System.out.println("################                    Controle de vendas de Produtos                    ################ \n"
					+ "0- Listar Vendas de Produtos/Hospede, 1- Listar vendas de produtos, 2- Registrar uma vendas de produtos, 3- Alterar vendas de produtos,"
					+ "4- Remover venda do sistema (Cancelar venda),\n5- Consutar Reservas, 6- Consutar Produtos , ENTER para SAIR !....");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			switch (option) {
			case "0":
				System.out.println("###########        Listar Vendas de Produtos p/Hospede6        ###########\n");
				this.option0();
				break;
			case "1":
				System.out.println("###########        Listar Vendas de Produtos        ###########\n");
				this.option1();
				break;
			case "2":
				System.out.println("###########        Registrar Vendas de Produtos        ###########\n");
				this.option2();
				break;
			case "3":
				System.out.println("###########        Alterar Vendas de Produtos        ###########\n");
				this.option3();
				break;
			case "4":
				System.out.println("###########        Remover Vendas de Produtos        ###########\n");
				this.option4();
				break;
			case "5":
				System.out.println("###########               Reservas               ###########\n");
				this.option5();
				break;
			case "6":
				System.out.println("###########               Produtos               ###########\n");
				this.option6();
				break;
			case "":
				continuar = false;
				System.out.println("################                     .................                    ################\n");
				break;
			default:
				break;
			}
		} while (continuar);
	}
	private void option0() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Informe os dados do hospede (CPF,Nome)");
		String cpf = scanner.nextLine();
		Scanner scanner2 = new Scanner(System.in);
		String nome = scanner2.nextLine();
		
		
		double total = 0;
		ArrayList<ReservaProduto> allReservaPro = reservaProDao.show(cpf,nome);
		
		for (ReservaProduto reservaProduto : allReservaPro) {
			System.out.println(reservaProduto.toString());
			total += reservaProduto.getPreco();
		}
		System.out.println("\nNumero de compras: "+ allReservaPro.size()+"\nTotal: "+total);
	}
	public void option1() {
		// TODO Auto-generated method stub
		ArrayList<ReservaProduto> allReservaPro = reservaProDao.find();
		for (ReservaProduto reservaProduto : allReservaPro) {
			System.out.println(reservaProduto.toString());
		}
	}
	public void option2() {
		// TODO Auto-generated method stub
		Input input = new Input();
		System.out.println("Informe Informe os dados da venda!!!");
		ReservaProduto reservaProduto = input.createReservaProduto();
		
		ReservaCompleta reserva = new ReservaDAO(new FbdConnection()).findOne(reservaProduto.getIdReserva(),reservaProduto.getCpfHospede());
		Produto produto = new ProdutoDAO(new FbdConnection()).findOne(reservaProduto.getIdProduto());
		
		if(reserva != null && produto != null) {
			reservaProduto.setCpfHospede(reserva.getCpfHospede());
			reservaProduto.setHospede(reserva.getNome());
			reservaProduto.setProduto(produto.getNome());
			reservaProduto.setPreco(produto.getPreco());
			
			System.out.println(reservaProduto.toString() +" Confirmar venda S/N ?");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			if(option.equals("s") || option.equals("S")) {
				int response = reservaProDao.create(reservaProduto);
				System.out.println((response >= 1)?response+" vendas de produtos confirmadas....":"A operação foi cancelada....");
			}		
		}else {
			System.out.println((reserva == null)?
				"Reserva de ID = "+reservaProduto.getIdReserva()+" e Cpf_Hospede =" +reservaProduto.getHospede()+" não foi encontrada. Operação cancelada...":
				"Produto de ID = "+reservaProduto.getIdProduto()+" não foi encontrado. Operação cancelada...");
		}
	}
	public void option3() {
				Input input = new Input();
				System.out.println("Informe Informe os dados da venda que deseja alterar !!!");
				
				ReservaProduto oldReservaPro = input.createReservaProduto();
				ReservaProduto oldCopia = oldReservaPro;
				oldReservaPro = reservaProDao.findOne(oldReservaPro);
				
				if(oldReservaPro != null) {
					System.out.println("Informe Informe os novos dados da venda para alteração !!!");
					ReservaProduto newReservaPro =  input.createReservaProduto();;
					
					System.out.println(newReservaPro.toString() +" Confirmar venda S/N ?");
					Scanner scanner = new Scanner(System.in);
					String option = scanner.nextLine();
					
					if(option.equals("s") || option.equals("S")) {
						int response = reservaProDao.update(oldReservaPro, newReservaPro);
						System.out.println((response >= 1)?response+"alterações de vendas confirmadas....":"A operação foi cancelada....");
					}		
				}else {
					System.out.println("nenhuma venda "+oldCopia.toString()+", foi encontrada. Operação cancelada...");
				}
	}
	public void option4() {
		Input input = new Input();
		System.out.println("Informe os dados da venda que deseja alterar !!!");
				
		ReservaProduto oldReservaPro = input.createReservaProduto();
		ReservaProduto oldCopia = oldReservaPro;
		oldReservaPro = reservaProDao.findOne(oldReservaPro);
				
		if(oldReservaPro != null) {
			System.out.println(oldReservaPro.toString()+" encontrado!\nInforme os novos dados da venda para alteração !!!");
			ReservaProduto newReservaPro =  input.createReservaProduto();;
					
			if(newReservaPro != null) {
			ReservaCompleta reserva = new ReservaDAO(new FbdConnection()).findOne(newReservaPro.getIdReserva(),newReservaPro.getCpfHospede());
			Produto produto = new ProdutoDAO(new FbdConnection()).findOne(newReservaPro.getIdProduto());
						
				if(reserva != null && produto != null){
					newReservaPro.setCpfHospede(reserva.getCpfHospede());
					newReservaPro.setHospede(reserva.getNome());
					newReservaPro.setProduto(produto.getNome());
					newReservaPro.setPreco(produto.getPreco());
							
					System.out.println(newReservaPro.toString() +"\nConfirmar alteração da venda S/N ?");
					Scanner scanner = new Scanner(System.in);
					String option = scanner.nextLine();
							
					if(option.equals("s") || option.equals("S")) {
						int response = reservaProDao.update(oldReservaPro, newReservaPro);
						System.out.println((response >= 1)?response+"alterações de vendas confirmadas....":"A operação foi cancelada....");
					}else {
						System.out.println("Operação Cancelada...");
					}
				}else {
					System.out.println((reserva == null)?
					"Reserva de ID = "+newReservaPro.getIdReserva()+" e Cpf_Hospede =" +newReservaPro.getCpfHospede()+" não foi encontrada. Operação cancelada...":
					"Produto de ID = "+newReservaPro.getIdProduto()+" não foi encontrado. Operação cancelada...");
				}
			}else {
				System.out.println("Operação Cancelada...");
			}
		}else {
			System.out.println("A venda não foi encontrada. Operação cancelda...");
		}	
	}
	public void option5() {
		new ReservaController().option1();
	}
	public void option6() {
		new ProdutoController().option1();
	}
}
