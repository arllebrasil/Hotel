package ufc.fbd.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReservaProduto {
	public int idReserva;
	public String hospede;
	public String cpfHospede;
	public String produto;
	public int idProduto;
	public double preco;
	public Calendar dataCompra;
	
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
	public String getHospede() {
		return hospede;
	}
	public void setHospede(String hospede) {
		this.hospede = hospede;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
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
		this.dataCompra  =  Calendar.getInstance();
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
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	@Override
	public String toString() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		String date = dataForm.format(dataCompra.getTime());
		return "ReservaProduto [idReserva=" + idReserva + ", hospede=" + hospede + ", cpfHospede=" + cpfHospede
				+ ", produto=" + produto + ", idProduto=" + idProduto + ", preco=" + preco + ", data da compra="
				+ date + "]";
	}
	
}
