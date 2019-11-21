package ufc.fbd.model;

public class Telefone {
	public String cpf;
	public String telefone;
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Telefone(String cpf, String telefone) {
		super();
		this.cpf = cpf;
		this.telefone = telefone;
	}
	public Telefone() {
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return this.cpf+" "+this.telefone;
	}
}
