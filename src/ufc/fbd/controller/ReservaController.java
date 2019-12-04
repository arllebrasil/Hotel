


package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import ufc.fbd.DAO.HospedeReservaDAO;
import ufc.fbd.DAO.ReservaDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.HospedeReserva;
import ufc.fbd.model.Reserva;
import ufc.fbd.model.ReservaCompleta;

public class ReservaController {
	private ReservaDAO reservaDao  = null;
	private HospedeReservaDAO hospReserva = null;
	
	public ReservaController() {
		super();
		this.reservaDao = new ReservaDAO(new FbdConnection());
		this.hospReserva = new HospedeReservaDAO(new FbdConnection());
	}

	public void start() {
		boolean continuar = true;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("##### Controle de Reservas #####\n"
					+ "1- Listar Reservas, 2- Registrar reserva, 3 - Alterar reserva, "
					+ "4 - Remover reserva, Para retonar aperte ENTER");
			
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				System.out.println("##### Lista de Reservas #####\n");
				this.option1();
				break;
			case "2":
				System.out.println("##### Registrar reserva #####\n");
				this.option2();
				break;
			case "3":
				System.out.println("##### Alterar reserva #####\n");
				this.option3();
				break;
			case "4":
				System.out.println("##### Remover reserva #####\n");
				this.option4();
				break;
			case "":
				System.out.println("##### Saindo de reservas #####\n");
				continuar = false;
				break;
			default:
				break;
			}
		}while(continuar);
	}

	public void option1() {
		// TODO Auto-generated method stub
		ArrayList<ReservaCompleta> allReservas = reservaDao.find();
		for (ReservaCompleta reservaCompleta : allReservas) {
			System.out.println(reservaCompleta.toString());
		}
	}
	public void option2() {
		Reserva newReserva = null;
		HospedeReserva newHospReserva = null;
		Input input = new Input();
		
		newReserva = input.createReserva();
		if (newReserva != null) {
			newHospReserva = input.createHospedeReserva(newReserva.getIdReserva(),newReserva.getCpfHospede());
		}else {
			System.out.println("O cadastro foi cancelado!!!");
			return;
		}
		
		if(newReserva != null && newHospReserva != null) {
			
			int checked = reservaDao.create(newReserva);
			if(checked >= 1){
				checked = hospReserva.create(newHospReserva);
				if(checked >= 1) {
					System.out.println("reserva Concluida");
				}else {
					System.out.println("Algum Erro aconteceu");
				}
			}
		}
	}
	public void option3() {
		Reserva upReserva = null;
		HospedeReserva upHopReserva = null;
		Input input = new Input();
		upReserva = input.createReserva();
		if (upReserva != null) {
			upHopReserva = input.createHospedeReserva(upReserva.getIdReserva(),upReserva.getCpfHospede());
		}else {
			System.out.println("A alteração foi cancelado!!!");
			return;
		}
		
		if(upReserva != null && upHopReserva != null) {
			
			int checked = reservaDao.update(upReserva);
			if(checked >= 1){
				checked = hospReserva.update(upHopReserva);
				if(checked >= 1) {
					System.out.println("reserva auterada");
				}else {
					System.out.println("Algum Erro aconteceu");
				}
			}
		}
		
		
	}
	public void option4() {
		Input input = new Input();
		Reserva delReserva = input.createReserva();
		
		if(delReserva != null) {
			int checked = reservaDao.delete(delReserva);
			System.out.println("Remoções confirmadas:"+checked);
		}else {
			System.out.println("A remoção foi cancelada!!!");
		}
		
	}
}
