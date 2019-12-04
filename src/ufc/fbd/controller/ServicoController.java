package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import ufc.fbd.DAO.ServicoDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Servico;

public class ServicoController {
	private ServicoDAO servicoDao = null;
	public ServicoController() {
		this.servicoDao = new ServicoDAO(new FbdConnection());
	}
	public void start() {
		boolean continuar = true;
		do {
			System.out.println("\n#################          Controle de Servi�os         #################\n"
					+ "\n1- Listar Servi�os, 2- Registrar Servi�os, 3- Alterar Servi�os, 4 - Remover Servi�os, ENTER para sair");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			switch (option) {
			case "1":
				System.out.println("\n#################          Listar Servi�os          #################\n");
				this.option1();
				break;
			case "2":
				System.out.println("\n#################          Cadatrar Servi�os          #################\n");
				this.option2();
				break;
			case "3":
				System.out.println("\n#################          Alterar Servi�os          #################\n");
				this.option3();
				break;
			case "4":
				System.out.println("\n#################          Remover Servi�os          #################\n");
				this.option4();
				break;
			case "":
				System.out.println("\n#################          ................         #################\n");
				continuar = false;
				break;
			default:
				break;
			}
		} while (continuar);
	}
	public void option1() {
		// TODO Auto-generated method stub
		ArrayList<Servico> allServico = this.servicoDao.find();
		for (Servico servico : allServico) {
			System.out.println(servico.toString());
		}
		
	}
	public void option2() {
		// TODO Auto-generated method stub
		Input input = new Input();
		System.out.println("Informe os dados do servi�os que deseja Registrar!");
		Servico newServico = input.createServico();
		if(newServico != null) {
			int response = this.servicoDao.create(newServico);
			System.out.println((response >= 1)?response+" servi�os registrados!":"Um erro ocorreu, opera��o cancelada...");
		}else {
			System.out.println("Opera��o cancelada...");
		}
	}
	public void option3() {
		// TODO Auto-generated method stub
		Input input = new Input();
		
		System.out.println("Informe os dados do servi�o que deseja Alterar!");
		Scanner scanner = new Scanner(System.in);
		String servicoId = scanner.nextLine();
		
		int oldId = Integer.parseInt(servicoId);
		Servico oldServico = servicoDao.findOne(oldId);
		
		if(oldServico != null) {
			System.out.println(oldServico.toString()+" encontrado!\nInforme os novos dados para altera��o");
			oldServico = input.createServico();
			System.out.println(oldServico+" Deseja confirmar a atualiza��o S/N?");
			
			String option = scanner.nextLine();
			if(option.equals("s")||option.equals("S")) {
				int response = this.servicoDao.update(oldServico);
				System.out.println((response >= 1)?response+" servi�o alterados!":"Um erro ocorreu, opera��o cancelada...");
			}else {
				System.out.println("Opera��o cancelada...");
			}	
		}else {
			System.out.println("Nenhum servi�o com id = "+oldId+"foi encontrado. Opera��o cancelada...");
		}
	}
	public void option4() {
		// TODO Auto-generated method stub
		Input input = new Input();
		
		System.out.println("Informe os dados do produto que deseja Remover!");
		Scanner scanner = new Scanner(System.in);
		String servicoId = scanner.nextLine();
		
		int oldId = Integer.parseInt(servicoId);
		Servico oldServico = this.servicoDao.findOne(oldId);
		
		if(oldServico != null) {
			System.out.println(oldServico+" Deseja confirmar a remo��o S/N?");
			String option = scanner.nextLine();
			
			if(option.equals("s")||option.equals("S")) {
				int response = this.servicoDao.delete(oldServico);
				System.out.println((response >= 1)?response+" servi�os removidos!":"Um erro ocorreu, opera��o cancelada...");
			}else {
				System.out.println("Opera��o cancelada...");
			}	
		}else {
			System.out.println("Nenhum servi�o com id = "+oldId+"foi encontrado. Opera��o cancelada...");
		}
		
	}

}
