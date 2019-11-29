package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

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
		TelefoneDAO telefoneDao = new TelefoneDAO(new FbdConnection());
	}
	
	public void start() {
		boolean continuar = true;
		do {
			System.out.println("#####	Controle de Hospedes	######\n"
					+ " 1- Listar Hospedes, 2- Registrar Hospede, 3 - Alterar Hospede"
					+ "4 - Deletar Hospede,\n 5- Registrar Telefone p/Hospede, 6- Apagar Telefone p/Hospede, Para retonar aperte ENTER");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			switch (option) {
			case "1":	
				System.out.println("##### Listar Todos os Hospedes!!! #####");
				this.option1();
				
				break;
			case "2":
				System.out.println("##### Registrar Hospede!!! #####");
				this.option2();
				break;
			case "3":
				System.out.println("##### Alterar Hospede!!! #####");
				this.option3();
				break;
			case "4":
				System.out.println("##### Remover Hospede!!! #####");
				this.option4();
				
				break;
			case "5":
				System.out.println("##### Registrar Telefones!!! #####");
				this.option4();
				break;
			case "6":
				System.out.println("##### Remover Telefones!!! #####");
				this.option4();
				break;
			default:
				continuar = false;
				//Limpar Tela;
				break;
			}
		}while(continuar );
		
	}
	public void option1(){
		ArrayList<Hospede> allHospede = hospedeDao.find();
		for (Hospede hospede : allHospede) {
			System.out.println(hospede.toString());
		}
	}
	public void option2(){
		
		Input input = new Input();
		Hospede newHospede = input.createHospede();
		System.out.println("Para confirmar o cadastro do "+newHospede+" aperte (S/N):");
		
		Scanner scanner = new Scanner(System.in);
		String confirmar = scanner.nextLine();
		if(confirmar == "s" || confirmar =="S") {
			hospedeDao.create(newHospede);
		}else if(confirmar == "n" || confirmar == "N") {
			System.out.println("Cadastro Cancelado!!!");
		}else {
			System.out.println("Entrada Invalida, o cadastro será cancelado!!!");
		}	
	}
	public void option3() {
		Input input = new  Input();
		Scanner scanner = new Scanner(System.in);
		Hospede oldHospede = null;
		Hospede newHospede = null;
		
		System.out.println("Para alterar um hospede você deve informar seus dados!!!");
		boolean continuar = true;
		do {
			System.out.println("Digite o cpf do hospede: ");
			String oldCpf = scanner.nextLine();
			System.out.println("Digite o nome do hospede: ");
			String oldNome = scanner.nextLine();
			oldHospede = hospedeDao.findOne(oldCpf, oldNome);
			
			if(oldHospede == null) {
				System.out.println("O Hospede não foi encontrado!!!\nDeseja Recomeçar(S/N)?");
				String option = scanner.nextLine();
				if(option != "s" || option != "S") {
					continuar = false;
				}
			}else {
				continuar = false;
			}	
		}while(continuar);
		if(oldHospede != null) {
			System.out.println("Agora você deve informar os novos dados do hospede que devem ser salvas!!!");
			newHospede = input.createHospede();
		}
		if(oldHospede != null && newHospede != null) {
			System.out.println("Cofirmar atualização(S/N)???\n"
					+oldHospede.toString()
					+"\nAlterar para : ");
			String option = scanner.nextLine();
			if(option != "s"  || option == "S") {
				hospedeDao.update(oldHospede,newHospede);
			}
		}
		
		System.out.println("");
	}
	public void option4() {
		Input input = new  Input();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Para remover um hospede da base você deve informar seus dados!!!");
		Hospede old = input.createHospede();
		
		if(old != null) {
			System.out.println("Cofirme remoção do hospede(S/N)???\n"
					+old.toString());
			String option = scanner.nextLine();
			if(option == "s" || option == "S") {
				hospedeDao.delete(old);
			}
		}
		
	}
	public void option5() {
		Input input = new  Input();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Registrar um ou mais Telefones!!!");
		ArrayList<Telefone> allCreate = input.createTelefone();
		
		System.out.println("Telefones:");
		for (Telefone telefone : allCreate) {
			System.out.println(telefone.toString());
		}
		
		System.out.println("Pressione para cofirmar(S/N)!!!");
		String option = scanner.nextLine();
		if(option == "S" || option == "s") {
			for (Telefone telefone : allCreate) {
				this.telefoneDao.create(telefone);
			}	
		}else {
			System.out.println("Os Registros foram cancelados!!!");
		}
	}
	public void option6() {
		Input input = new  Input();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Remover um ou mais Telefones!!!");
		ArrayList<Telefone> allCreate = input.createTelefone();
		
		System.out.println("Telefones:");
		for (Telefone telefone : allCreate) {
			System.out.println(telefone.toString());
		}
		
		System.out.println("Pressione para cofirmar(S/N)!!!");
		String option = scanner.nextLine();
		if(option == "S" || option == "s") {
			for (Telefone telefone : allCreate) {
				this.telefoneDao.delete(telefone);
			}	
		}else {
			System.out.println("As Remoções foram canceladas!!!");
		}
	}
}
