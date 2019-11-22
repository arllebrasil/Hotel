package ufc.fbd.view;

import java.util.ArrayList;

import ufc.fbd.DAO.HospedeReservaDAO;
import ufc.fbd.DAO.QuartoDAO;
import ufc.fbd.DAO.ReservaDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.HospedeReserva;
import ufc.fbd.model.Quarto;
import ufc.fbd.model.Reserva;

public class MainView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HospedeReservaDAO rDao = new HospedeReservaDAO(new FbdConnection());
		ReservaDAO reservaDAO = new ReservaDAO(new FbdConnection());
		
		
		
		//Reserva res = new Reserva(11,"07831240402",1);
		//reservaDAO.create(res);
		
		//HospedeReserva aux = new HospedeReserva(11,"07831240402","22/11/2019","23/11/2019");
		//rDao.create(aux);
		ArrayList<HospedeReserva> all = rDao.find();
		for (HospedeReserva hospedeReserva : all) {
			System.out.println(hospedeReserva.toString());
		}
	}
}
