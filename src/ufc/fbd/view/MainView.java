package ufc.fbd.view;

import java.util.ArrayList;

import ufc.fbd.DAO.ReservaServicoDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.ReservaServico;

public class MainView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReservaServicoDAO dao = new ReservaServicoDAO(new FbdConnection());
		ReservaServico oldReservaServ= new ReservaServico(2,"13/10/2019","17831240314",1);
		
		ReservaServico newReservaServ= new ReservaServico(2,"28/10/2019","17831240314",2);
		dao.delete(newReservaServ);
		//2,17831240314,3,28/11/2019
		ArrayList<ReservaServico> allReservaServico = dao.find();
		for (ReservaServico reservaServico : allReservaServico) {
			System.out.println(reservaServico.toString());
		}	
	}
}
