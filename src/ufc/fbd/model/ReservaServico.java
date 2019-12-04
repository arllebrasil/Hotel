package ufc.fbd.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.postgresql.jdbc2.optional.SimpleDataSource;

public class ReservaServico {
	public int idReserva;
	public String hospede;
	public String cpfHospede;
	public String servico;
	public int idServico;
	public double preco;
	public Calendar dataCompra;
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
	public String getHospede() {
		return hospede;
	}
	public void setHospede(String hospede) {
		this.hospede = hospede;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Calendar getData() {
		return dataCompra;
	}
	public String showData() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		return dataForm.format(this.dataCompra.getTime());
	}
	public void setData(Calendar data) {
		this.dataCompra = data;
	}
	public void setData(String data) {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		this.dataCompra = Calendar.getInstance();
		try {
			this.dataCompra.setTime(dataForm.parse(data));
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
		String date = dataForm.format(dataCompra.getTime());
		return "ReservaServico [idReserva=" + idReserva + ", hospede=" + hospede + ", cpfHospede=" + cpfHospede
				+ ", servico=" + servico + ", idServico=" + idServico + ", preco=" + preco + ", data da Compra="
				+ date + "]";
	}
	
}
