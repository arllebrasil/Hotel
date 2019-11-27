package ufc.fbd.view;

import java.util.ArrayList;

import ufc.fbd.DAO.ReservaProdutoDAO;
import ufc.fbd.DAO.ServicoDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.ReservaProduto;
import ufc.fbd.model.Servico;

public class MainView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReservaProdutoDAO dao = new ReservaProdutoDAO(new FbdConnection());
		
		ArrayList<ReservaProduto> allReservaPro = dao.find();
		for (ReservaProduto reservaProduto : allReservaPro) {
			System.out.println(reservaProduto.toString());
		}
		
	}
}
