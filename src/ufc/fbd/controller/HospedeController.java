package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import ufc.fbd.DAO.HospedeDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Hospede;

public class HospedeController {
	HospedeDAO hospedeDao = null;
	public HospedeController() {
		this.hospedeDao = new HospedeDAO(new FbdConnection());
	}
	
	public void start() {
		
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
			
			break;
		case "5":
			
			break;
		case "6":
			
			break;
		default:
			
			break;
		}
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
		
		System.out.println("Para alterar um hospede você deve informar seus dados!!!");
		System.out.println("Digite o cpf: ");
		String oldCpf = scanner.nextLine();
		
		Hospede oldHospede = hospedeDao.findOne(cpfH, nome);
		System.out.println("Agora você deve informar os novos dados do hospede que devem ser salvas!!!");
		Hospede newHospede = input.createHospede();
		
		System.out.println("");
	}
	public void update() {
		
	}
}
