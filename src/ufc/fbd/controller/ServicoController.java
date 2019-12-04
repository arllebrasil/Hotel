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
			System.out.println("\n#################          Controle de Serviços         #################\n"
					+ "\n1- Listar Serviços, 2- Registrar Serviços, 3- Alterar Serviços, 4 - Remover Serviços, ENTER para sair");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			switch (option) {
			case "1":
				System.out.println("\n#################          Listar Serviços          #################\n");
				this.option1();
				break;
			case "2":
				System.out.println("\n#################          Cadatrar Serviços          #################\n");
				this.option2();
				break;
			case "3":
				System.out.println("\n#################          Alterar Serviços          #################\n");
				this.option3();
				break;
			case "4":
				System.out.println("\n#################          Remover Serviços          #################\n");
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
		System.out.println("Informe os dados do serviços que deseja Registrar!");
		Servico newServico = input.createServico();
		if(newServico != null) {
			int response = this.servicoDao.create(newServico);
			System.out.println((response >= 1)?response+" serviços registrados!":"Um erro ocorreu, operação cancelada...");
		}else {
			System.out.println("Operação cancelada...");
		}
	}
	public void option3() {
		// TODO Auto-generated method stub
		Input input = new Input();
		
		System.out.println("Informe os dados do serviço que deseja Alterar!");
		Scanner scanner = new Scanner(System.in);
		String servicoId = scanner.nextLine();
		
		int oldId = Integer.parseInt(servicoId);
		Servico oldServico = servicoDao.findOne(oldId);
		
		if(oldServico != null) {
			System.out.println(oldServico.toString()+" encontrado!\nInforme os novos dados para alteração");
			oldServico = input.createServico();
			System.out.println(oldServico+" Deseja confirmar a atualização S/N?");
			
			String option = scanner.nextLine();
			if(option.equals("s")||option.equals("S")) {
				int response = this.servicoDao.update(oldServico);
				System.out.println((response >= 1)?response+" serviço alterados!":"Um erro ocorreu, operação cancelada...");
			}else {
				System.out.println("Operação cancelada...");
			}	
		}else {
			System.out.println("Nenhum serviço com id = "+oldId+"foi encontrado. Operação cancelada...");
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
			System.out.println(oldServico+" Deseja confirmar a remoção S/N?");
			String option = scanner.nextLine();
			
			if(option.equals("s")||option.equals("S")) {
				int response = this.servicoDao.delete(oldServico);
				System.out.println((response >= 1)?response+" serviços removidos!":"Um erro ocorreu, operação cancelada...");
			}else {
				System.out.println("Operação cancelada...");
			}	
		}else {
			System.out.println("Nenhum serviço com id = "+oldId+"foi encontrado. Operação cancelada...");
		}
		
	}

}
