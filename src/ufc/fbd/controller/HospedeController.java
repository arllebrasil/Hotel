package ufc.fbd.controller;

import java.util.ArrayList;

import ufc.fbd.DAO.HospedeDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Hospede;

public class HospedeController {
	FbdConnection fbdConnection = new FbdConnection();
	HospedeDAO clienteDao = new HospedeDAO(fbdConnection);
	
	public void show(String cpf){
		Hospede cliente = clienteDao.findOne(cpf);
		System.out.println(cliente.toString());
	}
	public void index(){
		ArrayList<Hospede> allCliente = clienteDao.find();
		for (Hospede hospede : allCliente) {
			System.out.println(hospede.toString());
		}
	}
	public void delete(Hospede cliente) {
		clienteDao.delete(cliente);
	}
	public void update(Hospede cliente) {
		clienteDao.update(cliente);
	}
	public void story(Hospede cliente) {
		clienteDao.create(cliente);
	}
}
