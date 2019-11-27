package ufc.fbd.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReservaProduto {
	public int idReserva;
	public Calendar data;
	public String cpfHospede;
	public int idProduto;
	public ReservaProduto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservaProduto(int idReserva,String data,String cpfHospede,int idProduto) {
		super();
		this.idReserva = idReserva;
		this.setData(data);
		this.cpfHospede = cpfHospede;
		this.idProduto = idProduto;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public Calendar getData() {
		return data;
	}
	public String showData() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		return dataForm.format(this.data.getTime());
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public void setData(String data) {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		this.data  =  Calendar.getInstance();
		try {
			this.data.setTime(dataForm.parse(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Data Informada invalida");
			e.printStackTrace();
		}
	}
	public String getCpfHospede() {
		return cpfHospede;
	}
	public void setCpfHospede(String cpfHospede) {
		this.cpfHospede = cpfHospede;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	@Override
	public String toString() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		String date = dataForm.format(data.getTime());
		return "ReservaProduto [idReserva=" + idReserva + ", data=" + date + ", cpfHospede=" + cpfHospede
				+ ", idProduto=" + idProduto + "]";
	}
	
	
}
