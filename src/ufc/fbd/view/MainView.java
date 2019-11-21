package ufc.fbd.view;

import java.util.ArrayList;

import ufc.fbd.DAO.QuartoDAO;
import ufc.fbd.config.FbdConnection;

public class MainView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuartoDAO qtdDAO = new QuartoDAO(new FbdConnection());
		qtdDAO.find();
	}
}
