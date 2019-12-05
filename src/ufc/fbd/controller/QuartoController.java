package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import ufc.fbd.DAO.QuartoDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Quarto;

public class QuartoController {
	private QuartoDAO quartoDao = null;
	
	public QuartoController() {
		this.quartoDao = new QuartoDAO(new FbdConnection());
	}
	public void start() {
		boolean continuar = true;
		do {
			System.out.println("\n#################                    Controle de Quartos                    #################\n"
					+ "\n1- Listar Quartos, 2- Registrar Quarto, 3- Alterar Quarto, 4 - Remover Quarto, ENTER para sair");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			switch (option) {
			case "1":
				System.out.println("\n#################          Listar Quartos          #################\n");
				this.option1();
				break;
			case "2":
				System.out.println("\n#################          Cadatrar Quartos          #################\n");
				this.option2();
				break;
			case "3":
				System.out.println("\n#################          Alterar Quartos          #################\n");
				this.option3();
				break;
			case "4":
				System.out.println("\n#################          Remover Quartos          #################\n");
				this.option4();
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
	public void option1() {
		// TODO Auto-generated method stub
		ArrayList<Quarto> allQuartos = quartoDao.find();
		for (Quarto quarto : allQuartos) {
			System.out.println(quarto.toString());
		}
	}
	public void option2() {
		// TODO Auto-generated method stub
		Input input = new Input();
		System.out.println("\nPreencha os dados do novo quarto!\n");
		Quarto newQuarto = input.createQuarto();
		if (newQuarto != null) {
			int response = this.quartoDao.create(newQuarto);
			System.out.println((response >= 1)?response+" inser��es concluidas!!!":"Ouve uma viola��o e a inser��o foi cancelada!!!");
		}else {
			System.out.println("A opera��o foi cancelada...");
		}
	}
	public void option3() {
		// TODO Auto-generated method stub
		Input input = new Input();
		
		System.out.println("\nInforme o numero do quarto para ser alterado!\n");
		Scanner scanner = new Scanner(System.in);
		int oldId = scanner.nextInt();
		
		Quarto oldQuarto = quartoDao.findOne(oldId);
		
		if (oldQuarto != null) {
			System.out.println(oldQuarto.toString()+" Encontrado...\n");
			System.out.println("Informe os novos dados a serem registrados!.\nObs: o numero do quarto n�o deve ser alterado!\n");
			Quarto newQuarto = input.createQuarto();
			if (newQuarto != null) {
				System.out.println(newQuarto.toString()+" Confirmar altera��o S/N ?");
				scanner = new Scanner(System.in);
				String option = scanner.nextLine();
				
				if(option.equals("s")||option.equals("s")) {

					int response = this.quartoDao.update(newQuarto);
					System.out.println((response >= 1)?response+" altera��es concluidas!!!":"Ouve uma viola��o e a altera��o foi cancelada!!!");
				}else {
					System.out.println("Opera��o cancelada...");
				}
			} else {
				System.out.println("Opera��o cancelada...");
			}
		}else {
				System.out.println("Nenhum quarto com numero " +oldId+ " foi encontrado\nOpera��o cancelada...");
		}
	}
	public void option4() {
		// TODO Auto-generated method stub
		System.out.println("\nInforme o numero do quarto para ser removido!\n");
		Scanner scanner = new Scanner(System.in);
		int oldId = scanner.nextInt();
		Quarto oldQuarto = quartoDao.findOne(oldId);
		
		if (oldQuarto != null) {
			System.out.println(oldQuarto.toString()+" Encontrado...");
			System.out.println("Deseja Confirmar a remo��o S/N ?");
			Scanner scanner2 = new Scanner(System.in);
			String option = scanner2.nextLine();
			if ((option.equals("s")) || (option.equals("S"))) {
				int response = this.quartoDao.delete(oldQuarto);
				System.out.println((response >= 1)?response+" exclus�es concluidas!!!":"Ouve um Erro exclus�o foi cancelada!!!");
			} else {
				System.out.println("Opera��o cancelada...");
			}
		}else {
				System.out.println("Nenhum quarto com numero " +oldId+ " foi encontrado\nOpera��o cancelada...");
		}
		
	}
}
