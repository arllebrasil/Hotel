package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import ufc.fbd.DAO.HospedeDAO;
import ufc.fbd.DAO.TelefoneDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Hospede;
import ufc.fbd.model.Telefone;

public class HospedeController {
	HospedeDAO hospedeDao = null;
	TelefoneDAO telefoneDao = null;
	
	public HospedeController() {
		this.hospedeDao = new HospedeDAO(new FbdConnection());
		this.telefoneDao = new TelefoneDAO(new FbdConnection());
	}
	public void start() {
		boolean continuar = true;
		do {
			System.out.println("\n################       Controle de Hospede       ################\n");
			System.out.println("1- Listar Hospedes, 2- Registrar Hospede, 3- Atualizar Hospede, 4- Remover Hospede,\n"
					+ "5- Lista de Telefones, 6- Registra Telefone, 7- Remover Telefone, ENTER - para retornar!");
			
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			switch (option) {
			case "1":
				this.option1();
				break;
			case "2":
				this.option2();
				break;
			case "3":
				this.option3();
				break;
			case "4":
				this.option4();
				break;
			case "5":
				this.option5();
				break;
			case "6":
				this.option6();
				break;
			case "7":
				this.option7();
				break;
			case "":
				continuar = false;
				break;

			default:
				break;
			}
		}while(continuar);
	}

	private void option1() {
		// TODO Auto-generated method stub
		ArrayList<Hospede> allHospede = this.hospedeDao.find();
		for (Hospede hospede : allHospede) {
			System.out.println(hospede.toString());
		}
	}

	private void option2() {
		// TODO Auto-generated method stub
		Input input = new Input();
		Hospede newHospede = input.createHospede(); 
		if(newHospede != null) {			
			int response = hospedeDao.create(newHospede);
			System.out.println((response >= 1)? response+" Inserção completa!!!":"Erro de Incerção");
		}else {
			System.out.println("A inserção foi cancelada!!!");
		}
	}

	private void option3() {
		// TODO Auto-generated method stub
		Input input = new Input();
		boolean continuar = true;
		do {
			System.out.println("Informe os dados atuais do hospede!");
			Scanner scanner = new Scanner(System.in);
			
			String oldCpf = scanner.nextLine();
			String oldNome = scanner.nextLine();
			Hospede oldHospede = hospedeDao.findOne(oldCpf, oldNome);
			
			if(oldHospede == null) {
				
				System.out.println("O usuaria "+oldNome+" não foi encontrado. Deseja recomeçar a atualização S/N ?");
				String option = scanner.nextLine();
				continuar = (option == "s" || option == "S")?true:false;
				System.out.println((continuar)?"...":"Operação cancelada!!!");
				
			}else{
				System.out.println("Agora informe os novos dados para o Hospede..\n"+ oldHospede.toString()+"\nObs:cpf não deve ser alterado!");
				Hospede newHospede = input.createHospede();
				
				if(oldHospede != null && newHospede != null) {			
					int response = hospedeDao.update(oldHospede,newHospede);
					System.out.println((response >= 1)? response+" Atualização completa!!!":"Erro de Atualização");
				}else {
					System.out.println("A inserção não pode ser concluida sem os dados (cancelado)!!!");
				}
				continuar = false;
			}
		} while (continuar);	
	}

	private void option4() {
		// TODO Auto-generated method stub
		boolean continuar = true;
		do {
			System.out.println("Informe o CPF e NOME do hospede!");
			Scanner scanner = new Scanner(System.in);
			String oldCpf = scanner.nextLine();
			String oldNome = scanner.nextLine();
			Hospede oldHospede = hospedeDao.findOne(oldCpf, oldNome);
			
			if(oldHospede == null) {
				System.out.println("Nenhum "+oldNome+"foi encontrado!\nDeseja Recomeçar S/N ?");
				String option = scanner.nextLine();
				continuar = (option == "s" || option == "S")?true:false;
				System.out.println((continuar)?"...":"Operação cancelada!!!");
			}else{
				int response = hospedeDao.delete(oldHospede);
				System.out.println(response+" remoções completas!");
				continuar = false;
			}
		} while (continuar);
		
	}

	private void option5() {
		// TODO Auto-generated method stub
		ArrayList<Telefone> allTelefone = telefoneDao.find();
		for (Telefone telefone : allTelefone) {
			System.out.println(telefone.toString());
		}
	}

	private void option6(){
		// TODO Auto-generated method stub
		Input input = new Input();
		System.out.println("Informe uma lista de Telefones para Inseridos.\nObs: Telefones com cpf inexistentes seram descartados da operação!");
		ArrayList <Telefone> newTelefones = input.createTelefone();
		for (Telefone telefone : newTelefones) {
			telefoneDao.create(telefone);
		}
	}
	private void option7() {
		// TODO Auto-generated method stub
		Input input = new Input();
		System.out.println("Informe uma lista de Telefones para removidos.\nObs: Telefones com cpf inexistentes seram descartados da operação!");
		ArrayList <Telefone> newTelefones = input.createTelefone();
		for (Telefone telefone : newTelefones) {
			telefoneDao.delete(telefone);
		}
	}
}
