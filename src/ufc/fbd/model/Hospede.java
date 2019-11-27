package ufc.fbd.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Hospede {
	public String cpf;
	public String nome;
	public Calendar dataNascimento;
	public  int endNumero;
	public  String endBairro;
	public  String endRua;
	public  String endCidade;
	public  int endCEP;
	public Hospede(String cpf, String nome, String dateNasc, int endNum, String bairro, String rua, String cidade,int cep) {
		// TODO Auto-generated constructor stub
		this.cpf = cpf;
		this.nome = nome;
		this.setDataNascimento(dateNasc);
		this.endNumero = endNum;
		this.endBairro = bairro;
		this.endRua = rua;
		this.endCidade = cidade;
		this.endCEP = cep;
	}
	public Hospede() {
		// TODO Auto-generated constructor stub
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public String showDataNascimento() {
		SimpleDateFormat dateForme = new SimpleDateFormat("dd/MM/yyyy");
		return dateForme.format(this.dataNascimento.getTime());
	}
	public void setDataNascimento(Calendar dataNacimento) {
		this.dataNascimento = dataNacimento;
	}
	public void setDataNascimento(String dataNacimento){
		this.dataNascimento = Calendar.getInstance();
		SimpleDateFormat dateForme = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataNascimento.setTime(dateForme.parse(dataNacimento));
		} catch (ParseException e) {
			System.out.println("Data não pose ser convertida para calendario");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		System.out.println(dateForme.format(this.dataNascimento.getTime()));	
	}
	public int getEndNumero() {
		return endNumero;
	}
	public void setEndNumero(int endNumero) {
		this.endNumero = endNumero;
	}
	public String getEndBairro() {
		return endBairro;
	}
	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}
	public String getEndRua() {
		return endRua;
	}
	public void setEndRua(String endRua) {
		this.endRua = endRua;
	}
	public String getEndCidade() {
		return endCidade;
	}
	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}
	public int getEndCEP() {
		return endCEP;
	}
	public void setEndCEP(int endCEP) {
		this.endCEP = endCEP;
	}
	@Override
	public String toString() {
		SimpleDateFormat dataForm = new SimpleDateFormat("dd/MM/yyyy");
		String dtNacimento = dataForm.format(dataNascimento.getTime());
		return "Hospede [cpf=" + cpf + ", nome=" + nome + ", dataNacimento=" + dtNacimento + ", endNumero="
				+ endNumero + ", endBairro=" + endBairro + ", endRua=" + endRua + ", endCidade=" + endCidade
				+ ", endCEP=" + endCEP + "]";
	}
}
