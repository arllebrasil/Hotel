package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import ufc.fbd.DAO.ReservaDAO;
import ufc.fbd.DAO.ReservaServicoDAO;
import ufc.fbd.DAO.ServicoDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.ReservaCompleta;
import ufc.fbd.model.ReservaServico;
import ufc.fbd.model.Servico;

public class ReservaServicoController {
	private ReservaServicoDAO reservaServDao = null;
	public ReservaServicoController() {
		this.reservaServDao = new ReservaServicoDAO(new FbdConnection());
	}
	public void start() {
		boolean continuar = true;
		do {
			System.out.println("################            Controle de vendas de Servi�os            ################ \n"
					+ "1- Listar vendas de Servi�os, 2- Registrar uma vendas de Servi�os, 3- Alterar vendas de Servi�os,"
					+ "4- Remover venda de Servi�os do sistema (Cancelar venda),\n5- Consutar Reservas, 6- Consutar Servi�os , ENTER para SAIR !....");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			switch (option) {
			case "1":
				System.out.println("###########        Listar Vendas de Servi�os        ###########\n");
				this.option1();
				break;
			case "2":
				System.out.println("###########        Registrar Vendas de Servi�os        ###########\n");
				this.option2();
				break;
			case "3":
				System.out.println("###########        Alterar Vendas de Servi�os        ###########\n");
				this.option3();
				break;
			case "4":
				System.out.println("###########        Remover Vendas de Servi�os        ###########\n");
				this.option4();
				break;
			case "5":
				System.out.println("###########               Reservas               ###########\n");
				this.option5();
				break;
			case "6":
				System.out.println("###########               Servi�os               ###########\n");
				this.option6();
				break;
			case "":
				System.out.println("###########        ..........................        ###########\n");
				continuar = false;
				break;
			default:
				break;
			}
		} while (continuar);
	}
	public void option1() {
		// TODO Auto-generated method stub
		ArrayList<ReservaServico> allReservaServ = reservaServDao.find();
		for (ReservaServico reservaServico : allReservaServ) {
			System.out.println(reservaServico.toString());
		}
	}
	public void option2() {
		// TODO Auto-generated method stub
		Input input = new Input();
		System.out.println("Informe os dados da venda!!!");
		ReservaServico reservaServico = input.createReservaServico();
		
		ReservaCompleta reserva = new ReservaDAO(new FbdConnection()).findOne(reservaServico.getIdReserva(),reservaServico.getCpfHospede());
		Servico servico = new ServicoDAO(new FbdConnection()).findOne(reservaServico.getIdServico());
		
		if(reserva != null && servico != null) {
			reservaServico.setCpfHospede(reserva.getCpfHospede());
			reservaServico.setHospede(reserva.getNome());
			reservaServico.setServico(servico.getDescricao());
			reservaServico.setPreco(servico.getPreco());
			
			System.out.println(reservaServico.toString() +" Confirmar venda S/N ?");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			if(option.equals("s") || option.equals("S")) {
				int response = reservaServDao.create(reservaServico);
				System.out.println((response >= 1)?response+" vendas de servi�os confirmadas....":"A opera��o foi cancelada....");
			}		
		}else {
			System.out.println((reserva == null)?
				"Reserva de ID = "+reservaServico.getIdReserva()+" e Cpf_Hospede =" +reservaServico.getHospede()+" n�o foi encontrada. Opera��o cancelada...":
				"Produto de ID = "+reservaServico.getIdServico()+" n�o foi encontrado. Opera��o cancelada...");
		}
	}
	public void option3() {
		Input input = new Input();
		System.out.println("Informe os dados da venda que deseja alterar !!!");
				
		ReservaServico oldReservaServ = input.createReservaServico();
		ReservaServico oldCopia = oldReservaServ;
		oldReservaServ = reservaServDao.findOne(oldReservaServ);
				
		if(oldReservaServ != null) {
			System.out.println(oldReservaServ.toString()+" encontrado!\nInforme os novos dados da venda para altera��o !!!");
			ReservaServico newReservaServ =  input.createReservaServico();;
					
			if(newReservaServ != null) {
			ReservaCompleta reserva = new ReservaDAO(new FbdConnection()).findOne(newReservaServ.getIdReserva(),newReservaServ.getCpfHospede());
			Servico servico = new ServicoDAO(new FbdConnection()).findOne(newReservaServ.getIdServico());
						
				if(reserva != null && servico != null){
					newReservaServ.setCpfHospede(reserva.getCpfHospede());
					newReservaServ.setHospede(reserva.getNome());
					newReservaServ.setServico(servico.getDescricao());
					newReservaServ.setPreco(servico.getPreco());
							
					System.out.println(newReservaServ.toString() +"\nConfirmar altera��o da venda S/N ?");
					Scanner scanner = new Scanner(System.in);
					String option = scanner.nextLine();
							
					if(option.equals("s") || option.equals("S")) {
						int response = reservaServDao.update(oldReservaServ, newReservaServ);
						System.out.println((response >= 1)?response+"altera��es de vendas confirmadas....":"A opera��o foi cancelada....");
					}else {
						System.out.println("Opera��o Cancelada...");
					}
				}else {
					System.out.println((reserva == null)?
					"Reserva de ID = "+newReservaServ.getIdReserva()+" e Cpf_Hospede =" +newReservaServ.getCpfHospede()+" n�o foi encontrada. Opera��o cancelada...":
					"Produto de ID = "+newReservaServ.getIdServico()+" n�o foi encontrado. Opera��o cancelada...");
				}
			}else {
				System.out.println("Opera��o Cancelada...");
			}
		}else {
			System.out.println("A venda n�o foi encontrada. Opera��o cancelda...");
		}
	}
	public void option4() {
		Input input = new Input();
		
		System.out.println("Informe Informe os dados da venda que deseja remover !!!");
		ReservaServico oldReservaServ = input.createReservaServico();
		ReservaServico oldCopia = oldReservaServ;
		oldReservaServ = reservaServDao.findOne(oldReservaServ);		
		
		if(oldReservaServ != null) {
			
			oldReservaServ = reservaServDao.findOne(oldReservaServ);
			if(oldReservaServ != null) {
				System.out.println(oldReservaServ.toString() +" Confirmar remo��o S/N ?");
				Scanner scanner = new Scanner(System.in);
				String option = scanner.nextLine();
				
				if(option.equals("s") || option.equals("S")) {
					int response = reservaServDao.delete(oldReservaServ);
					System.out.println((response >= 1)?response+" remo��es confirmadas....":"A opera��o foi cancelada....");
				}else {
					System.out.println("Opera��o Cancelada....");
				}
			}else {
				System.out.println("nenhuma venda "+oldCopia.toString()+", foi encontrada. Opera��o cancelada...");
			}
		}else {
			System.out.println("Opera��o cancelada...");
		}	
	}
	public void option5() {
		new ReservaController().option1();
	}
	public void option6() {
		new ServicoController().option1();
	}

}
