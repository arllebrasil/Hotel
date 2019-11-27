package ufc.fbd.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.postgresql.jdbc2.optional.SimpleDataSource;

public class ReservaServico {
	public int idReserva;
	public Calendar data;
	public String cpfHospede;
	public int idServico;
	public ReservaServico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservaServico(int idReserva, String data, String cpfHospede, int idServico) {
		super();
		this.idReserva = idReserva;
		this.setData(data);
		this.cpfHospede = cpfHospede;
		this.idServico = idServico;
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
		this.data = Calendar.getInstance();
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
	public int getIdServico() {
		return idServico;
	}
	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}
	@Override
	public String toString() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		String date = dataForm.format(data.getTime());
		return "ReservaServico [idReserva=" + idReserva + ", data=" + date + ", cpfHospede=" + cpfHospede
				+ ", idServico=" + idServico + "]";
	}
	
}
