package ufc.fbd.view;

import java.util.ArrayList;

import ufc.fbd.DAO.ReservaServicoDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Hospede;
import ufc.fbd.model.ReservaServico;

public class MainView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Input input = new Input();
		Hospede aux = input.createHospede();
		System.out.println(aux.toString());
	}
}
