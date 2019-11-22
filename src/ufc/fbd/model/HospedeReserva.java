package ufc.fbd.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HospedeReserva {
	public int idReserva;
	public String cpfHospede;
	public Calendar inicioData;
	public Calendar fimData;
	public HospedeReserva() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HospedeReserva(int idReserva, String cpfHospede, String iData, String fData) {
		super();
		this.idReserva = idReserva;
		this.cpfHospede = cpfHospede;
		this.setInicioData(iData);
		this.setFimData(fData);
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		String inicio = dataForm.format(this.inicioData.getTime());
		String fim = dataForm.format(this.fimData.getTime());
		return "HospedeReserva [idReserva=" + idReserva + ", cpfHospede=" + cpfHospede + ", inicioData=" + inicio
				+ ", fimData=" + fim + "]";
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public String getCpfHospede() {
		return cpfHospede;
	}
	public void setCpfHospede(String cpfHospede) {
		this.cpfHospede = cpfHospede;
	}
	public Calendar getInicioData() {
		return inicioData;
	}
	public void setInicioData(Calendar iData) {
		this.inicioData = iData;
	}
	public void setInicioData(String iData) {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		this.inicioData = Calendar.getInstance();
		try {
			
			this.inicioData.setTime(dataForm.parse(iData));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Data não pose ser convertida para calendario");
			e.printStackTrace();
		}
	}
	public String showInicioData() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		return dataForm.format(this.inicioData.getTime());
	}
	public Calendar getFimData() {
		return fimData;
	}
	public void setFimData(Calendar fData) {
		this.fimData = fData;
	}
	public void setFimData(String fData) {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		this.fimData = Calendar.getInstance();
		try {
			this.fimData.setTime(dataForm.parse(fData));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Data não pose ser convertida para calendario");
			e.printStackTrace();
		}
	}
	public String showFimData() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		return dataForm.format(this.fimData.getTime());
	}
	
}
