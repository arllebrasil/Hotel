package ufc.fbd.model;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ufc.fbd.config.FbdConnection;

public class ReservaCompleta {
	public int idReserva;
	public String nome;
	public String cpfHospede;
	public int quartoId;
	public double diaria;
	public Calendar inicioData;
	public Calendar fimData;
	public ReservaCompleta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservaCompleta(int idReserva, String cpfHospede,int quartoId,double diaria, String iData, String fData) {
		super();
		this.idReserva = idReserva;
		this.cpfHospede = cpfHospede;
		this.quartoId = quartoId;
		this.diaria = diaria;
		this.setInicioData(iData);
		this.setFimData(fData);
	}
	
	public int getIdReserva() {
		return idReserva;
	}
	@Override
	public String toString() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		String inicio = dataForm.format(this.inicioData.getTime());
		String fim = dataForm.format(this.fimData.getTime());
		return "ReservaCompleta [idReserva=" + idReserva + ", nome=" + nome + ", cpfHospede=" + cpfHospede
				+ ", quartoId=" + quartoId + ", diaria=" + diaria + ", inicioData=" + inicio + ", fimData="
				+ fim + "]";
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfHospede() {
		return cpfHospede;
	}
	public void setCpfHospede(String cpfHospede) {
		this.cpfHospede = cpfHospede;
	}
	public int getQuartoId() {
		return quartoId;
	}
	public void setQuartoId(int quartoId) {
		this.quartoId = quartoId;
	}
	public double getDiaria() {
		return diaria;
	}
	public void setDiaria(double diaria) {
		this.diaria = diaria;
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
